package org.xianghao.xmall.api.service.impl;

import org.xianghao.xmall.api.dao.*;
import org.xianghao.xmall.api.domain.*;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询属性类目操作
 * @author xianghao
 *
 */
@Component
@Scope("prototype")  
public class QueryPropertyCategoryOperation implements CategoryOperation<Properties> {

	/**
	 * 最终结果
	 */
	private Properties properties = new Properties(); 
	
	/**
	 * 类目与属性关系管理DAO组件
	 */
	@Autowired
	private CategoryPropertyRelationshipDAO categoryPropertyRelationDAO;
	/**
	 * 属性管理DAO组件
	 */
	@Autowired
	private PropertyDAO propertyDAO;
	/**
	 * 属性分组管理DAO组件
	 */
	@Autowired
	private PropertyGroupDAO propertyGroupDAO;
	/**
	 * 属性分组与属性的关系管理DAO组件
	 */
	@Autowired
	private PropertyGroupRelationshipDAO propertyGroupRelationDAO;
	/**
	 * 类目管理DAO组件
	 */
	@Autowired
	private CategoryDAO categoryDAO;
	
	/**
	 * 执行操作
	 */
	@Override
	public Properties doExecute(Category category) throws Exception {
		List<CategoryPropertyRelationshipVO> relations = queryCategoryPropertyRelations(category); 
		queryProperties(relations); 
		queryPropertyGroups(category); 
		
		Category parentCategory = getParentCategory(category);
		if(parentCategory != null) {
			parentCategory.execute(this);
		}
		
		return properties;
	}
	
	/**
	 * 获取父类目
	 * @param category
	 * @return
	 * @throws Exception
	 */
	private Category getParentCategory(Category category) throws Exception {
		CategoryDO categoryDO = categoryDAO.getById(category.getCategoryId());
		CategoryDO parentCategoryDO = categoryDAO.getById(categoryDO.getParentId());
		
		if(parentCategoryDO != null) {
			return new Category(parentCategoryDO.getId());
		} else {
			return null;
		}
	}
	
	/**
	 * 查询类目与属性的关联关系
	 * @param category 类目
	 * @throws Exception
	 */
	private List<CategoryPropertyRelationshipVO> queryCategoryPropertyRelations(
			Category category) throws Exception {
		List<CategoryPropertyRelationshipDO> relationDOs = categoryPropertyRelationDAO
				.listByCategoryId(category.getCategoryId());
		List<CategoryPropertyRelationshipDTO> relationDTOs = ObjectUtils.convertList(
				relationDOs, CategoryPropertyRelationshipDTO.class);
		List<CategoryPropertyRelationshipVO> relationVOs = ObjectUtils.convertList(
				relationDTOs, CategoryPropertyRelationshipVO.class);
		properties.getPropertyRelations().addAll(relationVOs);
		return relationVOs;
	}
	
	/**
	 * 查询类目关联的属性
	 * @param category 类目
	 * @throws Exception
	 */
	private void queryProperties(List<CategoryPropertyRelationshipVO> relations) throws Exception {
		for(CategoryPropertyRelationshipVO relation : relations) { 
			PropertyDO propertyDO = propertyDAO.getPropertyById(relation.getPropertyId());
			PropertyVO propertyVO = propertyDO.clone(PropertyDTO.class).clone(PropertyVO.class);
			properties.getProperties().add(propertyVO);
		}
	}
	
	/**
	 * 查询属性分组
	 * @param category 类目
	 * @throws Exception
	 */
	private void queryPropertyGroups(Category category) throws Exception {
		List<PropertyGroupDO> propertyGroupDOs = propertyGroupDAO.listByCategoryId(
				category.getCategoryId());
		List<PropertyGroupDTO> propertyGroupDTOs = ObjectUtils.convertList(
				propertyGroupDOs, PropertyGroupDTO.class);
		List<PropertyGroupVO> propertyGroupVOs = ObjectUtils.convertList(
				propertyGroupDTOs, PropertyGroupVO.class);
		
		for(PropertyGroupVO propertyGroup : propertyGroupVOs) {
			propertyGroup.setRelations(queryPropertyGroupRelations(propertyGroup.getId()));  
			propertyGroup.setProperties(queryPropertiesForGroup(propertyGroup));  
		}
		
		properties.getPropertyGroups().addAll(propertyGroupVOs);
	}
	
	/**
	 * 查询属性分组与属性的关联关系
	 * @param category 类目
	 * @throws Exception
	 */
	private List<PropertyGroupRelationshipVO> queryPropertyGroupRelations(
			Long propertyGroupId) throws Exception {
		List<PropertyGroupRelationshipDO> relationDOs = propertyGroupRelationDAO
				.listByPropertyGroupId(propertyGroupId);
		List<PropertyGroupRelationshipDTO> relationDTOs = ObjectUtils.convertList(
				relationDOs, PropertyGroupRelationshipDTO.class);
		return ObjectUtils.convertList(
				relationDTOs, PropertyGroupRelationshipVO.class);
	}
	
	/**
	 * 查询属性分组关联的属性
	 * @param category 类目
	 * @throws Exception
	 */
	private List<PropertyVO> queryPropertiesForGroup(PropertyGroupVO propertyGroup) throws Exception {
		List<PropertyVO> propertyVOs = new ArrayList<PropertyVO>();
		for(PropertyGroupRelationshipVO relation : propertyGroup.getRelations()) {
			PropertyDO propertyDO = propertyDAO.getPropertyById(relation.getPropertyId());
			PropertyVO propertyVO = propertyDO.clone(PropertyDTO.class).clone(PropertyVO.class);
			propertyVOs.add(propertyVO);
		}
		return propertyVOs;
	}
	
}
