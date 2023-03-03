package org.xianghao.xmall.order.price;

import org.xianghao.xmall.api.domain.promotion.PromotionActivityDTO;

/**
 * 订单价格计算组件的工厂接口
 * @author xianghao
 *
 */
public interface OrderPriceCalculatorFactory {

	/**
	 * 创建订单总金额价格计算组件
	 * @return 订单总金额价格计算组件
	 */
	TotalPriceCalculator createTotalPriceCalculator();
	
	/**
	 * 创建促销活动价格计算组件
	 * @param promotionActivity 促销活动
	 * @return 促销活动价格计算组件
	 */
	PromotionActivityCalculator createPromotionActivityCalculator(
			PromotionActivityDTO promotionActivity);
	
	/**
	 * 创建运费价格计算组件
	 * @return 运费价格计算组件
	 */
	FreightCalculator createFreightCalculator();
	
}
