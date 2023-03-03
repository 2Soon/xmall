package org.xianghao.xmall.promotion.dao;

import org.xianghao.xmall.promotion.domain.CouponAchieveDO;

import java.util.List;

/**
 * 优惠券领取记录管理DAO接口
 * @author xianghao
 *
 */
public interface CouponAchieveDAO {
	
	/**
	 * 根据优惠券id和用户账号id查询优惠券的领取记录
	 * @param couponId 优惠券id 
	 * @param userAccountId 用户账号id
	 * @return 优惠券领取记录
	 */
	CouponAchieveDO getByUserAccountId(Long couponId, Long userAccountId);
	
	/**
	 * 新增优惠券领取记录
	 * @param couponAchieve 优惠券领取记录
	 */
	void save(CouponAchieveDO couponAchieve);
	
	/**
	 * 查询用户还没有使用过的优惠券领取记录
	 * @param userAccountId 用户账号id
	 * @return 优惠券领取记录
	 */
	List<CouponAchieveDO> listUnsedByUserAccountId(Long userAccountId);
	
	/**
	 * 更新优惠券领取记录
	 * @param couponAchieve 优惠券领取记录
	 */
	void update(CouponAchieveDO couponAchieve);
	
}
