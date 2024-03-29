package org.xianghao.xmall.api.domain.wms;

import org.xianghao.xmall.common.util.AbstractObject;

import java.util.Date;

/**
 * 销售出库单拣货条目
 * @author xianghao
 *
 */
public class SaleDeliveryOrderPickingItemDTO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 销售出库单条目id
	 */
	private Long saleDeliveryOrderItemId;
	/**
	 * 货位id
	 */
	private Long goodsAllocationId;
	/**
	 * 商品sku id
	 */
	private Long goodsSkuId;
	/**
	 * 拣货数量
	 */
	private Long pickingCount;
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
	public Long getSaleDeliveryOrderItemId() {
		return saleDeliveryOrderItemId;
	}
	public void setSaleDeliveryOrderItemId(Long saleDeliveryOrderItemId) {
		this.saleDeliveryOrderItemId = saleDeliveryOrderItemId;
	}
	public Long getGoodsAllocationId() {
		return goodsAllocationId;
	}
	public void setGoodsAllocationId(Long goodsAllocationId) {
		this.goodsAllocationId = goodsAllocationId;
	}
	public Long getPickingCount() {
		return pickingCount;
	}
	public void setPickingCount(Long pickingCount) {
		this.pickingCount = pickingCount;
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
	public Long getGoodsSkuId() {
		return goodsSkuId;
	}
	public void setGoodsSkuId(Long goodsSkuId) {
		this.goodsSkuId = goodsSkuId;
	}
	
	@Override
	public String toString() {
		return "SaleDeliveryOrderPickingItemDTO [id=" + id + ", saleDeliveryOrderItemId=" + saleDeliveryOrderItemId
				+ ", goodsAllocationId=" + goodsAllocationId + ", goodsSkuId=" + goodsSkuId + ", pickingCount="
				+ pickingCount + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
	
}
