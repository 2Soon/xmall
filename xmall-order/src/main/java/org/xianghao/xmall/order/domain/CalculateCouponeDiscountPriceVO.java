package org.xianghao.xmall.order.domain;

import org.xianghao.xmall.api.domain.promotion.CouponDTO;
import org.xianghao.xmall.common.util.AbstractObject;

/**
 * 计算优惠券抵扣金额的VO类
 * @author xianghao
 *
 */
public class CalculateCouponeDiscountPriceVO extends AbstractObject {

	/**
	 * 订单
	 */
	private OrderInfoVO order;
	/**
	 * 优惠券
	 */
	private CouponDTO coupon;
	
	public OrderInfoVO getOrder() {
		return order;
	}
	public void setOrder(OrderInfoVO order) {
		this.order = order;
	}
	public CouponDTO getCoupon() {
		return coupon;
	}
	public void setCoupon(CouponDTO coupon) {
		this.coupon = coupon;
	}
	
	@Override
	public String toString() {
		return "CalculateCouponeDiscountPriceVO [order=" + order + ", coupon=" + coupon + "]";
	}
	
}
