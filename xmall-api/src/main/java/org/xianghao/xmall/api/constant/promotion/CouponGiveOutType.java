package org.xianghao.xmall.api.constant.promotion;

/**
 * 优惠券发放类型
 * @author xianghao
 *
 */
public class CouponGiveOutType {

	/**
	 * 可领取可发放
	 */
	public static final Integer ACHIEVABLE_AND_GIVE_OUT = 1;
	/**
	 * 仅可发放
	 */
	public static final Integer ONLY_GIVE_OUT = 2;
	/**
	 * 仅可领取
	 */
	public static final Integer ONLY_ACHIEVABLE = 3;
	
	private CouponGiveOutType() {
		
	}
	
}
