package org.xianghao.xmall.schedule.service;

import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.api.domain.schedule.SaleDeliveryScheduleResult;

/**
 * 销售出库调度器接口
 * @author xianghao
 *
 */
public interface SaleDeliveryScheduler {
	
	/**
	 * 调度销售出库
	 * @param orderItem 订单条目
	 * @return 调度结果
	 * @throws Exception
	 */
	SaleDeliveryScheduleResult schedule(OrderItemDTO orderItem) throws Exception;
	
	/**
	 * 获取订单条目的调度结果
	 * @param orderItem 订单条目
	 * @return 调度结果
	 * @throws Exception
	 */
	SaleDeliveryScheduleResult getScheduleResult(OrderItemDTO orderItem) throws Exception;

}
