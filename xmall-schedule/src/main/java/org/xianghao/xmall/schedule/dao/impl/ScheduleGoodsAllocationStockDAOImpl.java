package org.xianghao.xmall.schedule.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.schedule.dao.ScheduleGoodsAllocationStockDAO;
import org.xianghao.xmall.schedule.domain.ScheduleGoodsAllocationStockDO;
import org.xianghao.xmall.schedule.mapper.ScheduleGoodsAllocationStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 货位库存管理dao组件
 * @author xianghao
 *
 */
@Repository
public class ScheduleGoodsAllocationStockDAOImpl implements ScheduleGoodsAllocationStockDAO {
	
	/**
	 * 货位库存管理mapper组件
	 */
	@Autowired
	private ScheduleGoodsAllocationStockMapper goodsAllocationStockMapper;
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
	public ScheduleGoodsAllocationStockDO getBySkuId(
			Long goodsAllocationId, Long goodsSkuId) throws Exception {
		ScheduleGoodsAllocationStockDO goodsAllocationStock =
				goodsAllocationStockMapper.getBySkuId(goodsAllocationId, goodsSkuId);
		
		if(goodsAllocationStock == null) {
			goodsAllocationStock = new ScheduleGoodsAllocationStockDO();
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
	public void save(ScheduleGoodsAllocationStockDO goodsAllocationStock) throws Exception {
		goodsAllocationStock.setGmtCreate(dateProvider.getCurrentTime()); 
		goodsAllocationStock.setGmtModified(dateProvider.getCurrentTime()); 
		goodsAllocationStockMapper.save(goodsAllocationStock); 
	}
	
	/**
	 * 更新货位库存
	 * @param goodsAllocationStockDO 货位库存DO对象
	 */
	@Override
	public void update(ScheduleGoodsAllocationStockDO goodsAllocationStock) throws Exception {
		goodsAllocationStock.setGmtModified(dateProvider.getCurrentTime()); 
		goodsAllocationStockMapper.update(goodsAllocationStock); 
	}

}
