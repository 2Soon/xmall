package org.xianghao.xmall.wms.stock;

import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.api.domain.schedule.SaleDeliveryScheduleResult;
import org.xianghao.xmall.api.domain.schedule.ScheduleOrderPickingItemDTO;
import org.xianghao.xmall.api.domain.schedule.ScheduleOrderSendOutDetailDTO;
import org.xianghao.xmall.wms.dao.GoodsAllocationStockDetailDAO;
import org.xianghao.xmall.wms.dao.WmsGoodsAllocationStockDAO;
import org.xianghao.xmall.wms.dao.WmsGoodsStockDAO;
import org.xianghao.xmall.wms.domain.GoodsAllocationStockDetailDO;
import org.xianghao.xmall.wms.domain.WmsGoodsAllocationStockDO;
import org.xianghao.xmall.wms.domain.WmsGoodsStockDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 支付订单库存更新组件
 * @author xianghao
 *
 */
@Component
@Scope("prototype") 
public class PayOrderWmsStockUpdater extends AbstractWmsStockUpdater {

	/**
	 * 调度结果
	 */
	private SaleDeliveryScheduleResult scheduleResult;
	
	/**
	 * 商品库存管理的DAO组件
	 */
	@Autowired
	private WmsGoodsStockDAO goodsStockDAO;
	/**
	 * 货位库存管理的DAO组件
	 */
	@Autowired
	private WmsGoodsAllocationStockDAO goodsAllocationStockDAO;
	/**
	 * 货位库存明细管理的DAO组件
	 */
	@Autowired
	private GoodsAllocationStockDetailDAO stockDetailDAO;
	
	/**
	 * 更新商品库存
	 */
	@Override
	protected void updateGoodsStock() throws Exception {
		OrderItemDTO orderItem = scheduleResult.getOrderItem();
		
		WmsGoodsStockDO goodsStock = goodsStockDAO.getBySkuId(orderItem.getGoodsSkuId());

		Long lockedStockQuantity = goodsStock.getLockedStockQuantity()
				- orderItem.getPurchaseQuantity();
		goodsStock.setLockedStockQuantity(lockedStockQuantity); 
		
		Long outputStockQuantity = goodsStock.getOutputStockQuantity()  
				+ orderItem.getPurchaseQuantity();
		goodsStock.setOutputStockQuantity(outputStockQuantity); 
		
		goodsStockDAO.update(goodsStock); 
	}

	/**
	 * 更新货位库存
	 */
	@Override
	protected void updateGoodsAllocationStock() throws Exception {
		List<ScheduleOrderPickingItemDTO> pickingItems = scheduleResult.getPickingItems();
		
		for(ScheduleOrderPickingItemDTO pickingItem : pickingItems) {
			WmsGoodsAllocationStockDO goodsAllocationStock = goodsAllocationStockDAO
					.getBySkuId(pickingItem.getGoodsAllocationId(), pickingItem.getGoodsSkuId());
			
			Long lockedStockQuantity = goodsAllocationStock.getLockedStockQuantity()
					- pickingItem.getPickingCount();
			goodsAllocationStock.setLockedStockQuantity(lockedStockQuantity); 
			
			Long outputStockQuantity = goodsAllocationStock.getOutputStockQuantity() 
					+ pickingItem.getPickingCount();
			goodsAllocationStock.setOutputStockQuantity(outputStockQuantity); 
			
			goodsAllocationStockDAO.update(goodsAllocationStock); 
		}
	}
	
	/**
	 * 更新货位库存明细
	 */
	@Override
	protected void updateGoodsAllocationStockDetail() throws Exception {
		List<ScheduleOrderSendOutDetailDTO> sendOutDetails = scheduleResult.getSendOutDetails();
		
		for(ScheduleOrderSendOutDetailDTO sendOutDetail : sendOutDetails) {
			GoodsAllocationStockDetailDO stockDetail = stockDetailDAO.getById(
					sendOutDetail.getGoodsAllocationStockDetailId());
			
			Long lockedStockQuantity = stockDetail.getLockedStockQuantity()
					- sendOutDetail.getSendOutCount();
			stockDetail.setLockedStockQuantity(lockedStockQuantity); 
			
			stockDetailDAO.update(stockDetail); 
		} 
 	}
	
	/**
	 * 设置需要的参数
	 */
	@Override
	public void setParameter(Object parameter) {
		this.scheduleResult = (SaleDeliveryScheduleResult) parameter; 
	}

}
