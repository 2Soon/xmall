package org.xianghao.xmall.promotion.dao.impl;

import org.xianghao.xmall.promotion.dao.CouponDAO;
import org.xianghao.xmall.promotion.domain.CouponDO;
import org.xianghao.xmall.promotion.domain.CouponQuery;
import org.xianghao.xmall.promotion.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class CouponDAOImpl implements CouponDAO {
	
	/**
	 * 优惠券管理mapper组件
	 */
	@Autowired
	private CouponMapper couponMapper;

	/**
	 * 分页查询优惠券
	 * @param query 查询条件
	 * @return 优惠券
	 */
	@Override
	public List<CouponDO> listByPage(CouponQuery query) {
		return couponMapper.listByPage(query);
	}
	
	/**
	 * 新增优惠券
	 * @param coupon 优惠券
	 */
	@Override
	public void save(CouponDO coupon) {
		couponMapper.save(coupon);
	}
	
	/**
	 * 根据id查询优惠券
	 * @param id 优惠券id
	 * @return 优惠券
	 */
	@Override
	public CouponDO getById(Long id) {
		return couponMapper.getById(id);
	}
	
	/**
	 * 更新优惠券
	 * @param coupon 优惠券
	 */
	@Override
	public void update(CouponDO coupon) {
		couponMapper.update(coupon); 
	}
	
	/**
	 * 删除优惠券
	 * @param id 优惠券id
	 */
	@Override
	public void remove(Long id) {
		couponMapper.remove(id);
	}
	
	/**
	 * 查询所有优惠券
	 * @return 所有优惠券
	 */
	@Override
	public List<CouponDO> listAll() {
		return couponMapper.listAll();
	}
	
	/**
	 * 更新优惠券状态
	 * @param coupon 优惠券
	 */
	@Override
	public void updateStatus(CouponDO coupon) {
		couponMapper.updateStatus(coupon); 
	}
	
}
