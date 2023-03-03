package org.xianghao.xmall.order.price;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xianghao.xmall.api.constant.promotion.CouponType;
import org.xianghao.xmall.api.domain.promotion.CouponDTO;

/**
 * 优惠券计算组件工厂
 * @author xianghao
 *
 */
@Component
public class CouponCalculatorFactory {

	/**
	 * 现金券计算组件
	 */
	@Autowired
	private CashCouponCalculator cashCouponCalculator;
	/**
	 * 满减券计算组件
	 */
	@Autowired
	private ReachDiscountCouponCalculator reachDiscountCouponCalculator;
	/**
	 * 默认计算组件
	 */
	@Autowired
	private DefaultCouponCalculator defaultCouponCalculator;
	
	/**
	 * 创建一个优惠券计算组件
	 * @param coupon 优惠券
	 * @return 优惠券计算组件
	 */
	public CouponCalculator create(CouponDTO coupon) {
		if(CouponType.CASH_COUPON.equals(coupon.getType())) {
			return cashCouponCalculator;
		} else if(CouponType.REACH_DISCOUNT_COUPON.equals(coupon.getType())) {
			return reachDiscountCouponCalculator;
		}
		return defaultCouponCalculator;
	}
	
}
