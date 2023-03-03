package org.xianghao.xmall.wms.api;


import org.xianghao.xmall.api.api.wms.WmsApi;
import org.xianghao.xmall.api.constant.wms.PurchaseInputOrderStatus;
import org.xianghao.xmall.api.constant.wms.WmsStockUpdateEvent;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderDTO;
import org.xianghao.xmall.api.domain.wms.SaleDeliveryOrderDTO;
import org.xianghao.xmall.api.domain.wms.WmsSaleDeliveryScheduleResult;
import org.xianghao.xmall.wms.service.PurchaseInputOrderService;
import org.xianghao.xmall.wms.service.ReturnGoodsInputOrderService;
import org.xianghao.xmall.wms.service.SaleDeliveryOrderService;
import org.xianghao.xmall.wms.stock.WmsStockUpdater;
import org.xianghao.xmall.wms.stock.WmsStockUpdaterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * wms中心对外接口service组件
 * @author xianghao
 *
 */
@RestController
public class WmsService implements WmsApi {
	
	private static final Logger logger = LoggerFactory.getLogger(WmsService.class);
	
	/**
	 * 采购入库单管理service组件
	 */
	@Autowired
	private PurchaseInputOrderService purchaseInputOrderService;
	/**
	 * 销售出库单管理service组件
	 */
	@Autowired
	private SaleDeliveryOrderService saleDeliveryOrderService;
	/**
	 * 退货入库单管理service组件
	 */
	@Autowired
	private ReturnGoodsInputOrderService returnGoodsInputOrderService;
	/**
	 * 库存更新组件工厂
	 */
	@Autowired
	private WmsStockUpdaterFactory stockUpdaterFactory;
	/**
	 * 采购中心
	 */
	@Autowired
	private PurchaseService purchaseService;
	
	/**
	 * 创建采购入库单
	 * @param purchaseInputOrderDTO 采购入库单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean createPurchaseInputOrder(PurchaseInputOrderDTO purchaseInputOrder) {
		try {
			purchaseInputOrderService.save(purchaseInputOrder); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 创建销售出库单
	 * @param saleDeliveryOrderDTO 销售出库单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean createSaleDeliveryOrder(SaleDeliveryOrderDTO saleDeliveryOrder) {
		try {
			saleDeliveryOrderService.save(saleDeliveryOrder); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 创建退货入库单
	 * @param returnGoodsInputOrder 退货入库单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean createReturnGoodsInputOrder(ReturnGoodsInputOrderDTO returnGoodsInputOrder) {
		try {
			returnGoodsInputOrderService.save(returnGoodsInputOrder); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知WMS中心，“提交订单”事件发生了
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean informSubmitOrderEvent(WmsSaleDeliveryScheduleResult scheduleResult) {
		try {
			WmsStockUpdater stockUpdater = stockUpdaterFactory.create(
					WmsStockUpdateEvent.SUBMIT_ORDER, scheduleResult);
			stockUpdater.update();
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知WMS中心，“支付订单”事件发生了
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean informPayOrderEvent(WmsSaleDeliveryScheduleResult scheduleResult) {
		try {
			WmsStockUpdater stockUpdater = stockUpdaterFactory.create(
					WmsStockUpdateEvent.PAY_ORDER, scheduleResult);
			stockUpdater.update();
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知WMS中心，“取消订单”事件发生了
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@Override
	public Boolean informCancelOrderEvent(WmsSaleDeliveryScheduleResult scheduleResult) {
		try {
			WmsStockUpdater stockUpdater = stockUpdaterFactory.create(
					WmsStockUpdateEvent.CANCEL_ORDER, scheduleResult);
			stockUpdater.update();
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 获取订单对应的物流单号 
	 * @param orderId 订单id
	 * @return 物流单号
	 */
	@Override
	public String getLogisticCode(Long orderId) {
		try {
			SaleDeliveryOrderDTO saleDeliveryOrder = saleDeliveryOrderService.getByOrderId(orderId);
			saleDeliveryOrder = saleDeliveryOrderService.getById(saleDeliveryOrder.getId());
			return saleDeliveryOrder.getLogisticOrder().getLogisticCode();
		} catch (Exception e) {
			logger.error("error", e);
			return null;
		}
	}
	
	/**
	 * 通知wms中心，“创建采购结算单”事件发生了
	 * @param purchaseInputOrderId 采购入库单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informCreatePurchaseSettlementOrderEvent(Long purchaseInputOrderId) {
		try {
			PurchaseInputOrderDTO purchaseInputOrder = purchaseInputOrderService.getById(
					purchaseInputOrderId);
			purchaseInputOrderService.updateStatus(purchaseInputOrderId, 
					PurchaseInputOrderStatus.WAIT_FOR_SETTLEMENT);
			purchaseService.informCreatePurchaseSettlementOrderEvent(
					purchaseInputOrder.getPurchaseOrderId());
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知wms中心，“完成采购结算单”事件发生了
	 * @param purchaseInputOrderId 采购入库单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informFinishedPurchaseSettlementOrderEvent(Long purchaseInputOrderId) {
		try {
			PurchaseInputOrderDTO purchaseInputOrder = purchaseInputOrderService.getById(
					purchaseInputOrderId);
			purchaseInputOrderService.updateStatus(purchaseInputOrderId, 
					PurchaseInputOrderStatus.FINISHED);
			purchaseService.informFinishedPurchaseSettlementOrderEvent(
					purchaseInputOrder.getPurchaseOrderId());
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
