package org.xianghao.xmall.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.promotion.CouponDTO;
import org.xianghao.xmall.common.json.JsonExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 满减券计算组件
 * @author xianghao
 *
 */
@Component
public class ReachDiscountCouponCalculator implements CouponCalculator {

	@Autowired
	private JsonExtractor jsonExtractor;
	
	@Override
	public Double calculate(OrderInfoDTO order, CouponDTO coupon) throws Exception {
		Double payableAmount = order.getPayableAmount();
		
		JSONArray rules = JSONArray.parseArray(coupon.getRule());

		for(int i = 0; i < rules.size(); i++) {
			JSONObject rule = rules.getJSONObject(i);
		
			Double thresholdAmount = jsonExtractor.getDouble(rule, "thresholdAmount"); 
			Double reduceAmount = jsonExtractor.getDouble(rule, "reduceAmount"); 
			
			if(payableAmount > thresholdAmount) {
				return reduceAmount;
			}
		}
		
		return 0.0;
	}

}
