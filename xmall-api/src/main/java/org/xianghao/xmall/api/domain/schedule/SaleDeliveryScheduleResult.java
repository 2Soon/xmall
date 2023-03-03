package org.xianghao.xmall.api.domain.schedule;

import org.xianghao.xmall.common.util.AbstractObject;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;

import java.util.List;

/**
 * 调度销售出库的结果
 * @author xianghao
 *
 */
public class SaleDeliveryScheduleResult extends AbstractObject {

	/**
	 * 订单条目
	 */
	private OrderItemDTO orderItem;
	/**
	 * 拣货条目
	 */
	private List<ScheduleOrderPickingItemDTO> pickingItems;
	/**
	 * 发货明细
	 */
	private List<ScheduleOrderSendOutDetailDTO> sendOutDetails;
	
	public OrderItemDTO getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItemDTO orderItem) {
		this.orderItem = orderItem;
	}
	public List<ScheduleOrderPickingItemDTO> getPickingItems() {
		return pickingItems;
	}
	public void setPickingItems(List<ScheduleOrderPickingItemDTO> pickingItems) {
		this.pickingItems = pickingItems;
	}
	public List<ScheduleOrderSendOutDetailDTO> getSendOutDetails() {
		return sendOutDetails;
	}
	public void setSendOutDetails(List<ScheduleOrderSendOutDetailDTO> sendOutDetails) {
		this.sendOutDetails = sendOutDetails;
	}
	
}
