package org.xianghao.xmall.logistics.dao;

import org.xianghao.xmall.logistics.domain.FreightTemplateDO;
import org.xianghao.xmall.logistics.domain.FreightTemplateQuery;

import java.util.List;

/**
 * 运费模板管理DAO接口
 * @author xianghao
 *
 */
public interface FreightTemplateDAO {

	/**
	 * 新增运费模板
	 * @param freightTemplate 运费模板
	 * @throws Exception
	 */
	void save(FreightTemplateDO freightTemplate) throws Exception;
	
	/**
	 * 分页查询运费模板
	 * @param query 运费模板查询条件
	 * @return 运费模板
	 * @throws Exception
	 */
	List<FreightTemplateDO> listByPage(FreightTemplateQuery query) throws Exception;
	
	/**
	 * 根据id查询运费模板
	 * @param id 运费模板id
	 * @return 运费模板
	 * @throws Exception
	 */
	FreightTemplateDO getById(Long id) throws Exception;
	
	/**
	 * 更新运费模板
	 * @param freightTemplate 运费模板
	 * @throws Exception
	 */
	void update(FreightTemplateDO freightTemplate) throws Exception;
	
}
