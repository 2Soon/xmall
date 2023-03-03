package org.xianghao.xmall.order.price;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.order.api.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认的运费计算组件
 * @author xianghao
 *
 */
@Component
public class DefaultFreightCalculator implements FreightCalculator {

	/**
	 * 物流服务
	 */
	@Autowired
	private LogisticsService logisticsService;
	
	/**
	 * 计算运费
	 */
	@Override
	public Double calculate(OrderInfoDTO order, OrderItemDTO orderItem,
							PromotionActivityResult result) throws Exception {
		List<OrderItemDTO> orderItems = new ArrayList<OrderItemDTO>();
		orderItems.add(orderItem);
		order.setOrderItems(orderItems); 
		
		return logisticsService.calculateFreight(order); 
	}
	
}
