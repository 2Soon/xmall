package org.xianghao.xmall.promotion.service.impl;

import org.xianghao.xmall.api.constant.promotion.CouponStatus;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.promotion.dao.CouponDAO;
import org.xianghao.xmall.promotion.domain.CouponDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 优惠券状态检查任务
 * @author xianghao
 *
 */
@Component
public class CouponStatusCheckTask {

	private static final Logger logger = LoggerFactory.getLogger(
			CouponStatusCheckTask.class);
	
	/**
	 * 优惠券管理DAO组件
	 */
	@Autowired
	private CouponDAO couponDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 执行任务
	 */
	@Scheduled(fixedRate = 1 * 60 * 1000)
	public void execute() {
		try {
			List<CouponDO> coupons = couponDAO.listAll(); 
			for(CouponDO coupon : coupons) {
				if(CouponStatus.UNSTARTED.equals(coupon.getStatus())) {
					tryStart(coupon); 
				} else if(CouponStatus.GIVING_OUT.equals(coupon.getStatus()) || 
						CouponStatus.GIVEN_OUT.equals(coupon.getStatus())) {
					tryExpire(coupon); 
				}
			}
		} catch (Exception e) {
			logger.error("error", e); 
		}
	}
	
	/**
	 * 尝试开始优惠券
	 * @param coupon 优惠券
	 * @throws Exception
	 */
	private void tryStart(CouponDO coupon) throws Exception {
		if(dateProvider.getCurrentTime().after(coupon.getValidStartTime())) {
			coupon.setStatus(CouponStatus.GIVING_OUT);  
			coupon.setGmtModified(dateProvider.getCurrentTime()); 
			couponDAO.updateStatus(coupon); 
		}
	}
	
	/**
	 * 尝试过期优惠券
	 * @param coupon 优惠券
	 * @throws Exception
	 */
	private void tryExpire(CouponDO coupon) throws Exception {
		if(dateProvider.getCurrentTime().after(coupon.getValidEndTime())) {
 			coupon.setStatus(CouponStatus.EXPIRED);
			coupon.setGmtModified(dateProvider.getCurrentTime()); 
			couponDAO.updateStatus(coupon); 
		}
	}
	
}
