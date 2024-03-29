package org.xianghao.xmall.schedule.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.schedule.dao.ScheduleGoodsAllocationStockDetailDAO;
import org.xianghao.xmall.schedule.domain.ScheduleGoodsAllocationStockDetailDO;
import org.xianghao.xmall.schedule.mapper.ScheduleGoodsAllocationStockDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 调度中心的货位库存明细管理的DAO组件
 * @author xianghao
 *
 */
@Repository
public class ScheduleGoodsAllocationStockDetailDAOImpl
		implements ScheduleGoodsAllocationStockDetailDAO {

	/**
	 * 调度中心的货位库存明细管理的mapper组件
	 */
	@Autowired
	private ScheduleGoodsAllocationStockDetailMapper stockDetailMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 根据商品sku id查询货位库存明细
	 * @param goodsSkuId 商品sku id 
	 * @return 货位库存明细
	 */
	@Override
	public List<ScheduleGoodsAllocationStockDetailDO> listByGoodsSkuId(
			Long goodsSkuId) throws Exception {
		return stockDetailMapper.listByGoodsSkuId(goodsSkuId);
	}
	
	/**
	 * 根据id查询货位库存明细
	 * @param id 货位库粗明细id
	 * @return 货位库存明细
	 */
	@Override
	public ScheduleGoodsAllocationStockDetailDO getById(Long id) throws Exception {
		return stockDetailMapper.getById(id);
	}
	
	/**
	 * 更新货位库存明细 
	 * @param stockDetail 货位库存明细
	 * @throws Exception
	 */
	@Override
	public void update(ScheduleGoodsAllocationStockDetailDO stockDetail) throws Exception {
		stockDetail.setGmtModified(dateProvider.getCurrentTime()); 
		stockDetailMapper.update(stockDetail); 
	}
	
	/**
	 * 新增货位库存明细
	 * @param stockDetail 货位库存明细
	 * @throws Exception
	 */
	@Override
	public void save(ScheduleGoodsAllocationStockDetailDO stockDetail) throws Exception {
		stockDetail.setGmtCreate(dateProvider.getCurrentTime()); 
		stockDetail.setGmtModified(dateProvider.getCurrentTime());
		stockDetailMapper.save(stockDetail); 
	}
	
}
