package org.xianghao.xmall.promotion.dao.impl;

import org.xianghao.xmall.promotion.dao.CouponAchieveDAO;
import org.xianghao.xmall.promotion.domain.CouponAchieveDO;
import org.xianghao.xmall.promotion.mapper.CouponAchieveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券领取记录管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class CouponAchieveDAOImpl implements CouponAchieveDAO {

	/**
	 * 优惠券领取记录管理mapper组件
	 */
	@Autowired
	private CouponAchieveMapper couponAchieveMapper;
	
	/**
	 * 根据优惠券id和用户账号id查询优惠券的领取记录
	 * @param couponId 优惠券id 
	 * @param userAccountId 用户账号id
	 * @return 优惠券领取记录
	 */
	@Override
	public CouponAchieveDO getByUserAccountId(Long couponId, Long userAccountId) {
		return couponAchieveMapper.getByUserAccountId(couponId, userAccountId);
	}
	
	/**
	 * 新增优惠券领取记录
	 * @param couponAchieve 优惠券领取记录
	 */
	@Override
	public void save(CouponAchieveDO couponAchieve) {
		couponAchieveMapper.save(couponAchieve); 
	}
	
	/**
	 * 查询用户还没有使用过的优惠券领取记录
	 * @param userAccountId 用户账号id
	 * @return 优惠券领取记录
	 */
	@Override
	public List<CouponAchieveDO> listUnsedByUserAccountId(Long userAccountId) {
		return couponAchieveMapper.listUnsedByUserAccountId(userAccountId);
	}
	
	/**
	 * 更新优惠券领取记录
	 * @param couponAchieve 优惠券领取记录
	 */
	@Override
	public void update(CouponAchieveDO couponAchieve) {
		couponAchieveMapper.update(couponAchieve); 
	}
	
}
