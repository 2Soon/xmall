package org.xianghao.xmall.promotion.dao;

import org.xianghao.xmall.promotion.domain.PromotionActivityGoodsRelationDO;

import java.util.List;

/**
 * 促销活动与商品关联关系管理的DAO组件接口
 * @author xianghao
 *
 */
public interface PromotionActivityGoodsRelationDAO {

	/**
	 * 根据促销活动id查询促销活动与商品的关联关系
	 * @param promotionActivityId 促销活动id
	 * @return 促销活动与商品的关联关系
	 */
	List<PromotionActivityGoodsRelationDO> listByActivityId(
			Long promotionActivityId);
	
	/**
	 * 新增促销活动与商品的关联关系
	 * @param relation 促销活动与商品的关联关系
	 */
	void save(PromotionActivityGoodsRelationDO relation);
	
	/**
	 * 删除促销活动对应的与商品的关联关系
	 * @param promotionActivityId 促销活动id
	 */
	void removeByActivityId(Long promotionActivityId);
	
}
