package org.xianghao.xmall.api.mapper;

import org.xianghao.xmall.api.domain.PropertyGroupRelationshipDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 属性分组与属性关系管理mapper组件
 * @author xianghao
 *
 */
@Mapper
public interface PropertyGroupRelationshipMapper {

	/**
	 * 新增属性分组与属性关系
	 * @param relation 属性分组与属性关系
	 */
	@Insert("INSERT INTO commodity_property_group_relationship("
				+ "property_group_id,"
				+ "property_id,"
				+ "is_required,"
				+ "property_types,"
				+ "gmt_create,"
				+ "gmt_modified"
			+ ") VALUES("
				+ "#{propertyGroupId},"  
				+ "#{propertyId},"
				+ "#{required},"
				+ "#{propertyTypes},"
				+ "#{gmtCreate},"
				+ "#{gmtModified}"
			+ ")") 
	@Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true) 
	void save(PropertyGroupRelationshipDO relation);
	
	/**
	 * 根据属性分组id查询属性分组与属性的关联关系
	 * @param propertyGroupId 属性分组id
	 * @return 属性分组与属性的关联关系
	 */
	@Select("SELECT "
				+ "id,"
				+ "property_group_id,"
				+ "property_id,"
				+ "is_required,"
				+ "property_types,"
				+ "gmt_create,"
				+ "gmt_modified "
			+ "FROM commodity_property_group_relationship "
			+ "WHERE property_group_id=#{propertyGroupId} ")  
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "property_group_id", property = "propertyGroupId"),
		@Result(column = "property_id", property = "propertyId"),
		@Result(column = "is_required", property = "required"),
		@Result(column = "property_types", property = "propertyTypes"),
		@Result(column = "gmt_create", property = "gmtCreate"),
		@Result(column = "gmt_modified", property = "gmtModified")
	})
	List<PropertyGroupRelationshipDO> listByPropertyGroupId(
			@Param("propertyGroupId") Long propertyGroupId);
	
	/**
	 * 根据属性分组id删除属性分组与属性的关联关系
	 * @param propertyGroupId 属性分组id
	 */
	@Delete("DELETE FROM commodity_property_group_relationship "
			+ "WHERE property_group_id=#{propertyGroupId}")  
	void removeByPropertyGroupId(@Param("propertyGroupId") Long propertyGroupId);
	
}
