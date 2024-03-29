package org.xianghao.xmall.wms.mapper;

import org.xianghao.xmall.wms.domain.GoodsAllocationDO;
import org.xianghao.xmall.wms.domain.GoodsAllocationQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 货位管理mapper组件
 * @author xianghao
 *
 */
@Mapper
public interface GoodsAllocationMapper {

	/**
	 * 分页查询货位
	 * @param query 查询条件
	 * @return 货位
	 */
	@Select("<script>"
			
			+ "SELECT "
				+ "a.id,"
				+ "a.code,"
				+ "a.remark,"
				+ "a.gmt_create,"
				+ "a.gmt_modified "
			+ "FROM wms_goods_allocation a,"
			+ "("
				+ "SELECT id "
				+ "FROM wms_goods_allocation "
				+ "WHERE 1=1 "
				
				+ "<if test='code != null'>"
				+ "AND code like '${code}%'" 
 				+ "</if>"
				
 				+ "LIMIT #{offset},#{size} " 
  			+ ") b "
			+ "WHERE a.id=b.id"
  			
			+ "</script>")
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "code", property = "code"),
		@Result(column = "remark", property = "remark"),
		@Result(column = "gmt_create", property = "gmtCreate"),
		@Result(column = "gmt_modified", property = "gmtModified") 
	})
	List<GoodsAllocationDO> listByPage(GoodsAllocationQuery query);
	
	/**
	 * 新增货位
	 * @param goodsAllocation 货位
	 */
	@Insert("INSERT INTO wms_goods_allocation("
				+ "code,"
				+ "remark,"
				+ "gmt_create,"
				+ "gmt_modified"
			+ ") VALUES("
				+ "#{code},"
				+ "#{remark},"
				+ "#{gmtCreate},"
				+ "#{gmtModified}"
			+ ")")
	@Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
	void save(GoodsAllocationDO goodsAllocation);
	
	/**
	 * 根据id查询货位
	 * @param id 货位id
	 * @return 货位
	 */
	@Select("SELECT "
				+ "id,"
				+ "code,"
				+ "remark,"
				+ "gmt_create,"
				+ "gmt_modified "
			+ "FROM wms_goods_allocation "
			+ "WHERE id=#{id}") 
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "code", property = "code"),
		@Result(column = "remark", property = "remark"),
		@Result(column = "gmt_create", property = "gmtCreate"),
		@Result(column = "gmt_modified", property = "gmtModified") 
	})
	GoodsAllocationDO getById(@Param("id") Long id);
	
	/**
	 * 更新货位
	 * @param goodsAllocation 货位
	 */
	@Update("UPDATE wms_goods_allocation SET "
				+ "code=#{code},"
				+ "remark=#{remark},"
				+ "gmt_modified=#{gmtModified} "
			+ "WHERE id=#{id}")   
	void update(GoodsAllocationDO goodsAllocation);
	
}
