package org.xianghao.xmall.api.dao.impl;

import org.xianghao.xmall.api.dao.CategoryDAO;
import org.xianghao.xmall.api.domain.CategoryDO;
import org.xianghao.xmall.api.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类目管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO {

	/**
	 * 类目管理mapper组件
	 */
	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
	 * 查询根类目
	 * @return 根类目集合
	 */
	@Override
 	public List<CategoryDO> listRoots() throws Exception {
		return categoryMapper.listRoots();
 	}
	/**
	 * 查询子类目
	 * @param id 父类目id
	 * @return 子类目集合
	 */
	@Override
	public List<CategoryDO> listChildren(Long id) throws Exception {
		return categoryMapper.listChildren(id);
	}
	
	/**
	 * 新增类目
	 * @param category 类目
	 */
	@Override
	public Long save(CategoryDO category) throws Exception {
		categoryMapper.save(category);
		return category.getId();
	}
	
	/**
	 * 根据id查询类目
	 * @param id 类目id
	 * @return 类目
	 */
	@Override
	public CategoryDO getById(Long id) throws Exception {
		return categoryMapper.getById(id);
	}
	
	/**
	 * 更新类目
	 * @param category 类目
	 */
	@Override
	public void update(CategoryDO category) throws Exception {
		categoryMapper.update(category); 
	}
	
	/**
	 * 删除类目
	 * @param id 类目id
	 */
	@Override
	public void remove(Long id) throws Exception {
		categoryMapper.remove(id);
	}
	
}
