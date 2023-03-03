package org.xianghao.xmall.wms.service;

import org.xianghao.xmall.wms.domain.GoodsAllocationDTO;
import org.xianghao.xmall.wms.domain.GoodsAllocationQuery;

import java.util.List;

/**
 * 货位管理service接口
 * @author xianghao
 *
 */
public interface GoodsAllocationService {

	/**
	 * 分页查询货位
	 * @param query 查询条件
	 * @return 货位
	 * @throws Exception
	 */
	List<GoodsAllocationDTO> listByPage(GoodsAllocationQuery query) throws Exception;
	
	/**
	 * 新增货位
	 * @param goodsAllocation 货位
	 * @throws Exception
	 */
	void save(GoodsAllocationDTO goodsAllocation) throws Exception;
	
	/**
	 * 根据id查询货位
	 * @param id 货位id
	 * @return 货位
	 * @throws Exception
	 */
	GoodsAllocationDTO getById(Long id) throws Exception;
	
	/**
	 * 更新货位
	 * @param goodsAllocation 货位
	 * @throws Exception
	 */
	void update(GoodsAllocationDTO goodsAllocation) throws Exception;
	
}
