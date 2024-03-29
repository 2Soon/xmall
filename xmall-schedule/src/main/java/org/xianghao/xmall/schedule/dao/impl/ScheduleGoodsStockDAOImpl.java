package org.xianghao.xmall.schedule.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.schedule.dao.ScheduleGoodsStockDAO;
import org.xianghao.xmall.schedule.domain.ScheduleGoodsStockDO;
import org.xianghao.xmall.schedule.mapper.ScheduleGoodsStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品库存管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class ScheduleGoodsStockDAOImpl implements ScheduleGoodsStockDAO {
	
	/**
	 * 商品库存管理mapper组件
	 */
	@Autowired
	private ScheduleGoodsStockMapper stockMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 根据商品sku id查询商品库存
	 * @param goodsSkuId 商品sku id
	 * @return 商品库存
	 */
	@Override
	public ScheduleGoodsStockDO getBySkuId(Long goodsSkuId) throws Exception {
		ScheduleGoodsStockDO goodsStock = stockMapper.getBySkuId(goodsSkuId) ;
		
		if(goodsStock == null) {
			goodsStock = new ScheduleGoodsStockDO();
			goodsStock.setGoodsSkuId(goodsSkuId); 
			goodsStock.setAvailableStockQuantity(0L); 
			goodsStock.setLockedStockQuantity(0L); 
			goodsStock.setOutputStockQuantity(0L); 
			save(goodsStock);  
		}
		
		return goodsStock;
	}
	
	/**
	 * 新增商品库存
	 * @param goodsStockDO 商品库存DO对象
	 */
	@Override
	public void save(ScheduleGoodsStockDO goodsStock) throws Exception {
		goodsStock.setGmtCreate(dateProvider.getCurrentTime()); 
		goodsStock.setGmtModified(dateProvider.getCurrentTime()); 
		stockMapper.save(goodsStock); 
	}
	
	/**
	 * 更新商品库存
	 * @param goodsStockDO 商品库存DO对象
	 */
	@Override
	public void update(ScheduleGoodsStockDO goodsStock) throws Exception {
		goodsStock.setGmtModified(dateProvider.getCurrentTime()); 
		stockMapper.update(goodsStock); 
	}

}
