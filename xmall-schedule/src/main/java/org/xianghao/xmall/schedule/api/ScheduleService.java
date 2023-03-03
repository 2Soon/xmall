package org.xianghao.xmall.schedule.api;

import org.xianghao.xmall.api.api.schedule.ScheduleApi;
import org.xianghao.xmall.api.constant.wms.PurchaseInputOrderStatus;
import org.xianghao.xmall.api.domain.customer.ReturnGoodsWorksheetDTO;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.api.domain.purchase.PurchaseOrderDTO;
import org.xianghao.xmall.api.domain.purchase.PurchaseOrderItemDTO;
import org.xianghao.xmall.api.domain.schedule.SaleDeliveryScheduleResult;
import org.xianghao.xmall.api.domain.schedule.ScheduleReturnGoodsWorksheetDTO;
import org.xianghao.xmall.api.domain.wms.*;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.schedule.constant.StockUpdateEvent;
import org.xianghao.xmall.schedule.dao.ScheduleOrderPickingItemDAO;
import org.xianghao.xmall.schedule.dao.ScheduleOrderSendOutDetailDAO;
import org.xianghao.xmall.schedule.domain.ScheduleOrderPickingItemDO;
import org.xianghao.xmall.schedule.domain.ScheduleOrderSendOutDetailDO;
import org.xianghao.xmall.schedule.service.SaleDeliveryOrderBuilder;
import org.xianghao.xmall.schedule.service.SaleDeliveryScheduler;
import org.xianghao.xmall.schedule.service.impl.SaleDeliveryOrderBuilderFactory;
import org.xianghao.xmall.schedule.stock.ScheduleStockUpdater;
import org.xianghao.xmall.schedule.stock.ScheduleStockUpdaterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 调度中心对外接口service组件
 * @author xianghao
 *
 */
@RestController
public class ScheduleService implements ScheduleApi {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);
	
	/**
	 * 仓储服务
	 */
	@Autowired
	private WmsService wmsService;
	/**
	 * 销售出库单构建器工厂
	 */
	@Autowired
	private SaleDeliveryOrderBuilderFactory saleDeliveryOrderBuilderFactory;
	/**
	 * 库存服务
	 */
	@Autowired
	private InventoryService inventoryService;
	/**
	 * 销售出库调度器
	 */
	@Autowired
	private SaleDeliveryScheduler saleDeliveryScheduler;
	/**
	 * 拣货条目管理DAO组件
	 */
	@Autowired
	private ScheduleOrderPickingItemDAO pickingItemDAO;
	/**
	 * 发货明细管理DAO组件
	 */
	@Autowired
	private ScheduleOrderSendOutDetailDAO sendOutDetailDAO;
	/**
	 * 库存更新组件工厂
	 */
	@Autowired
	private ScheduleStockUpdaterFactory stockUpdaterFactory;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	/**
	 * 订单服务
	 */
	@Autowired
	private OrderService orderService;
	
	/**
	 * 通知库存中心，“采购入库完成”事件发生了
	 * @param purchaseInputOrderDTO 采购入库单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean informPurchaseInputFinished(
			PurchaseInputOrderDTO purchaseInputOrder) {
		try {
			ScheduleStockUpdater stockUpdater = stockUpdaterFactory.create(
					StockUpdateEvent.PURCHASE_INPUT, purchaseInputOrder);
			stockUpdater.update();
			
			inventoryService.informPurchaseInputFinished(purchaseInputOrder);
			
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知库存中心，“完成退货入库”事件发生了
	 * @param returnGoodsInputOrderDTO 退货入库单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean informReturnGoodsInputFinished(
			ReturnGoodsInputOrderDTO returnGoodsInputOrder) {
		try {
			ScheduleStockUpdater stockUpdater = stockUpdaterFactory.create(
					StockUpdateEvent.RETURN_GOODS_INPUT, returnGoodsInputOrder);
			stockUpdater.update();
			
			inventoryService.informReturnGoodsInputFinished(returnGoodsInputOrder);
			
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知库存中心，“提交订单”事件发生了
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean informSubmitOrderEvent(OrderInfoDTO order) {
		try {
			for(OrderItemDTO orderItem : order.getOrderItems()) {
				SaleDeliveryScheduleResult scheduleResult =
						saleDeliveryScheduler.schedule(orderItem);
				
				List<ScheduleOrderPickingItemDO> pickingItems = ObjectUtils.convertList(
						scheduleResult.getPickingItems(), ScheduleOrderPickingItemDO.class);
				List<ScheduleOrderSendOutDetailDO> sendOutDetails = ObjectUtils.convertList(
						scheduleResult.getSendOutDetails(), ScheduleOrderSendOutDetailDO.class);
				
				pickingItemDAO.batchSave(orderItem.getOrderInfoId(), orderItem.getId(), pickingItems); 
				sendOutDetailDAO.batchSave(orderItem.getOrderInfoId(), orderItem.getId(), sendOutDetails);
				
				ScheduleStockUpdater stockUpdater = stockUpdaterFactory.create(
						StockUpdateEvent.SUBMIT_ORDER, scheduleResult);
				stockUpdater.update();
				
				wmsService.informSubmitOrderEvent(convertSaleDeliveryScheduleResult(scheduleResult)); 
			}

			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 通知库存中心，“取消订单”事件发生了
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean informCancelOrderEvent(OrderInfoDTO order) {
		try {
			for(OrderItemDTO orderItem : order.getOrderItems()) {
				SaleDeliveryScheduleResult scheduleResult = saleDeliveryScheduler
						.getScheduleResult(orderItem);
				
				ScheduleStockUpdater stockUpdater = stockUpdaterFactory.create(
						StockUpdateEvent.CANCEL_ORDER, scheduleResult);
				stockUpdater.update();
				
				pickingItemDAO.removeByOrderItemId(orderItem.getOrderInfoId(), orderItem.getId()); 
				sendOutDetailDAO.removeByOrderItemId(orderItem.getOrderInfoId(), orderItem.getId()); 
				
				wmsService.informCancelOrderEvent(convertSaleDeliveryScheduleResult(scheduleResult));
			}
			
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知库存中心，“支付订单”事件发生了
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean informPayOrderEvent(OrderInfoDTO order) {
		try {
			for(OrderItemDTO orderItem : order.getOrderItems()) {
				SaleDeliveryScheduleResult scheduleResult = saleDeliveryScheduler
						.getScheduleResult(orderItem);
				
				ScheduleStockUpdater stockUpdater = stockUpdaterFactory.create(
						StockUpdateEvent.PAY_ORDER, scheduleResult);
				stockUpdater.update();
				
				wmsService.informPayOrderEvent(convertSaleDeliveryScheduleResult(scheduleResult));
			}
			
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 转换销售出库调度结果
	 * @param saleDeliveryScheduleResult
	 * @return
	 * @throws Exception
	 */
	private WmsSaleDeliveryScheduleResult convertSaleDeliveryScheduleResult(
			SaleDeliveryScheduleResult result) throws Exception {
		WmsSaleDeliveryScheduleResult targetResult = 
				result.clone(WmsSaleDeliveryScheduleResult.class);
		
		List<WmsScheduleOrderPickingItemDTO> targetPickingItems = 
				ObjectUtils.convertList(result.getPickingItems(), WmsScheduleOrderPickingItemDTO.class);
		targetResult.setPickingItems(targetPickingItems); 
		
		List<WmsScheduleOrderSendOutDetailDTO> targetSendOutDetails = 
				ObjectUtils.convertList(result.getSendOutDetails(), WmsScheduleOrderSendOutDetailDTO.class);
		targetResult.setSendOutDetails(targetSendOutDetails);
		
		return targetResult;
	}
	
	/**
	 * 调度采购入库
	 * @param purchaseOrderDTO 采购单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean schedulePurchaseInput(PurchaseOrderDTO purchaseOrder) {
		try {
			PurchaseInputOrderDTO purchaseInputOrder = 
					createPurchaseInputOrder(purchaseOrder);
			
			List<PurchaseInputOrderItemDTO> purchaseInputOrderItems =
					new ArrayList<PurchaseInputOrderItemDTO>();
			for(PurchaseOrderItemDTO purchaseOrderItem : purchaseOrder.getItems()) {
				purchaseInputOrderItems.add(createPurchaseInputOrderItem(purchaseOrderItem)); 
			}
			purchaseInputOrder.setItems(purchaseInputOrderItems);  
			
			wmsService.createPurchaseInputOrder(purchaseInputOrder); 
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
		return true;
	}
	
	/**
	 * 创建一个采购入库单
	 * @param purchaseOrder 采购单
	 * @return 采购入库单
	 * @throws Exception
	 */
	private PurchaseInputOrderDTO createPurchaseInputOrder(
			PurchaseOrderDTO purchaseOrder) throws Exception {
		PurchaseInputOrderDTO purchaseInputOrder = 
				purchaseOrder.clone(PurchaseInputOrderDTO.class);
		
		purchaseInputOrder.setId(null); 
		purchaseInputOrder.setPurchaseOrderId(purchaseOrder.getId()); 
		purchaseInputOrder.setGmtCreate(dateProvider.getCurrentTime());
		purchaseInputOrder.setGmtModified(dateProvider.getCurrentTime()); 
		purchaseInputOrder.setStatus(PurchaseInputOrderStatus.EDITING);
		purchaseInputOrder.setPurchaseContactor(purchaseOrder.getContactor());
		purchaseInputOrder.setPurchaseContactorPhoneNumber(
				purchaseOrder.getContactorPhoneNumber()); 
		purchaseInputOrder.setPurchaseContactorEmail(purchaseOrder.getContactorEmail()); 
		purchaseInputOrder.setPurchaseOrderRemark(purchaseOrder.getRemark()); 
		
		return purchaseInputOrder;
	}
	
	/**
	 * 创建采购入库单条目
	 * @param purchaseOrderItem
	 * @return
	 * @throws Exception
	 */
	private PurchaseInputOrderItemDTO createPurchaseInputOrderItem(
			PurchaseOrderItemDTO purchaseOrderItem) throws Exception {
		PurchaseInputOrderItemDTO purchaseInputOrderItem = 
				purchaseOrderItem.clone(PurchaseInputOrderItemDTO.class);
		purchaseInputOrderItem.setId(null); 
		purchaseInputOrderItem.setGmtCreate(dateProvider.getCurrentTime()); 
		purchaseInputOrderItem.setGmtModified(dateProvider.getCurrentTime());  
		return purchaseInputOrderItem;
	}

	/**
	 * 调度销售出库
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean scheduleSaleDelivery(OrderInfoDTO order) {
		try {
			SaleDeliveryOrderBuilder saleDeliveryOrderBuilder = 
					saleDeliveryOrderBuilderFactory.get();
			
			SaleDeliveryOrderDTO saleDeliveryOrder = saleDeliveryOrderBuilder
					.setOrderRelatedData(order)
					.createSaleDeliveryOrderItems(order.getOrderItems())
					.createSendOutOrder(order)
					.createLogisticOrder(order)
					.initStatus()
					.initTimes()
					.create();
			
			wmsService.createSaleDeliveryOrder(saleDeliveryOrder);
			
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 调度退货入库
	 * @param orderDTO 订单DTO
	 * @param returnGoodsWorksheetDTO 退货工单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean scheduleReturnGoodsInput(
			ScheduleReturnGoodsWorksheetDTO scheduleReturnGoodsWorksheet) {
		try {
			OrderInfoDTO order = scheduleReturnGoodsWorksheet.getOrder();
			ReturnGoodsWorksheetDTO returnGoodsWorksheet = scheduleReturnGoodsWorksheet.getReturnGoodsWorksheetDTO();
			
			ReturnGoodsInputOrderDTO returnGoodsInputOrder = order.clone(ReturnGoodsInputOrderDTO.class);
			returnGoodsInputOrder.setId(null); 
			returnGoodsInputOrder.setReturnGoodsWorksheetId(returnGoodsWorksheet.getId()); 
			returnGoodsInputOrder.setOrderId(order.getId());
			returnGoodsInputOrder.setReturnGoodsReason(returnGoodsWorksheet.getReturnGoodsReason()); 
			returnGoodsInputOrder.setReturnGoodsRemark(returnGoodsWorksheet.getReturnGoodsRemark()); 
			returnGoodsInputOrder.setGmtCreate(dateProvider.getCurrentTime()); 
			returnGoodsInputOrder.setGmtModified(dateProvider.getCurrentTime()); 
			
			List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItems = ObjectUtils.convertList(
					order.getOrderItems(), ReturnGoodsInputOrderItemDTO.class);
			for(ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItem : returnGoodsInputOrderItems) {
				returnGoodsInputOrderItem.setId(null);  
				returnGoodsInputOrderItem.setGmtCreate(dateProvider.getCurrentTime()); 
				returnGoodsInputOrderItem.setGmtModified(dateProvider.getCurrentTime()); 
			}
			returnGoodsInputOrder.setItems(returnGoodsInputOrderItems); 
			
			wmsService.createReturnGoodsInputOrder(returnGoodsInputOrder);
			
			return true;
		} catch (Exception e) {
			logger.error("error", e);  
			return false;
		}
	}
	
	/**
	 * 获取调度结果
	 * @param orderInfoId 订单id
	 * @param goodsSkuId 商品sku id
	 * @return 调度结果
	 */
	@Override
	public SaleDeliveryScheduleResult getScheduleResult(Long orderInfoId, Long goodsSkuId) {
		try {
			OrderInfoDTO order = orderService.getOrderById(orderInfoId);
			OrderItemDTO targetOrderItem = null;
			
			for(OrderItemDTO orderItem : order.getOrderItems()) {
				if(orderItem.getGoodsSkuId().equals(goodsSkuId)) {
					targetOrderItem = orderItem;
					break;
				}
			}
			
			return saleDeliveryScheduler.getScheduleResult(targetOrderItem); 
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
}
