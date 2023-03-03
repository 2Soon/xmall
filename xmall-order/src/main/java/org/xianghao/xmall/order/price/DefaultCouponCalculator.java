package org.xianghao.xmall.order.price;

import org.springframework.stereotype.Component;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.promotion.CouponDTO;

/**
 * 默认的优惠券计算组件
 * @author xianghao
 *
 */
@Component
public class DefaultCouponCalculator implements CouponCalculator {

	@Override
	public Double calculate(OrderInfoDTO order, CouponDTO coupon) {
		return 0.0;
	}

}
