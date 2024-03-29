package org.xianghao.xmall.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.xianghao.xmall.auth.dao.AccountRoleRelationshipDAO;
import org.xianghao.xmall.auth.dao.RoleDAO;
import org.xianghao.xmall.auth.dao.RolePriorityRelationshipDAO;
import org.xianghao.xmall.auth.domain.RoleDO;
import org.xianghao.xmall.auth.domain.RoleDTO;
import org.xianghao.xmall.auth.domain.RolePriorityRelationshipDO;
import org.xianghao.xmall.auth.domain.RolePriorityRelationshipDTO;
import org.xianghao.xmall.auth.domain.RoleQuery;
import org.xianghao.xmall.auth.service.RoleService;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.common.util.ObjectUtils;

/**
 * 角色管理模块service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

	/**
	 * 角色管理模块DAO组件
	 */
	@Autowired
	private RoleDAO roleDAO;
	/**
	 * 角色权限关系管理模块DAO组件
	 */
	@Autowired
	private RolePriorityRelationshipDAO rolePriorityRelationDAO;
	/**
	 * 账号角色关系管理模块DAO组件
	 */
	@Autowired
	private AccountRoleRelationshipDAO accountRoleRelationDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	/**
	 * 权限缓存管理组件
	 */
	@Autowired
	private PriorityCacheManager priorityCacheManager;
	
	/**
	 * 分页查询角色
	 * @param query 查询条件
	 * @return 角色DO对象集合
	 */
	@Override
	public List<RoleDTO> listByPage(RoleQuery query) throws Exception {
		List<RoleDO> roles = roleDAO.listByPage(query);
		return ObjectUtils.convertList(roles, RoleDTO.class);  
	}
	
	/**
	 * 根据id查询角色DO对象
	 * @param id 角色 id 
	 * @return 角色DO对象
	 */
	@Override
	public RoleDTO getById(Long id) throws Exception {
		RoleDO role = roleDAO.getById(id); 
		if(role == null) {
			return null;
		}
		
		RoleDTO resultRole = role.clone(RoleDTO.class);
		
		List<RolePriorityRelationshipDO> relations = 
				rolePriorityRelationDAO.listByRoleId(id);
		resultRole.setRolePriorityRelations(ObjectUtils.convertList(
				relations, RolePriorityRelationshipDTO.class));  
		
		return resultRole;
	}
	
	/**
	 * 新增角色
	 * @param role 角色DO对象
	 */
	@Override
	public void save(RoleDTO role) throws Exception {
		role.setGmtCreate(dateProvider.getCurrentTime()); 
		role.setGmtModified(dateProvider.getCurrentTime());  
		Long roleId = roleDAO.save(role.clone(RoleDO.class));  
		
		for(RolePriorityRelationshipDTO relation : role.getRolePriorityRelations()) {
			relation.setRoleId(roleId);
			relation.setGmtCreate(dateProvider.getCurrentTime()); 
			relation.setGmtModified(dateProvider.getCurrentTime()); 
			rolePriorityRelationDAO.save(relation.clone(RolePriorityRelationshipDO.class));
		}
	}
	
	/**
	 * 更新角色
	 * @param role 角色DO对象
	 */
	@Override
	public void update(RoleDTO role) throws Exception {
		role.setGmtModified(dateProvider.getCurrentTime()); 
		roleDAO.update(role.clone(RoleDO.class));
		
		rolePriorityRelationDAO.removeByRoleId(role.getId());
		
		for(RolePriorityRelationshipDTO relation : role.getRolePriorityRelations()) {
			relation.setRoleId(role.getId());
			relation.setGmtCreate(dateProvider.getCurrentTime()); 
			relation.setGmtModified(dateProvider.getCurrentTime()); 
			rolePriorityRelationDAO.save(relation.clone(RolePriorityRelationshipDO.class));
		}
		
		List<Long> accountIds = accountRoleRelationDAO
				.listAccountIdsByRoleId(role.getId());
		for(Long accountId : accountIds) {
			priorityCacheManager.remove(accountId);
		}
	}
	
	/**
	 * 删除角色
	 * @param id 角色id
	 * @return 处理结果
	 */
	@Override
	public Boolean remove(Long id) throws Exception {
		Long count = accountRoleRelationDAO.countByRoleId(id);
		if(count > 0L) {
			return false;
		}
		
		roleDAO.remove(id);
		rolePriorityRelationDAO.removeByRoleId(id);
		
		return true;
	}
	
}
