package org.xianghao.xmall.api.dao;

import org.xianghao.xmall.api.domain.PropertyDO;
import org.xianghao.xmall.api.domain.PropertyQuery;

import java.util.List;

/**
 * 商品属性管理模块的DAO组件接口
 * @author xianghao
 *
 */
public interface PropertyDAO {
	
	/**
	 * 分页查询商品属性
	 * @param propertyQuery 查询条件
	 * @return 商品属性
	 * @throws Exception
	 */
	List<PropertyDO> listPropertiesByPage(PropertyQuery propertyQuery) throws Exception;
	
	/**
	 * 新增商品属性
	 * @param propertyDO 商品属性DO对象
	 * @throws Exception
	 */
	void saveProperty(PropertyDO propertyDO) throws Exception;
	
	/**
	 * 根据id查询商品属性 
	 * @param id 商品属性id
	 * @return 商品属性
	 * @throws Exception
	 */
	PropertyDO getPropertyById(Long id) throws Exception;
	
	/**
	 * 更新商品属性
	 * @param propertyDO 商品属性DO对象
	 * @throws Exception
	 */
	void updateProperty(PropertyDO propertyDO) throws Exception;
	
}
