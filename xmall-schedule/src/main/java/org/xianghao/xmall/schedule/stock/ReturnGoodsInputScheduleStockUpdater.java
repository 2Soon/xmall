package org.xianghao.xmall.schedule.stock;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.api.domain.schedule.SaleDeliveryScheduleResult;
import org.xianghao.xmall.api.domain.schedule.ScheduleOrderPickingItemDTO;
import org.xianghao.xmall.api.domain.wms.GoodsAllocationStockDetailDTO;
import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderDTO;
import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderItemDTO;
import org.xianghao.xmall.schedule.api.OrderService;
import org.xianghao.xmall.schedule.dao.ScheduleGoodsAllocationStockDAO;
import org.xianghao.xmall.schedule.dao.ScheduleGoodsAllocationStockDetailDAO;
import org.xianghao.xmall.schedule.dao.ScheduleGoodsStockDAO;
import org.xianghao.xmall.schedule.domain.*;
import org.xianghao.xmall.schedule.service.SaleDeliveryScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 退货入库
 * @author xianghao
 *
 */
@Component
@Scope("prototype")  
public class ReturnGoodsInputScheduleStockUpdater extends AbstractScheduleStockUpdater {

	/**
	 * 退货入库单
	 */
	private ReturnGoodsInputOrderDTO returnGoodsInputOrder;
	
	/**
	 * 商品库存管理的DAO组件
	 */
	@Autowired
	private ScheduleGoodsStockDAO goodsStockDAO;
	/**
	 * 货位库存管理的DAO组件
	 */
	@Autowired
	private ScheduleGoodsAllocationStockDAO goodsAllocationStockDAO;
	/**
	 * 货位库存明细管理的DAO组件
	 */
	@Autowired
	private ScheduleGoodsAllocationStockDetailDAO stockDetailDAO;
	/**
	 * 销售出库调度器
	 */
	@Autowired
	private SaleDeliveryScheduler saleDeliveryScheduler;
	/**
	 * 订单服务
	 */
	@Autowired
	private OrderService orderService;
	
	/**
	 * 更新商品库存
	 */
	@Override
	protected void updateGoodsStock() throws Exception {
		List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItems =
				returnGoodsInputOrder.getItems();
		for(ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItem : returnGoodsInputOrderItems) {
			ScheduleGoodsStockDO goodsStock = goodsStockDAO.getBySkuId(
					returnGoodsInputOrderItem.getGoodsSkuId());
			goodsStock.setAvailableStockQuantity(goodsStock.getAvailableStockQuantity()
					+ returnGoodsInputOrderItem.getArrivalCount()); 
			goodsStock.setOutputStockQuantity(goodsStock.getOutputStockQuantity()
					- returnGoodsInputOrderItem.getArrivalCount());
			goodsStockDAO.update(goodsStock); 
		}
	}

	/**
	 * 更新货位库存
	 */
	@Override
	protected void updateGoodsAllocationStock() throws Exception {
		List<ReturnGoodsInputOrderItemDTO> items = returnGoodsInputOrder.getItems();
		
		OrderInfoDTO order = orderService.getOrderById(returnGoodsInputOrder.getOrderId());
		
		for(ReturnGoodsInputOrderItemDTO item : items) {
			OrderItemDTO targetOrderItem = null;
			
			for(OrderItemDTO orderItem : order.getOrderItems()) {
				if(orderItem.getGoodsSkuId().equals(item.getGoodsSkuId())) {
					targetOrderItem = orderItem;
					break;
				}
			}
			
			SaleDeliveryScheduleResult scheduleResult = saleDeliveryScheduler.getScheduleResult(targetOrderItem);
			
			for(ScheduleOrderPickingItemDTO pickingItem : scheduleResult.getPickingItems()) {
				ScheduleGoodsAllocationStockDO goodsAllocationStock = goodsAllocationStockDAO.getBySkuId(
						pickingItem.getGoodsAllocationId(), pickingItem.getGoodsSkuId());
				goodsAllocationStock.setAvailableStockQuantity(goodsAllocationStock.getAvailableStockQuantity() 
						+ pickingItem.getPickingCount());
				goodsAllocationStock.setOutputStockQuantity(goodsAllocationStock.getOutputStockQuantity()
						- pickingItem.getPickingCount()); 
				goodsAllocationStockDAO.update(goodsAllocationStock); 
			}
		}
	}
	
	/**
	 * 更新货位库存明细
	 */
	@Override
	protected void updateGoodsAllocationStockDetail() throws Exception {
		List<ReturnGoodsInputOrderItemDTO> items = returnGoodsInputOrder.getItems();
		
		for(ReturnGoodsInputOrderItemDTO item : items) {
			List<GoodsAllocationStockDetailDTO> stockDetails = item.getStockDetails();
			for(GoodsAllocationStockDetailDTO stockDetail : stockDetails) {
				stockDetailDAO.save(stockDetail.clone(ScheduleGoodsAllocationStockDetailDO.class));  
			}
		}
 	}
	
	/**
	 * 设置需要的参数
	 */
	@Override
	public void setParameter(Object parameter) {
		this.returnGoodsInputOrder = (ReturnGoodsInputOrderDTO) parameter;
	}
	
}
