package org.xianghao.xmall.api.domain.wms;

import org.xianghao.xmall.api.domain.order.OrderItemDTO;

import java.util.List;

/**
 * 调度销售出库的结果
 * @author xianghao
 *
 */
public class WmsSaleDeliveryScheduleResult {

	/**
	 * 订单条目
	 */
	private OrderItemDTO orderItem;
	/**
	 * 拣货条目
	 */
	private List<WmsScheduleOrderPickingItemDTO> pickingItems;
	/**
	 * 发货明细
	 */
	private List<WmsScheduleOrderSendOutDetailDTO> sendOutDetails;
	
	public OrderItemDTO getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItemDTO orderItem) {
		this.orderItem = orderItem;
	}
	public List<WmsScheduleOrderPickingItemDTO> getPickingItems() {
		return pickingItems;
	}
	public void setPickingItems(List<WmsScheduleOrderPickingItemDTO> pickingItems) {
		this.pickingItems = pickingItems;
	}
	public List<WmsScheduleOrderSendOutDetailDTO> getSendOutDetails() {
		return sendOutDetails;
	}
	public void setSendOutDetails(List<WmsScheduleOrderSendOutDetailDTO> sendOutDetails) {
		this.sendOutDetails = sendOutDetails;
	}
	
}
