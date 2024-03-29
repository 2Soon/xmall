package org.xianghao.xmall.wms.domain;

import org.xianghao.xmall.common.util.AbstractObject;

import java.util.Date;

/**
 * 物流单
 * @author xianghao
 *
 */
public class LogisticOrderVO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 销售出库单id
	 */
	private Long saleDeliveryOrderId;
	/**
	 * 物流单号
	 */
	private String logisticCode;
	/**
	 * 物流单内容
	 */
	private String content;
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
	public Long getSaleDeliveryOrderId() {
		return saleDeliveryOrderId;
	}
	public void setSaleDeliveryOrderId(Long saleDeliveryOrderId) {
		this.saleDeliveryOrderId = saleDeliveryOrderId;
	}
	public String getLogisticCode() {
		return logisticCode;
	}
	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		return "LogisticOrderVO [id=" + id + ", saleDeliveryOrderId=" + saleDeliveryOrderId + ", logisticCode="
				+ logisticCode + ", content=" + content + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified
				+ "]";
	}
	
}
