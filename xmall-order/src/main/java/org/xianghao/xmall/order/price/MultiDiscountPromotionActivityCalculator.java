package org.xianghao.xmall.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.api.domain.promotion.PromotionActivityDTO;
import org.xianghao.xmall.common.json.JsonExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 多买优惠促销活动价格计算组件
 * @author xianghao
 *
 */
@Component
public class MultiDiscountPromotionActivityCalculator implements PromotionActivityCalculator {

	@Autowired
	private JsonExtractor jsonExtractor;
	
	/**
	 * 计算促销活动的减免金额
	 */
	@Override
	public PromotionActivityResult calculate(OrderItemDTO item,
											 PromotionActivityDTO promotionActivity) throws Exception {
		Double totalAmount = item.getPurchaseQuantity() * item.getPurchasePrice();
		Long purchaseCount = item.getPurchaseQuantity();
		
		String rulesJson = promotionActivity.getRule();
		
		JSONArray rules = JSONArray.parseArray(rulesJson);
		
		for(int i = 0; i < rules.size(); i++) {
			JSONObject rule = rules.getJSONObject(i);
		
			Long thresholdCount = jsonExtractor.getLong(rule, "thresholdCount"); 
			Double discountRate = jsonExtractor.getDouble(rule, "discountRate"); 
			
			if(purchaseCount > thresholdCount) {
				return new PromotionActivityResult(totalAmount * (1.0 - discountRate));  
			}
		}
		
		return new PromotionActivityResult(); 
	}

}
