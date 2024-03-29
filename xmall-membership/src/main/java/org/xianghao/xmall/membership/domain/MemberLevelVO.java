package org.xianghao.xmall.membership.domain;

import org.xianghao.xmall.common.util.AbstractObject;

import java.util.Date;

/**
 * 会员等级
 * @author xianghao
 *
 */
public class MemberLevelVO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 用户账号id
	 */
	private Long userAccountId;
	/**
	 * 成长值
	 */
	private Long growthValue;
	/**
	 * 会员等级
	 */
	private Integer level;
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
	public Long getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}
	public Long getGrowthValue() {
		return growthValue;
	}
	public void setGrowthValue(Long growthValue) {
		this.growthValue = growthValue;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
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
		return "MemberLevelVO [id=" + id + ", userAccountId=" + userAccountId + ", growthValue=" + growthValue
				+ ", level=" + level + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
	
}
