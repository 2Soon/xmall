package org.xianghao.xmall.api.dao;

import org.xianghao.xmall.api.domain.PropertyGroupRelationshipDO;

import java.util.List;

/**
 * 属性分组与属性关系管理DAO组件接口
 * @author xianghao
 *
 */
public interface PropertyGroupRelationshipDAO {

	/**
	 * 新增属性分组与属性关系
	 * @param relation 属性分组与属性关系
	 * @throws Exception
	 */
	void save(PropertyGroupRelationshipDO relation) throws Exception;
	
	/**
	 * 根据属性分组id查询属性分组与属性的关联关系
	 * @param propertyGroupId 属性分组id
	 * @return 属性分组与属性的关联关系
	 * @throws Exception
	 */
	List<PropertyGroupRelationshipDO> listByPropertyGroupId(Long propertyGroupId) throws Exception;
	
	/**
	 * 根据属性分组id删除属性分组与属性的关联关系
	 * @param propertyGroupId 属性分组id
	 * @throws Exception
	 */ 
	void removeByPropertyGroupId(Long propertyGroupId) throws Exception;
	
}
