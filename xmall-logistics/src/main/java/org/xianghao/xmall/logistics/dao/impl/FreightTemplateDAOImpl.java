package org.xianghao.xmall.logistics.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.logistics.dao.FreightTemplateDAO;
import org.xianghao.xmall.logistics.domain.FreightTemplateDO;
import org.xianghao.xmall.logistics.domain.FreightTemplateQuery;
import org.xianghao.xmall.logistics.mapper.FreightTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 运费模板管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class FreightTemplateDAOImpl implements FreightTemplateDAO {
	
	/**
	 * 运费模板管理mapper组件
	 */
	@Autowired
	private FreightTemplateMapper freightTemplateMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 新增运费模板
	 * @param freightTemplate 运费模板
	 */
	@Override
	public void save(FreightTemplateDO freightTemplate) throws Exception {
		freightTemplate.setGmtCreate(dateProvider.getCurrentTime()); 
		freightTemplate.setGmtModified(dateProvider.getCurrentTime()); 
		freightTemplateMapper.save(freightTemplate); 
	}
	
	/**
	 * 分页查询运费模板
	 * @param query 运费模板查询条件
	 * @return 运费模板
	 */
	@Override
	public List<FreightTemplateDO> listByPage(FreightTemplateQuery query) throws Exception {
		return freightTemplateMapper.listByPage(query);
	}
	
	/**
	 * 根据id查询运费模板
	 * @param query 运费模板查询条件
	 * @return 运费模板
	 */
	@Override
	public FreightTemplateDO getById(Long id) throws Exception {
		return freightTemplateMapper.getById(id);
	}
	
	/**
	 * 更新运费模板
	 * @param freightTemplate 运费模板
	 */
	@Override
	public void update(FreightTemplateDO freightTemplate) throws Exception {
		freightTemplateMapper.update(freightTemplate); 
	}
	
}
