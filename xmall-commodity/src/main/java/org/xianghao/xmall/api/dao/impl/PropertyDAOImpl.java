package org.xianghao.xmall.api.dao.impl;

import org.xianghao.xmall.api.dao.PropertyDAO;
import org.xianghao.xmall.api.domain.PropertyDO;
import org.xianghao.xmall.api.domain.PropertyQuery;
import org.xianghao.xmall.api.mapper.PropertyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品属性管理模块的DAO组件
 * @author xianghao
 *
 */
@Repository
public class PropertyDAOImpl implements PropertyDAO {
	
	/**
	 * 商品属性管理模块的mapper组件
	 */
	@Autowired
	private PropertyMapper propertyMapper;
	
	/**
	 * 分页查询商品属性
	 * @param propertyQuery 查询条件
	 * @return 商品属性
	 */
	@Override
	public List<PropertyDO> listPropertiesByPage(PropertyQuery propertyQuery) throws Exception {
		return propertyMapper.listPropertiesByPage(propertyQuery);
	}
	
	/**
	 * 新增商品属性
	 * @param propertyDO 商品属性DO对象
	 */
	@Override
	public void saveProperty(PropertyDO propertyDO) throws Exception {
		propertyMapper.saveProperty(propertyDO); 
	}
	
	/**
	 * 根据id查询商品属性 
	 * @param id 商品属性id
	 * @return 商品属性
	 */
	@Override
	public PropertyDO getPropertyById(Long id) throws Exception {
		return propertyMapper.getPropertyById(id);
	}
	
	/**
	 * 更新商品属性
	 * @param propertyDO 商品属性DO对象
	 */
	@Override
	public void updateProperty(PropertyDO propertyDO) throws Exception {
		propertyMapper.updateProperty(propertyDO); 
	}
	
}
