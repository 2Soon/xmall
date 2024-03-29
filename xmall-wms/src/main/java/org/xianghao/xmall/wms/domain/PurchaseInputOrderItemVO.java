package org.xianghao.xmall.wms.domain;

import org.xianghao.xmall.common.util.AbstractObject;

import java.util.Date;
import java.util.List;

/**
 * 采购入库单条目
 * @author xianghao
 *
 */
public class PurchaseInputOrderItemVO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 采购入库单id
	 */
	private Long purchaseInputOrderId;
	/**
	 * 商品sku id
	 */
	private Long goodsSkuId;
	/**
	 * 商品sku的采购数量
	 */
	private Long purchaseCount;
	/**
	 * 商品sku的采购价格
	 */
	private Double purchasePrice;
	/**
	 * 商品sku到货后质检出来的合格商品数量
	 */
	private Long qualifiedCount;
	/**
	 * 商品sku实际到货的数量
	 */
	private Long arrivalCount;
	/**
	 * 采购入库单条目的创建时间
	 */
	private Date gmtCreate;
	/**
	 * 采购入库单条目的修改时间
	 */
	private Date gmtModified;
	/**
	 * 采购入库单商品上架条目集合
	 */
	private List<PurchaseInputOrderPutOnItemVO> putOnItemDTOs;
	/**
	 * 货位库存明细
	 */
	private List<GoodsAllocationStockDetailVO> stockDetails;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPurchaseInputOrderId() {
		return purchaseInputOrderId;
	}
	public void setPurchaseInputOrderId(Long purchaseInputOrderId) {
		this.purchaseInputOrderId = purchaseInputOrderId;
	}
	public Long getGoodsSkuId() {
		return goodsSkuId;
	}
	public void setGoodsSkuId(Long goodsSkuId) {
		this.goodsSkuId = goodsSkuId;
	}
	public Long getPurchaseCount() {
		return purchaseCount;
	}
	public void setPurchaseCount(Long purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Long getQualifiedCount() {
		return qualifiedCount;
	}
	public void setQualifiedCount(Long qualifiedCount) {
		this.qualifiedCount = qualifiedCount;
	}
	public Long getArrivalCount() {
		return arrivalCount;
	}
	public void setArrivalCount(Long arrivalCount) {
		this.arrivalCount = arrivalCount;
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
	public List<PurchaseInputOrderPutOnItemVO> getPutOnItemDTOs() {
		return putOnItemDTOs;
	}
	public void setPutOnItemDTOs(List<PurchaseInputOrderPutOnItemVO> putOnItemDTOs) {
		this.putOnItemDTOs = putOnItemDTOs;
	}
	public List<GoodsAllocationStockDetailVO> getStockDetails() {
		return stockDetails;
	}
	public void setStockDetails(List<GoodsAllocationStockDetailVO> stockDetails) {
		this.stockDetails = stockDetails;
	}
	
	@Override
	public String toString() {
		return "PurchaseInputOrderItemVO [id=" + id + ", purchaseInputOrderId=" + purchaseInputOrderId + ", goodsSkuId="
				+ goodsSkuId + ", purchaseCount=" + purchaseCount + ", purchasePrice=" + purchasePrice
				+ ", qualifiedCount=" + qualifiedCount + ", arrivalCount=" + arrivalCount + ", gmtCreate=" + gmtCreate
				+ ", gmtModified=" + gmtModified + ", putOnItemDTOs=" + putOnItemDTOs + ", stockDetails=" + stockDetails
				+ "]";
	}
	
}
