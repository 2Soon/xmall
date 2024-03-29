package org.xianghao.xmall.inventory.stock;

import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderItemDTO;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.inventory.dao.GoodsStockDAO;
import org.xianghao.xmall.inventory.domain.GoodsStockDO;

import java.util.List;
import java.util.Map;

/**
 * 退货入库
 * @author xianghao
 *
 */
public class ReturnGoodsInputStockUpdater extends AbstractStockUpdater {

	/**
	 * 退货入库单条目DTO集合
	 */
	private Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap;
	
	/**
	 * 构造函数
	 * @param goodsStockDOs 商品库存DO集合
	 * @param goodsStockDAO 商品库存管理模块的DAO组件
	 * @param dateProvider 日期辅助组件
	 * @param returnGoodsInputOrderItemDTOMap 退货入库单条目DTO集合
	 */
	public ReturnGoodsInputStockUpdater(
			List<GoodsStockDO> goodsStockDOs, 
			GoodsStockDAO goodsStockDAO,
			DateProvider dateProvider,
			Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap) {
		super(goodsStockDOs, goodsStockDAO, dateProvider);
		this.returnGoodsInputOrderItemDTOMap = returnGoodsInputOrderItemDTOMap;
	}

	/**
	 * 更新销售库存
	 */
	@Override
	protected void updateSaleStockQuantity() throws Exception {
		for(GoodsStockDO goodsStockDO : goodsStockDOs) {
			ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = 
					returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
			goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() 
					+ returnGoodsInputOrderItemDTO.getArrivalCount()); 
		}
	}

	/**
	 * 更新锁定库存
	 */
	@Override
	protected void updateLockedStockQuantity() throws Exception {
		
	}

	/**
	 * 更新已销售库存
	 */
	@Override
	protected void updateSaledStockQuantity() throws Exception {
		for(GoodsStockDO goodsStockDO : goodsStockDOs) {
			ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = 
					returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
			goodsStockDO.setSaledStockQuantity(goodsStockDO.getSaledStockQuantity() 
					- returnGoodsInputOrderItemDTO.getArrivalCount()); 
		}
	}
	
}
