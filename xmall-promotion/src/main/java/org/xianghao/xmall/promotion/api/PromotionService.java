package org.xianghao.xmall.promotion.api;

import org.xianghao.xmall.api.api.promotion.PromotionApi;
import org.xianghao.xmall.api.constant.promotion.CouponStatus;
import org.xianghao.xmall.api.domain.promotion.CouponDTO;
import org.xianghao.xmall.api.domain.promotion.PromotionActivityDTO;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.promotion.dao.CouponAchieveDAO;
import org.xianghao.xmall.promotion.dao.CouponDAO;
import org.xianghao.xmall.promotion.dao.PromotionActivityDAO;
import org.xianghao.xmall.promotion.domain.CouponAchieveDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 促销中心service组件
 * @author xianghao
 *
 */
@RestController
public class PromotionService implements PromotionApi {
	
	private static final Logger logger = LoggerFactory.getLogger(PromotionService.class);
	
	/**
	 * 促销活动管理DAO组件
	 */
	@Autowired
	private PromotionActivityDAO promotionActivityDAO;
	/**
	 * 优惠券领取记录管理DAO组件
	 */
	@Autowired
	private CouponAchieveDAO couponAchieveDAO;
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
	 * 根据商品id查询促销活动
	 * @param goodsId 商品id
	 * @return 促销活动
	 */
	@Override
	public List<PromotionActivityDTO> listByGoodsId(Long goodsId) {
		try {
			return ObjectUtils.convertList(promotionActivityDAO.listEnabledByGoodsId(goodsId), 
					PromotionActivityDTO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return new ArrayList<PromotionActivityDTO>();
		}
	}
	
	/**
	 * 根据id查询促销活动
	 * @param id 促销活动id
	 * @return 促销活动
	 */
	@Override
	public PromotionActivityDTO getById(Long id) {
		try {
			return promotionActivityDAO.getById(id).clone(PromotionActivityDTO.class); 
		} catch(Exception e) {
			logger.error("Error", e); 
			return null;
		}
	}
	
	/**
	 * 查询用户当前可以使用的有效优惠券
	 * @param userAccountId 用户账号id
	 * @return 有效优惠券
	 */
	@Override
	public List<CouponDTO> listValidByUserAccountId(Long userAccountId) {
		List<CouponDTO> coupons = new ArrayList<CouponDTO>();
		
		try {
			List<CouponAchieveDO> couponAchieves = couponAchieveDAO
					.listUnsedByUserAccountId(userAccountId);
			for(CouponAchieveDO couponAchieve : couponAchieves) {
				CouponDTO coupon = couponDAO.getById(couponAchieve.getCouponId())
						.clone(CouponDTO.class);
				if(CouponStatus.GIVING_OUT.equals(coupon.getStatus()) ||
						CouponStatus.GIVEN_OUT.equals(coupon.getStatus())) {
					coupons.add(coupon);
				}
			}
		} catch (Exception e) {
			logger.error("error", e); 
		}
		
		return coupons;
	}
	
	/**
	 * 使用优惠券
	 * @param couponId 优惠券id
	 * @param userAccountId 用户账号id
	 * @return 处理结果
	 */
	@Override
	public Boolean useCoupon(Long couponId, Long userAccountId) {
		try {
			CouponAchieveDO couponAchieve = new CouponAchieveDO();
			couponAchieve.setCouponId(couponId); 
			couponAchieve.setUserAccountId(userAccountId); 
			couponAchieve.setUsed(1); 
			couponAchieve.setUsedTime(dateProvider.getCurrentTime()); 
			couponAchieve.setGmtModified(dateProvider.getCurrentTime()); 
			
			couponAchieveDAO.update(couponAchieve); 
			
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}

}
