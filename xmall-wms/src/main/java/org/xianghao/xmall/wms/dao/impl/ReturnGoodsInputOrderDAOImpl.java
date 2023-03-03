package org.xianghao.xmall.wms.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.wms.dao.ReturnGoodsInputOrderDAO;
import org.xianghao.xmall.wms.domain.ReturnGoodsInputOrderDO;
import org.xianghao.xmall.wms.domain.ReturnGoodsInputOrderQuery;
import org.xianghao.xmall.wms.mapper.ReturnGoodsInputOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 退货入库单管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class ReturnGoodsInputOrderDAOImpl implements ReturnGoodsInputOrderDAO {

	/**
	 * 退货入库单管理mapper组件
	 */
	@Autowired
	private ReturnGoodsInputOrderMapper returnGoodsInputOrderMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 新增退货入库单
	 * @param returnGoodsInputOrder 退货入库单
	 */
	@Override
	public Long save(ReturnGoodsInputOrderDO returnGoodsInputOrder) throws Exception {
		returnGoodsInputOrderMapper.save(returnGoodsInputOrder); 
		return returnGoodsInputOrder.getId();
	}
	
	/**
	 * 分页查询退货入库单
	 * @param query 查询条件
	 * @return 退货入库单
	 */
	@Override
	public List<ReturnGoodsInputOrderDO> listByPage(
			ReturnGoodsInputOrderQuery query) throws Exception {
		return returnGoodsInputOrderMapper.listByPage(query);
	}
	
	/**
	 * 根据id查询退后入库单
	 * @param id 退货入库单id
	 * @return 退后入库单
	 */
	@Override
	public ReturnGoodsInputOrderDO getById(Long id) throws Exception {
		return returnGoodsInputOrderMapper.getById(id);
	}
	
	/**
	 * 更新退货入库单
	 * @param returnGoodsInputOrder 退货入库单
	 */
	@Override
	public void update(ReturnGoodsInputOrderDO returnGoodsInputOrder) throws Exception {
		returnGoodsInputOrder.setGmtModified(dateProvider.getCurrentTime());
		returnGoodsInputOrderMapper.update(returnGoodsInputOrder); 
	}
	
	/**
	 * 更新退货入库单的状态
	 * @param id 退货入库单id 
	 * @param status 退货入库单状态
	 * @throws Exception
	 */
	@Override
	public void updateStatus(Long id, Integer status) throws Exception {
		ReturnGoodsInputOrderDO returnGoodsInputOrder = getById(id);
		returnGoodsInputOrder.setStatus(status); 
		update(returnGoodsInputOrder);
	}
	 
}
