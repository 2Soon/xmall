package org.xianghao.xmall.promotion.domain;

import org.xianghao.xmall.common.util.AbstractObject;

import java.util.Date;

/**
 * 促销活动和商品的关联关系
 * @author xianghao
 *
 */
public class PromotionActivityGoodsRelationDO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 促销活动id
	 */
	private Long promotionActivityId;
	/**
	 * 商品id
	 */
	private Long goodsId;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPromotionActivityId() {
		return promotionActivityId;
	}
	public void setPromotionActivityId(Long promotionActivityId) {
		this.promotionActivityId = promotionActivityId;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	@Override
	public String toString() {
		return "PromotionActivityGoodsRelationDO [id=" + id + ", promotionActivityId=" + promotionActivityId
				+ ", goodsId=" + goodsId + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
	
}
