package org.xianghao.xmall.wms.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.wms.dao.GoodsAllocationStockDetailDAO;
import org.xianghao.xmall.wms.domain.GoodsAllocationStockDetailDO;
import org.xianghao.xmall.wms.mapper.GoodsAllocationStockDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 调度中心的货位库存明细管理的DAO组件
 * @author xianghao
 *
 */
@Repository
public class GoodsAllocationStockDetailDAOImpl implements GoodsAllocationStockDetailDAO {

	/**
	 * 调度中心的货位库存明细管理的mapper组件
	 */
	@Autowired
	private GoodsAllocationStockDetailMapper stockDetailMapper;
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
	public List<GoodsAllocationStockDetailDO> listByGoodsSkuId(
			Long goodsSkuId) throws Exception {
		return stockDetailMapper.listByGoodsSkuId(goodsSkuId);
	}
	
	/**
	 * 根据id查询货位库存明细
	 * @param id 货位库粗明细id
	 * @return 货位库存明细
	 */
	@Override
	public GoodsAllocationStockDetailDO getById(Long id) throws Exception {
		return stockDetailMapper.getById(id);
	}
	
	/**
	 * 更新货位库存明细 
	 * @param stockDetail 货位库存明细
	 * @throws Exception
	 */
	@Override
	public void update(GoodsAllocationStockDetailDO stockDetail) throws Exception {
		stockDetail.setGmtModified(dateProvider.getCurrentTime()); 
		stockDetailMapper.update(stockDetail); 
	}
	
	/**
	 * 新增货位库存明细
	 * @param stockDetail 货位库存明细
	 * @throws Exception
	 */
	@Override
	public void save(GoodsAllocationStockDetailDO stockDetail) throws Exception {
		stockDetail.setGmtCreate(dateProvider.getCurrentTime()); 
		stockDetail.setGmtModified(dateProvider.getCurrentTime());
		stockDetailMapper.save(stockDetail); 
	}
	
}
