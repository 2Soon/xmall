package org.xianghao.xmall.customer.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.customer.dao.ReturnGoodsWorksheetDAO;
import org.xianghao.xmall.customer.domain.ReturnGoodsWorksheetDO;
import org.xianghao.xmall.customer.domain.ReturnGoodsWorksheetQuery;
import org.xianghao.xmall.customer.mapper.ReturnGoodsWorksheetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 退货工单管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class ReturnGoodsWorksheetDAOImpl implements ReturnGoodsWorksheetDAO {

	/**
	 * 退货工单管理mapper组件
	 */
	@Autowired
	private ReturnGoodsWorksheetMapper returnGoodsWorksheetMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 新增退货工单
	 * @param worksheet 退货工单
	 */
	@Override
	public void save(ReturnGoodsWorksheetDO worksheet) throws Exception {
		worksheet.setGmtCreate(dateProvider.getCurrentTime()); 
		worksheet.setGmtModified(dateProvider.getCurrentTime()); 
		returnGoodsWorksheetMapper.save(worksheet); 
	}
	
	/**
	 * 分页查询退货工单
	 * @param query 查询条件
	 * @return 退货工单
	 */
	@Override
	public List<ReturnGoodsWorksheetDO> listByPage(
			ReturnGoodsWorksheetQuery query) throws Exception {
		return returnGoodsWorksheetMapper.listByPage(query);
	}
	
	/**
	 * 根据id查询退货工单
	 * @param id 退货工单id
	 * @return 退货工单
	 */
	@Override
	public ReturnGoodsWorksheetDO getById(Long id) throws Exception {
		return returnGoodsWorksheetMapper.getById(id);
	}
	
	/**
	 * 更新退货工单的状态
	 * @param worksheet 退货工单
	 */ 
	@Override
	public void updateStatus(ReturnGoodsWorksheetDO worksheet) throws Exception {
		worksheet.setGmtModified(dateProvider.getCurrentTime()); 
		returnGoodsWorksheetMapper.updateStatus(worksheet); 
	}
	
	/**
	 * 更新退货工单的退货物流单号
	 * @param worksheet 退货工单
	 */ 
	@Override
	public void updateReturnGoodsLogisticsCode(
			ReturnGoodsWorksheetDO worksheet) throws Exception {
		worksheet.setGmtModified(dateProvider.getCurrentTime()); 
		returnGoodsWorksheetMapper.updateReturnGoodsLogisticsCode(worksheet); 
	}
	
	/**
	 * 根据订单id查询退货工单
	 * @param id 订单id
	 * @return 退货工单
	 */
	@Override
	public ReturnGoodsWorksheetDO getByOrderInfoId(Long orderInfoId) throws Exception {
		return returnGoodsWorksheetMapper.getByOrderInfoId(orderInfoId);
	}
	
}
