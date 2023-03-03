package org.xianghao.xmall.schedule.stock;

import org.xianghao.xmall.api.domain.wms.GoodsAllocationStockDetailDTO;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderItemDTO;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderPutOnItemDTO;
import org.xianghao.xmall.schedule.dao.ScheduleGoodsAllocationStockDAO;
import org.xianghao.xmall.schedule.dao.ScheduleGoodsAllocationStockDetailDAO;
import org.xianghao.xmall.schedule.dao.ScheduleGoodsStockDAO;
import org.xianghao.xmall.schedule.domain.ScheduleGoodsAllocationStockDO;
import org.xianghao.xmall.schedule.domain.ScheduleGoodsAllocationStockDetailDO;
import org.xianghao.xmall.schedule.domain.ScheduleGoodsStockDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 采购入库库存更新组件
 * @author xianghao
 *
 */
@Component
@Scope("prototype") 
public class PurchaseInputScheduleStockUpdater extends AbstractScheduleStockUpdater {

	/**
	 * 采购入库单
	 */
	private PurchaseInputOrderDTO purchaseInputOrder;
	
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
	 * 更新商品库存
	 */
	@Override
	protected void updateGoodsStock() throws Exception {
		List<PurchaseInputOrderItemDTO> purchaseInputOrderItems =
				purchaseInputOrder.getItems();
		for(PurchaseInputOrderItemDTO purchaseInputOrderItem : purchaseInputOrderItems) {
			ScheduleGoodsStockDO goodsStock = goodsStockDAO.getBySkuId(
					purchaseInputOrderItem.getGoodsSkuId());
			goodsStock.setAvailableStockQuantity(goodsStock.getAvailableStockQuantity()
					+ purchaseInputOrderItem.getArrivalCount()); 
			goodsStockDAO.update(goodsStock); 
		}
	}

	/**
	 * 更新货位库存
	 */
	@Override
	protected void updateGoodsAllocationStock() throws Exception {
		List<PurchaseInputOrderItemDTO> items = purchaseInputOrder.getItems();
		
		for(PurchaseInputOrderItemDTO item : items) {
			List<PurchaseInputOrderPutOnItemDTO> putOnItems = item.getPutOnItemDTOs();
			
			for(PurchaseInputOrderPutOnItemDTO putOnItem : putOnItems) {
				ScheduleGoodsAllocationStockDO goodsAllocationStock = goodsAllocationStockDAO
						.getBySkuId(putOnItem.getGoodsAllocationId(), putOnItem.getGoodsSkuId());
				goodsAllocationStock.setAvailableStockQuantity(goodsAllocationStock.getAvailableStockQuantity() 
						+ putOnItem.getPutOnShelvesCount());
				goodsAllocationStockDAO.update(goodsAllocationStock); 
			}
		}
	}
	
	/**
	 * 更新货位库存明细
	 */
	@Override
	protected void updateGoodsAllocationStockDetail() throws Exception {
		List<PurchaseInputOrderItemDTO> items = purchaseInputOrder.getItems();
		
		for(PurchaseInputOrderItemDTO item : items) {
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
		this.purchaseInputOrder = (PurchaseInputOrderDTO) parameter;
	}

}
