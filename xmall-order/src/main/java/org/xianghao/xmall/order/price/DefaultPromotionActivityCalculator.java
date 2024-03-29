package org.xianghao.xmall.order.price;

import org.springframework.stereotype.Component;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.api.domain.promotion.PromotionActivityDTO;

/**
 * 没有促销活动的计算组件
 * @author xianghao
 *
 */
@Component
public class DefaultPromotionActivityCalculator implements PromotionActivityCalculator {
	
	/**
	 * 计算促销活动减免的金额
	 */
	@Override
	public PromotionActivityResult calculate(OrderItemDTO item,
											 PromotionActivityDTO promotionActivity) {
		return new PromotionActivityResult(); 
	}

}
