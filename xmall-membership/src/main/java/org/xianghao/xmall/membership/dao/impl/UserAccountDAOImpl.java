package org.xianghao.xmall.membership.dao.impl;

import org.xianghao.xmall.membership.dao.UserAccountDAO;
import org.xianghao.xmall.membership.domain.UserAccountDO;
import org.xianghao.xmall.membership.mapper.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户账号管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class UserAccountDAOImpl implements UserAccountDAO {

	/**
	 * 用户账号管理mapper组件
	 */
	@Autowired
	private UserAccountMapper userAccountMapper;
	
	/**
	 * 新增用户账号
	 * @param userAccount 用户账号
	 */
	@Override
	public UserAccountDO save(UserAccountDO userAccount) {
		userAccountMapper.save(userAccount);  
		return userAccount;
	}
	
	/**
	 * 为登录来统计是否有对应的账号在
	 * @param userAccount 用户账号
	 * @return
	 */
	@Override
	public UserAccountDO getForLogin(UserAccountDO userAccount) {
		return userAccountMapper.getForLogin(userAccount);
	}
	
	/**
	 * 根据id查询用户账号 
	 * @param id 用户账号id
	 * @return 用户账号
	 */
	@Override
	public UserAccountDO getById(Long id) {
		return userAccountMapper.getById(id);
	}
	
	/**
	 * 更新密码
	 * @param userAccount 用户账号
	 */
	@Override
	public void updatePassword(UserAccountDO userAccount) {
		userAccountMapper.updatePassword(userAccount); 
	}
	
	/**
	 * 查询所有用户账号
	 * @return
	 */
	@Override
	public List<UserAccountDO> listAll() {
		return userAccountMapper.listAll();
	}
	
}
