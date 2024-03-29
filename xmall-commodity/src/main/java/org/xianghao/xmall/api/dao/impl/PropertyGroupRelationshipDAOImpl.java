package org.xianghao.xmall.api.dao.impl;

import org.xianghao.xmall.api.dao.PropertyGroupRelationshipDAO;
import org.xianghao.xmall.api.domain.PropertyGroupRelationshipDO;
import org.xianghao.xmall.api.mapper.PropertyGroupRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性分组与属性关系管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class PropertyGroupRelationshipDAOImpl 
		implements PropertyGroupRelationshipDAO {
	
	/**
	 * 属性分组与属性关系管理mapper组件
	 */
	@Autowired
	private PropertyGroupRelationshipMapper propertyGroupRelationMapper;
	
	/**
	 * 新增属性分组与属性关系
	 * @param relation 属性分组与属性关系
	 */
	@Override
	public void save(PropertyGroupRelationshipDO relation) throws Exception {
		propertyGroupRelationMapper.save(relation); 
	}
	
	/**
	 * 根据属性分组id查询属性分组与属性的关联关系
	 * @param propertyGroupId 属性分组id
	 * @return 属性分组与属性的关联关系
	 */
	@Override
	public List<PropertyGroupRelationshipDO> listByPropertyGroupId(
			Long propertyGroupId) throws Exception {
		return propertyGroupRelationMapper.listByPropertyGroupId(propertyGroupId);
	}
	
	/**
	 * 根据属性分组id删除属性分组与属性的关联关系
	 * @param propertyGroupId 属性分组id
	 */ 
	@Override
	public void removeByPropertyGroupId(Long propertyGroupId) throws Exception {
		propertyGroupRelationMapper.removeByPropertyGroupId(propertyGroupId); 
	}

}
