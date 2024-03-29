package org.xianghao.xmall.promotion.domain;

import org.xianghao.xmall.common.util.AbstractObject;

import java.util.Date;

/**
 * 优惠券领取记录
 * @author xianghao
 *
 */
public class CouponAchieveDTO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 优惠券id
	 */
	private Long couponId;
	/**
	 * 用户账号id
	 */
	private Long userAccountId;
	/**
	 * 是否使用过这个优惠券
	 */
	private Integer used;
	/**
	 * 使用优惠券的时间
	 */
	private Date usedTime;
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
	public Long getCouponId() {
		return couponId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	public Long getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}
	public Integer getUsed() {
		return used;
	}
	public void setUsed(Integer used) {
		this.used = used;
	}
	public Date getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
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
		return "CouponAchieveDTO [id=" + id + ", couponId=" + couponId + ", userAccountId=" + userAccountId + ", used="
				+ used + ", usedTime=" + usedTime + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
	
}
