package org.xianghao.xmall.inventory.stock;

import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.inventory.dao.GoodsStockDAO;
import org.xianghao.xmall.inventory.domain.GoodsStockDO;

import java.util.List;
import java.util.Map;

/**
 * 取消订单库存更新组件
 * @author xianghao
 *
 */
public class CancelOrderStockUpdater extends AbstractStockUpdater {

	/**
	 * 订单条目DTO对象集合
	 */
	private Map<Long, OrderItemDTO> orderItemDTOMap;
	
	/**
	 * 构造函数
	 * @param goodsStockDOs 商品库存DO对象集合
	 * @param goodsStockDAO 商品库存管理模块DAO组件 
	 * @param dateProvider 日期辅助组件
	 */
	public CancelOrderStockUpdater(
			List<GoodsStockDO> goodsStockDOs, 
			GoodsStockDAO goodsStockDAO,
			DateProvider dateProvider,
			Map<Long, OrderItemDTO> orderItemDTOMap) {
		super(goodsStockDOs, goodsStockDAO, dateProvider);
		this.orderItemDTOMap = orderItemDTOMap;
	}

	/**
	 * 更新销售库存
	 */
	@Override
	protected void updateSaleStockQuantity() throws Exception {
		for(GoodsStockDO goodsStockDO : goodsStockDOs) {
			OrderItemDTO orderItemDTO = orderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
			goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() 
					+ orderItemDTO.getPurchaseQuantity()); 
		}
	}
	
	/**
	 * 更新锁定库存
	 */
	@Override
	protected void updateLockedStockQuantity() throws Exception {
		for(GoodsStockDO goodsStockDO : goodsStockDOs) {
			OrderItemDTO orderItemDTO = orderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
			goodsStockDO.setLockedStockQuantity(goodsStockDO.getLockedStockQuantity() 
					- orderItemDTO.getPurchaseQuantity()); 
		}
	}
	
	/**
	 * 更新已销售库存
	 */
	@Override
	protected void updateSaledStockQuantity() throws Exception {
		
	}

}
