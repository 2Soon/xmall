package org.xianghao.xmall.schedule.dao;

import org.xianghao.xmall.schedule.domain.ScheduleOrderSendOutDetailDO;

import java.util.List;

/**
 * 发货明细管理DAO接口
 * @author xianghao
 *
 */
public interface ScheduleOrderSendOutDetailDAO {

	/**
	 * 批量新增发货明细
	 * @param orderInfoId 订单id
	 * @param orderItemId 订单条目id
	 * @param sendOutDetails 发货明细
	 * @throws Exception
	 */
	void batchSave(Long orderInfoId, Long orderItemId,
			List<ScheduleOrderSendOutDetailDO> sendOutDetails) throws Exception;
	
	/**
	 * 根据订单id和订单条目id查询发货明细
	 * @param orderInfoId 订单id
	 * @param orderItemId 订单条目id
	 * @return 发货明细
	 * @throws Exception
	 */
	List<ScheduleOrderSendOutDetailDO> listByOrderItemId(
			Long orderInfoId, Long orderItemId) throws Exception;
	
	/**
	 * 根据订单条目id删除发货明细
	 * @param orderInfoId 订单id
	 * @param orderItemId 订单条目id
	 * @throws Exception
	 */
	void removeByOrderItemId(Long orderInfoId, 
			Long orderItemId) throws Exception;
	
}
