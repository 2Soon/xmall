package org.xianghao.xmall.order.price;

import com.alibaba.fastjson.JSONObject;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.api.domain.promotion.PromotionActivityDTO;
import org.xianghao.xmall.common.json.JsonExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 单品促销活动的价格计算组件
 * @author xianghao
 *
 */
@Component
public class DirectDiscountPromotionActivityCalcualtor implements PromotionActivityCalculator {

	@Autowired
	private JsonExtractor jsonExtractor;
	
	/**
	 * 计算促销活动的减免金额
	 */
	@Override
	public PromotionActivityResult calculate(OrderItemDTO item,
											 PromotionActivityDTO promotionActivity) throws Exception {
		BigDecimal totalAmount = BigDecimal.valueOf(item.getPurchaseQuantity() * item.getPurchasePrice());  
		
		JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
		BigDecimal discountRate = BigDecimal.valueOf(jsonExtractor.getDouble(rule, "discountRate"));   
		 
		BigDecimal discountAmountRate = BigDecimal.valueOf(1.0).subtract(discountRate);
		
		Double discountAmount = totalAmount.multiply(discountAmountRate).doubleValue();
		
		return new PromotionActivityResult(discountAmount);      
	}
	
}
