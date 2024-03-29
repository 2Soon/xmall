package org.xianghao.xmall.api.dao.impl;

import org.xianghao.xmall.api.dao.BrandDAO;
import org.xianghao.xmall.api.domain.BrandDO;
import org.xianghao.xmall.api.domain.BrandQuery;
import org.xianghao.xmall.api.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 品牌管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class BrandDAOImpl implements BrandDAO {
	
	/**
	 * 品牌管理mapper组件
	 */
	@Autowired
	private BrandMapper brandMapper;

	/**
	 * 分页查询品牌
	 * @param query 查询条件
	 * @return 品牌
	 */
	@Override
	public List<BrandDO> listByPage(BrandQuery query) {
		return brandMapper.listByPage(query);
	}
	
	/**
	 * 根据id查询品牌
	 * @param id 品牌id
	 * @return 品牌
	 */
	@Override
	public BrandDO getById(Long id) {
		return brandMapper.getById(id);
	}
	
	/**
	 * 新增品牌
	 * @param brand 品牌
	 */
	@Override
	public void save(BrandDO brand) {
		brandMapper.save(brand); 
	}
	
	/**
	 * 更新品牌
	 * @param brand
	 */
	@Override
	public void update(BrandDO brand) {
		brandMapper.update(brand); 
	}
	
	/**
	 * 删除品牌
	 * @param id 品牌id
	 */
	@Override
	public void remove(Long id) {
		brandMapper.remove(id); 
	}
	
	/**
	 * 更新品牌logo
	 * @param brand
	 */
	@Override
	public void updateLogoPath(BrandDO brand) {
		brandMapper.updateLogoPath(brand); 
	}
	
	/**
	 * 更新品牌授权认证书
	 * @param brand
	 */
	@Override
	public void updateAuthVoucherPath(BrandDO brand) {
		brandMapper.updateAuthVoucherPath(brand); 
	}
	
}
