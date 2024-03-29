package org.xianghao.xmall.order.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.order.constant.ReturnGoodsApplyStatus;
import org.xianghao.xmall.order.dao.ReturnGoodsApplyDAO;
import org.xianghao.xmall.order.domain.ReturnGoodsApplyDO;
import org.xianghao.xmall.order.mapper.ReturnGoodsApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 退货申请管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class ReturnGoodsApplyDAOImpl implements ReturnGoodsApplyDAO {

	/**
	 * 退货申请管理mapper组件
	 */
	@Autowired
	private ReturnGoodsApplyMapper returnGoodsApplyMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 新增退货申请
	 * @param apply 退货申请
	 */
	@Override
	public void save(ReturnGoodsApplyDO apply) throws Exception {
		apply.setReturnGoodsApplyStatus(ReturnGoodsApplyStatus.WAIT_FOR_APPROVE); 
		apply.setGmtCreate(dateProvider.getCurrentTime()); 
		apply.setGmtModified(dateProvider.getCurrentTime()); 
		returnGoodsApplyMapper.save(apply); 
	}
	
	/**
	 * 根据id查询退货申请
	 * @param id 退货申请id
	 * @return 退货申请
	 */
	@Override
	public ReturnGoodsApplyDO getByOrderInfoId(Long orderInfoId) throws Exception {
		return returnGoodsApplyMapper.getByOrderInfoId(orderInfoId);
	}
	
	/**
	 * 更新退货申请的状态
	 * @param apply 退货申请
	 */
	@Override
	public void update(ReturnGoodsApplyDO apply) throws Exception {
		apply.setGmtModified(dateProvider.getCurrentTime()); 
		returnGoodsApplyMapper.update(apply); 
	}
	
	/**
	 * 更新退货申请的状态
	 * @param orderInfoId 订单id
	 * @param returnGoodsApplyStatus 退货申请状态
	 * @throws Exception
	 */
	@Override
	public void updateStatus(Long orderInfoId, Integer returnGoodsApplyStatus) throws Exception {
		ReturnGoodsApplyDO apply = getByOrderInfoId(orderInfoId);
		apply.setReturnGoodsApplyStatus(returnGoodsApplyStatus); 
		returnGoodsApplyMapper.update(apply); 
	}
	
}
