package org.xianghao.xmall.api.dao.impl;

import org.xianghao.xmall.api.dao.PropertyGroupDAO;
import org.xianghao.xmall.api.domain.PropertyGroupDO;
import org.xianghao.xmall.api.mapper.PropertyGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性分组管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class PropertyGroupDAOImpl implements PropertyGroupDAO {
	
	/**
	 * 属性分组管理mapper组件
	 */
	@Autowired
	private PropertyGroupMapper propertyGroupMapper;
	
	/**
	 * 新增属性分组
	 * @param group 属性分组
	 */
	@Override
	public Long save(PropertyGroupDO group) throws Exception {
		propertyGroupMapper.save(group);
		return group.getId();
	}
	
	/**
	 * 根据类目id查询属性分组
	 * @param categoryId 类目id
	 * @return 属性分组
	 */
	@Override
	public List<PropertyGroupDO> listByCategoryId(Long categoryId) throws Exception {
		return propertyGroupMapper.listByCategoryId(categoryId);
	}
	
	/**
	 * 根据类目id删除属性分组
	 * @param categoryId 类目id
	 */
	@Override
	public void removeByCategoryId(Long categoryId) throws Exception {
		propertyGroupMapper.removeByCategoryId(categoryId);
	}

}
