package org.xianghao.xmall.wms.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.wms.dao.WmsGoodsAllocationStockDAO;
import org.xianghao.xmall.wms.domain.WmsGoodsAllocationStockDO;
import org.xianghao.xmall.wms.mapper.WmsGoodsAllocationStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 货位库存管理dao组件
 * @author xianghao
 *
 */
@Repository
public class WmsGoodsAllocationStockDAOImpl implements WmsGoodsAllocationStockDAO {
	
	/**
	 * 货位库存管理mapper组件
	 */
	@Autowired
	private WmsGoodsAllocationStockMapper goodsAllocationStockMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 根据商品sku id查询货位库存
	 * @param goodsSkuId 商品sku id
	 * @return 货位库存
	 */
	@Override
	public WmsGoodsAllocationStockDO getBySkuId(
			Long goodsAllocationId, Long goodsSkuId) throws Exception {
		WmsGoodsAllocationStockDO goodsAllocationStock =
				goodsAllocationStockMapper.getBySkuId(goodsAllocationId, goodsSkuId);
		
		if(goodsAllocationStock == null) {
			goodsAllocationStock = new WmsGoodsAllocationStockDO();
			goodsAllocationStock.setGoodsAllocationId(goodsAllocationId); 
			goodsAllocationStock.setGoodsSkuId(goodsSkuId); 
			goodsAllocationStock.setAvailableStockQuantity(0L);
			goodsAllocationStock.setLockedStockQuantity(0L); 
			goodsAllocationStock.setOutputStockQuantity(0L); 
			save(goodsAllocationStock); 
		}
		
		return goodsAllocationStock;
	}
	
	/**
	 * 新增货位库存
	 * @param goodsAllocationStockDO 货位库存DO对象
	 */
	@Override
	public void save(WmsGoodsAllocationStockDO goodsAllocationStock) throws Exception {
		goodsAllocationStock.setGmtCreate(dateProvider.getCurrentTime()); 
		goodsAllocationStock.setGmtModified(dateProvider.getCurrentTime()); 
		goodsAllocationStockMapper.save(goodsAllocationStock); 
	}
	
	/**
	 * 更新货位库存
	 * @param goodsAllocationStockDO 货位库存DO对象
	 */
	@Override
	public void update(WmsGoodsAllocationStockDO goodsAllocationStock) throws Exception {
		goodsAllocationStock.setGmtModified(dateProvider.getCurrentTime()); 
		goodsAllocationStockMapper.update(goodsAllocationStock); 
	}

}
