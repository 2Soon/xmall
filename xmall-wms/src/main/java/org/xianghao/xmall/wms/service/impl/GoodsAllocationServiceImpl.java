package org.xianghao.xmall.wms.service.impl;

import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.wms.dao.GoodsAllocationDAO;
import org.xianghao.xmall.wms.domain.GoodsAllocationDO;
import org.xianghao.xmall.wms.domain.GoodsAllocationDTO;
import org.xianghao.xmall.wms.domain.GoodsAllocationQuery;
import org.xianghao.xmall.wms.service.GoodsAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 货位管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsAllocationServiceImpl implements GoodsAllocationService {

	/**
	 * 货位管理DAO组件
	 */
	@Autowired
	private GoodsAllocationDAO goodsAllocationDAO;
	
	/**
	 * 分页查询货位
	 * @param query 查询条件
	 * @return 货位
	 */
	@Override
	public List<GoodsAllocationDTO> listByPage(GoodsAllocationQuery query) throws Exception {
		return ObjectUtils.convertList(
				goodsAllocationDAO.listByPage(query), 
				GoodsAllocationDTO.class); 
	}
	
	/**
	 * 新增货位
	 * @param goodsAllocation 货位
	 */
	@Override
	public void save(GoodsAllocationDTO goodsAllocation) throws Exception {
		goodsAllocationDAO.save(goodsAllocation.clone(GoodsAllocationDO.class));  
	}
	
	/**
	 * 根据id查询货位
	 * @param id 货位id
	 * @return 货位
	 */
	@Override
	public GoodsAllocationDTO getById(Long id) throws Exception {
		return goodsAllocationDAO.getById(id).clone(GoodsAllocationDTO.class); 
	}
	
	/**
	 * 更新货位
	 * @param goodsAllocation 货位
	 */
	@Override
	public void update(GoodsAllocationDTO goodsAllocation) throws Exception {
		goodsAllocationDAO.update(goodsAllocation.clone(GoodsAllocationDO.class));  
	}
	
}
