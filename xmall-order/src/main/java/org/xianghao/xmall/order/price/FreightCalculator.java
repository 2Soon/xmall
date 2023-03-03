package org.xianghao.xmall.order.price;


import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;

/**
 * 运费计算组件接口
 * @author xianghao
 *
 */
public interface FreightCalculator {
	
	/**
	 * 计算运费
	 * @param order 订单
	 * @param orderItem 订单条目
	 * @param result 促销活动计算结果
	 * @return 运费
	 */
	Double calculate(OrderInfoDTO order, OrderItemDTO orderItem,
					 PromotionActivityResult result) throws Exception;

}
