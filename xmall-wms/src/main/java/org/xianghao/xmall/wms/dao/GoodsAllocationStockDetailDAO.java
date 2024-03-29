package org.xianghao.xmall.wms.dao;

import org.xianghao.xmall.wms.domain.GoodsAllocationStockDetailDO;

import java.util.List;

/**
 * 调度中心的货位库存明细管理的DAO接口
 * @author xianghao
 *
 */
public interface GoodsAllocationStockDetailDAO {

	/**
	 * 根据商品sku id查询货位库存明细
	 * @param goodsSkuId 商品sku id 
	 * @return 货位库存明细
	 * @throws Exception
	 */
	List<GoodsAllocationStockDetailDO> listByGoodsSkuId(
			Long goodsSkuId) throws Exception;
	
	/**
	 * 根据id查询货位库存明细
	 * @param id 货位库粗明细id
	 * @return 货位库存明细
	 * @throws Exception
	 */
	GoodsAllocationStockDetailDO getById(Long id) throws Exception;
	
	/**
	 * 更新货位库存明细 
	 * @param stockDetail 货位库存明细
	 * @throws Exception
	 */
	void update(GoodsAllocationStockDetailDO stockDetail) throws Exception;
	
	/**
	 * 新增货位库存明细
	 * @param stockDetail 货位库存明细
	 * @throws Exception
	 */
	void save(GoodsAllocationStockDetailDO stockDetail) throws Exception;
	
}
