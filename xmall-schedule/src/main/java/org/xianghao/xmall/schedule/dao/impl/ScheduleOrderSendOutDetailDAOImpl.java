package org.xianghao.xmall.schedule.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.schedule.dao.ScheduleOrderSendOutDetailDAO;
import org.xianghao.xmall.schedule.domain.ScheduleOrderSendOutDetailDO;
import org.xianghao.xmall.schedule.mapper.ScheduleOrderSendOutDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 发货明细管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class ScheduleOrderSendOutDetailDAOImpl implements ScheduleOrderSendOutDetailDAO {

	/**
	 * 发货明细管理mapper组件
	 */
	@Autowired
	private ScheduleOrderSendOutDetailMapper sendOutDetailMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 批量新增发货明细
	 * @param orderItem 订单条目
	 * @param sendOutDetails 发货明细
	 * @throws Exception
	 */
	@Override
	public void batchSave(Long orderInfoId, Long orderItemId,
			List<ScheduleOrderSendOutDetailDO> sendOutDetails) throws Exception {
		for(ScheduleOrderSendOutDetailDO sendOutDetail : sendOutDetails) {
			sendOutDetail.setOrderInfoId(orderInfoId); 
			sendOutDetail.setOrderItemId(orderItemId); 
			sendOutDetail.setGmtCreate(dateProvider.getCurrentTime()); 
			sendOutDetail.setGmtModified(dateProvider.getCurrentTime());  
			sendOutDetailMapper.save(sendOutDetail);   
		}
	}
	
	/**
	 * 根据订单id和订单条目id查询发货明细
	 * @param orderInfoId 订单id
	 * @param orderItemId 订单条目id
	 * @return
	 */
	@Override
	public List<ScheduleOrderSendOutDetailDO> listByOrderItemId(Long orderInfoId, 
			Long orderItemId) throws Exception {
		return sendOutDetailMapper.listByOrderItemId(orderInfoId, orderItemId);
	}
	
	/**
	 * 根据订单条目id删除发货明细
	 * @param orderInfoId 订单id
	 * @param orderItemId 订单条目id
	 */
	@Override
	public void removeByOrderItemId(Long orderInfoId, 
			Long orderItemId) throws Exception {
		sendOutDetailMapper.removeByOrderItemId(orderInfoId, orderItemId);
	}
	
}
