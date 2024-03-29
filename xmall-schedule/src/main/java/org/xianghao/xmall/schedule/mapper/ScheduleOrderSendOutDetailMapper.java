package org.xianghao.xmall.schedule.mapper;

import org.xianghao.xmall.schedule.domain.ScheduleOrderSendOutDetailDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 发货明细管理的mapper组件
 * @author xianghao
 *
 */
@Mapper
public interface ScheduleOrderSendOutDetailMapper {

	/**
	 * 新增发货明细
	 * @param sendOutDetail 发货明细
	 */
	@Insert("INSERT INTO schedule_order_send_out_detail("
				+ "order_info_id,"
				+ "order_item_id,"
				+ "goods_allocation_stock_detail_id,"
				+ "send_out_count," 
				+ "gmt_create,"
				+ "gmt_modified"
			+ ") VALUES("
				+ "#{orderInfoId},"
				+ "#{orderItemId},"
				+ "#{goodsAllocationStockDetailId},"
				+ "#{sendOutCount}," 
				+ "#{gmtCreate},"
				+ "#{gmtModified}"
			+ ")") 
	@Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
	void save(ScheduleOrderSendOutDetailDO sendOutDetail);
	
	/**
	 * 根据订单id和订单条目id查询发货明细
	 * @param orderInfoId 订单id
	 * @param orderItemId 订单条目id
	 * @return
	 */
	@Select("SELECT "
				+ "id,"
				+ "order_info_id,"
				+ "order_item_id,"
				+ "goods_allocation_stock_detail_id,"
				+ "send_out_count,"
				+ "gmt_create,"
				+ "gmt_modified "
			+ "FROM schedule_order_send_out_detail "
			+ "WHERE order_info_id=#{orderInfoId} "
			+ "AND order_item_id=#{orderItemId}") 
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "order_info_id", property = "orderInfoId"),
		@Result(column = "order_item_id", property = "orderItemId"),
		@Result(column = "goods_allocation_stock_detail_id", property = "goodsAllocationStockDetailId"),
		@Result(column = "send_out_count", property = "sendOutCount"),
		@Result(column = "gmt_create", property = "gmtCreate"),
		@Result(column = "gmt_modified", property = "gmtModified") 
	})
	List<ScheduleOrderSendOutDetailDO> listByOrderItemId(
			@Param("orderInfoId") Long orderInfoId, 
			@Param("orderItemId") Long orderItemId);
	
	/**
	 * 根据订单条目id删除发货明细
	 * @param orderInfoId 订单id
	 * @param orderItemId 订单条目id
	 */
	@Delete("DELETE FROM schedule_order_send_out_detail "
			+ "WHERE order_info_id=#{orderInfoId} "
			+ "AND order_item_id=#{orderItemId}")  
	void removeByOrderItemId(
			@Param("orderInfoId") Long orderInfoId, 
			@Param("orderItemId") Long orderItemId);
	
}
