package org.xianghao.xmall.order.price;

import org.xianghao.xmall.api.domain.order.OrderItemDTO;

/**
 * 商品条目总金额计算器
 * @author xianghao
 *
 */
public interface TotalPriceCalculator {

	/**
	 * 计算订单条目总金额
	 * @param item 订单条目 
	 * @return 订单条目总金额
	 */
	Double calculate(OrderItemDTO item);
	
}
