package org.xianghao.xmall.order.price;

import com.alibaba.fastjson.JSONObject;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.promotion.CouponDTO;
import org.xianghao.xmall.common.json.JsonExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 现金券抵扣金额计算组件
 * @author xianghao
 *
 */
@Component
public class CashCouponCalculator implements CouponCalculator {
	
	@Autowired
	private JsonExtractor jsonExtractor;

	@Override
	public Double calculate(OrderInfoDTO order, CouponDTO coupon) throws Exception {
		JSONObject rule = JSONObject.parseObject(coupon.getRule());
		Double discountAmount = jsonExtractor.getDouble(rule, "discountAmount"); 
		Double payableAmount = order.getPayableAmount();
		
		if(discountAmount > payableAmount) {
			return payableAmount;
		}
		
		return discountAmount;
	}

}
