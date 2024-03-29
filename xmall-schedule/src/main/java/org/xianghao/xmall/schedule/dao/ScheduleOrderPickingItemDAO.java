package org.xianghao.xmall.schedule.dao;

import org.xianghao.xmall.schedule.domain.ScheduleOrderPickingItemDO;

import java.util.List;

/**
 * 拣货条目管理DAO接口
 * @author xianghao
 *
 */
public interface ScheduleOrderPickingItemDAO {

	/**
	 * 批量插入拣货条目
	 * @param orderInfoId 订单id
	 * @param orderItemId 订单条目id
	 * @param pickingItems 拣货条目
	 * @throws Exception
	 */
	void batchSave(Long orderInfoId, Long orderItemId, 
			List<ScheduleOrderPickingItemDO> pickingItems) throws Exception;
	
	/**
	 * 根据订单id和订单条目id查询拣货条目
	 * @param orderInfoId 订单id
	 * @param orderItemId 订单条目id
	 * @return 拣货条目
	 * @throws Exception
	 */
	List<ScheduleOrderPickingItemDO> listByOrderItemId(
			Long orderInfoId, Long orderItemId) throws Exception;
	
	/**
	 * 根据订单条目id删除拣货条目
	 * @param orderInfoId 订单id
	 * @param orderItemId 订单条目id
	 * @throws Exception
	 */
	void removeByOrderItemId(Long orderInfoId, Long orderItemId) throws Exception;
	
}
