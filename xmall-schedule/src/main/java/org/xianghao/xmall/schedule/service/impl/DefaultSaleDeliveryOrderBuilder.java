package org.xianghao.xmall.schedule.service.impl;

import org.xianghao.xmall.api.constant.wms.SaleDeliveryOrderStatus;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.api.domain.schedule.SaleDeliveryScheduleResult;
import org.xianghao.xmall.api.domain.wms.*;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.schedule.api.LogisticsService;
import org.xianghao.xmall.schedule.service.SaleDeliveryOrderBuilder;
import org.xianghao.xmall.schedule.service.SaleDeliveryScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认的销售出库单构造器
 * @author xianghao
 *
 */
@Component
@Scope("prototype") 
public class DefaultSaleDeliveryOrderBuilder implements SaleDeliveryOrderBuilder {

	/**
	 * 要构造的销售出库单
	 */
	private SaleDeliveryOrderDTO saleDeliveryOrder = new SaleDeliveryOrderDTO();
	
	/**
	 * 销售出库调度器
	 */
	@Autowired
	private SaleDeliveryScheduler saleDeliveryScheduler;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	/**
	 * 物流服务
	 */
	@Autowired
	private LogisticsService logisticsService;
	
	/**
	 * 设置订单相关的数据
	 * @param order 订单
	 * @return 销售出库单builder
	 */
	@Override
	public SaleDeliveryOrderBuilder setOrderRelatedData(
			OrderInfoDTO order) throws Exception {
		saleDeliveryOrder.setOrderId(order.getId()); 
		order.clone(saleDeliveryOrder);
		return this;
 	}
	
	/**
	 * 创建销售出库单条目
	 * @param orderItems 订单条目
	 * @return 销售出库单builder
	 */
	@Override
	public SaleDeliveryOrderBuilder createSaleDeliveryOrderItems(
			List<OrderItemDTO> orderItems) throws Exception {
		List<SaleDeliveryOrderItemDTO> saleDeliveryOrderItems =
				new ArrayList<SaleDeliveryOrderItemDTO>();
		
		for(OrderItemDTO orderItem : orderItems) {
			SaleDeliveryScheduleResult scheduleResult = saleDeliveryScheduler
					.getScheduleResult(orderItem);
			
			SaleDeliveryOrderItemDTO saleDeliveryOrderItem = 
					orderItem.clone(SaleDeliveryOrderItemDTO.class);
			
			saleDeliveryOrderItem.setPickingItems(ObjectUtils.convertList(
					scheduleResult.getPickingItems(), SaleDeliveryOrderPickingItemDTO.class));
			saleDeliveryOrderItem.setSendOutItems(ObjectUtils.convertList(
					scheduleResult.getSendOutDetails(), SaleDeliveryOrderSendOutDetailDTO.class));
			
			saleDeliveryOrderItem.setGmtCreate(dateProvider.getCurrentTime());
			saleDeliveryOrderItem.setGmtModified(dateProvider.getCurrentTime());  
			
			saleDeliveryOrderItems.add(saleDeliveryOrderItem);
		}
		
		saleDeliveryOrder.setSaleDeliveryOrderItems(saleDeliveryOrderItems);  
		
		return this;
	}
	
	/**
	 * 创建发货单
	 * @param order 订单
	 * @return 销售出库单builder
	 */
	@Override
	public SaleDeliveryOrderBuilder createSendOutOrder(
			OrderInfoDTO order) throws Exception {
		SendOutOrderDTO sendOutOrder = new SendOutOrderDTO();
		sendOutOrder.setSendOutOrderItems(new ArrayList<SendOutOrderItemDTO>());  
		
		order.clone(sendOutOrder);
		sendOutOrder.setOrderId(order.getId());
		sendOutOrder.setGmtCreate(dateProvider.getCurrentTime()); 
		sendOutOrder.setGmtModified(dateProvider.getCurrentTime()); 
		
		for(OrderItemDTO orderItem : order.getOrderItems()) {
			SendOutOrderItemDTO sendOutOrderItem = orderItem.clone(SendOutOrderItemDTO.class);
			sendOutOrderItem.setGmtCreate(dateProvider.getCurrentTime()); 
			sendOutOrderItem.setGmtModified(dateProvider.getCurrentTime()); 
			sendOutOrder.getSendOutOrderItems().add(sendOutOrderItem);
		}

		saleDeliveryOrder.setSendOutOrder(sendOutOrder);
		
		return this;
	}
	
	/**
	 * 创建物流单
	 * @param order 订单
	 * @return 销售出库单builder
	 */
	@Override
	public SaleDeliveryOrderBuilder createLogisticOrder(
			OrderInfoDTO order) throws Exception {
		LogisticOrderDTO logisticOrder = logisticsService.applyLogisticOrder(order);
		saleDeliveryOrder.setLogisticOrder(logisticOrder); 
		return this;
	}
	
	/**
	 * 初始化销售出库单的状态
	 * @return 销售出库单builder
	 */
	@Override
	public SaleDeliveryOrderBuilder initStatus() throws Exception {
		saleDeliveryOrder.setStatus(SaleDeliveryOrderStatus.EDITING);
		return this;
	}
	
	/**
	 * 初始化时间相关的字段
	 * @return 销售出库单builder
	 */
	@Override
	public SaleDeliveryOrderBuilder initTimes() throws Exception {
		saleDeliveryOrder.setGmtCreate(dateProvider.getCurrentTime()); 
		saleDeliveryOrder.setGmtModified(dateProvider.getCurrentTime()); 
		return this;
	}
	
	/**
	 * 创建最终构造好的销售出库单
	 * @return 销售出库单
	 */
	@Override
	public SaleDeliveryOrderDTO create() throws Exception {
		return saleDeliveryOrder;
	}
	
}
