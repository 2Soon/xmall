package org.xianghao.xmall.wms.mapper;

import org.xianghao.xmall.wms.domain.SaleDeliveryOrderSendOutDetailDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 销售出库单发货明细管理mapper组件
 * @author xianghao
 *
 */
@Mapper
public interface SaleDeliveryOrderSendOutDetailMapper {

	/**
	 * 新增销售出库单发货明细
	 * @param sendOutDetail 销售出库单发货明细
	 */
	@Insert("INSERT INTO wms_sale_delivery_order_send_out_detail("
				+ "sale_delivery_order_item_id,"
				+ "goods_allocation_stock_detail_id,"
				+ "send_out_count,"
				+ "gmt_create,"
				+ "gmt_modified"
			+ ") VALUES("
				+ "#{saleDeliveryOrderItemId},"
				+ "#{goodsAllocationStockDetailId},"
				+ "#{sendOutCount},"
				+ "#{gmtCreate},"
				+ "#{gmtModified}"
			+ ")") 
	@Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
	void save(SaleDeliveryOrderSendOutDetailDO sendOutDetail);
	
	/**
	 * 根据销售出库单条目id查询发货明细
	 * @param saleDeliveryOrderItemId 销售出库单id
	 * @return 发货明细
	 */
	@Select("SELECT "
				+ "id,"
				+ "sale_delivery_order_item_id,"
				+ "goods_allocation_stock_detail_id,"
				+ "send_out_count,"
				+ "gmt_create,"
				+ "gmt_modified "
			+ "FROM wms_sale_delivery_order_send_out_detail "
			+ "WHERE sale_delivery_order_item_id=#{saleDeliveryOrderItemId}") 
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "sale_delivery_order_item_id", property = "saleDeliveryOrderItemId"),
		@Result(column = "goods_allocation_stock_detail_id", property = "goodsAllocationStockDetailId"),
		@Result(column = "send_out_count", property = "sendOutCount"),
		@Result(column = "gmt_create", property = "gmtCreate"),
		@Result(column = "gmt_modified", property = "gmtModified") 
	})
	List<SaleDeliveryOrderSendOutDetailDO> listBySaleDeliveryOrderItemId(
			@Param("saleDeliveryOrderItemId") Long saleDeliveryOrderItemId);
	
}
