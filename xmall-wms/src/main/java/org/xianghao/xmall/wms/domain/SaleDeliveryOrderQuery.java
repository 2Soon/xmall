package org.xianghao.xmall.wms.domain;

/**
 * 销售出库单查询条件
 * @author xianghao
 *
 */
public class SaleDeliveryOrderQuery {

	/**
	 * 分页查询起始位置
	 */
	private Integer offset;
	/**
	 * 每页显示的数据条数
	 */
	private Integer size;
	/**
	 * 销售出库单状态
	 */
	private Integer status;
	
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
