package org.xianghao.xmall.api.mapper;

import org.xianghao.xmall.api.domain.CategoryDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 类目管理模块mapper组件
 * @author xianghao
 *
 */
@Mapper
public interface CategoryMapper {

	/**
	 * 查询根类目
	 * @return 根类目集合
	 */
	@Select("SELECT "
				+ "id,"
				+ "name,"
				+ "description,"
				+ "parent_id,"
				+ "is_leaf,"
				+ "gmt_create,"
				+ "gmt_modified "
			+ "FROM commodity_category "
			+ "WHERE parent_id IS NULL") 
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "name", property = "name"),
		@Result(column = "description", property = "description"),
		@Result(column = "parent_id", property = "parentId"),
		@Result(column = "is_leaf", property = "leaf"),
		@Result(column = "gmt_create", property = "gmtCreate"),
		@Result(column = "gmt_modified", property = "gmtModified") 
	})
 	List<CategoryDO> listRoots();
	
	/**
	 * 查询子类目
	 * @param id 父类目id
	 * @return 子类目集合
	 */
	@Select("SELECT "
			+ "id,"
			+ "name,"
			+ "description,"
			+ "parent_id,"
			+ "is_leaf,"
			+ "gmt_create,"
			+ "gmt_modified "
		+ "FROM commodity_category "
		+ "WHERE parent_id=#{id}")   
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "name", property = "name"),
		@Result(column = "description", property = "description"),
		@Result(column = "parent_id", property = "parentId"),
		@Result(column = "is_leaf", property = "leaf"),
		@Result(column = "gmt_create", property = "gmtCreate"),
		@Result(column = "gmt_modified", property = "gmtModified") 
	})
	List<CategoryDO> listChildren(@Param("id") Long id); 
	
	/**
	 * 根据id查询类目
	 * @param id 类目id
	 * @return 类目
	 */
	@Select("SELECT "
			+ "id,"
			+ "name,"
			+ "description,"
			+ "parent_id,"
			+ "is_leaf,"
			+ "gmt_create,"
			+ "gmt_modified "
		+ "FROM commodity_category "
		+ "WHERE id=#{id}")   
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "name", property = "name"),
		@Result(column = "description", property = "description"),
		@Result(column = "parent_id", property = "parentId"),
		@Result(column = "is_leaf", property = "leaf"),
		@Result(column = "gmt_create", property = "gmtCreate"),
		@Result(column = "gmt_modified", property = "gmtModified") 
	})
	CategoryDO getById(@Param("id") Long id);
	
	/**
	 * 新增类目
	 * @param category 类目
	 */
	@Insert("INSERT INTO commodity_category("
				+ "name,"
				+ "description,"
				+ "parent_id,"
				+ "is_leaf,"
				+ "gmt_create,"
				+ "gmt_modified"
			+ ") VALUES("
				+ "#{name},"
				+ "#{description},"
				+ "#{parentId},"
				+ "#{leaf},"
				+ "#{gmtCreate},"
				+ "#{gmtModified}"
			+ ")")  
	@Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true) 
	void save(CategoryDO category);
	
	/**
	 * 更新类目
	 * @param category 类目
	 */
	@Update("UPDATE commodity_category SET "
				+ "description=#{description},"
				+ "gmt_modified=#{gmtModified} "
			+ "WHERE id=#{id}") 
	void update(CategoryDO category);
	
	/**
	 * 删除类目
	 * @param id 类目id
	 */
	@Delete("DELETE FROM commodity_category WHERE id=#{id}")  
	void remove(@Param("id") Long id);
	
}
