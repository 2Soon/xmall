package org.xianghao.xmall.schedule.domain;

import org.xianghao.xmall.common.util.AbstractObject;

import java.util.Date;

/**
 * 发货明细
 * @author xianghao
 *
 */
public class ScheduleOrderSendOutDetailDO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 订单id
	 */
	private Long orderInfoId;
	/**
	 * 订单条目id
	 */
	private Long orderItemId;
	/**
	 * 货位库存明细id
	 */
	private Long goodsAllocationStockDetailId;
	/**
	 * 发货数量
	 */
	private Long sendOutCount;
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
	public Long getOrderInfoId() {
		return orderInfoId;
	}
	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
	public Long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Long getGoodsAllocationStockDetailId() {
		return goodsAllocationStockDetailId;
	}
	public void setGoodsAllocationStockDetailId(Long goodsAllocationStockDetailId) {
		this.goodsAllocationStockDetailId = goodsAllocationStockDetailId;
	}
	public Long getSendOutCount() {
		return sendOutCount;
	}
	public void setSendOutCount(Long sendOutCount) {
		this.sendOutCount = sendOutCount;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
		result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
		result = prime * result
				+ ((goodsAllocationStockDetailId == null) ? 0 : goodsAllocationStockDetailId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderInfoId == null) ? 0 : orderInfoId.hashCode());
		result = prime * result + ((orderItemId == null) ? 0 : orderItemId.hashCode());
		result = prime * result + ((sendOutCount == null) ? 0 : sendOutCount.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ScheduleOrderSendOutDetailDO other = (ScheduleOrderSendOutDetailDO) obj;
		if (gmtCreate == null) {
			if (other.gmtCreate != null) {
				return false;
			}
		} else if (!gmtCreate.equals(other.gmtCreate)) {
			return false;
		}
		if (gmtModified == null) {
			if (other.gmtModified != null) {
				return false;
			}
		} else if (!gmtModified.equals(other.gmtModified)) {
			return false;
		}
		if (goodsAllocationStockDetailId == null) {
			if (other.goodsAllocationStockDetailId != null) {
				return false;
			}
		} else if (!goodsAllocationStockDetailId.equals(other.goodsAllocationStockDetailId)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (orderInfoId == null) {
			if (other.orderInfoId != null) {
				return false;
			}
		} else if (!orderInfoId.equals(other.orderInfoId)) {
			return false;
		}
		if (orderItemId == null) {
			if (other.orderItemId != null) {
				return false;
			}
		} else if (!orderItemId.equals(other.orderItemId)) {
			return false;
		}
		if (sendOutCount == null) {
			if (other.sendOutCount != null) {
				return false;
			}
		} else if (!sendOutCount.equals(other.sendOutCount)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "ScheduleOrderSendOutDetailDO [id=" + id + ", orderInfoId=" + orderInfoId + ", orderItemId="
				+ orderItemId + ", goodsAllocationStockDetailId=" + goodsAllocationStockDetailId + ", sendOutCount="
				+ sendOutCount + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
	
}
