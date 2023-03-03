package org.xianghao.xmall.membership.dao.impl;

import org.xianghao.xmall.membership.dao.UserDetailDAO;
import org.xianghao.xmall.membership.domain.UserDetailDO;
import org.xianghao.xmall.membership.mapper.UserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 用户详细信息管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class UserDetailDAOImpl implements UserDetailDAO {
	
	/**
	 * 用户详细信息管理mapper组件
	 */
	@Autowired
	private UserDetailMapper userDetailMapper;
	
	/**
	 * 新增用户详细信息
	 * @param userDetail 用户详细信息
	 */
	@Override
	public void save(UserDetailDO userDetail) {
		userDetailMapper.save(userDetail); 
	}
	
	/**
	 * 根据用户账号id查询用户详细信息
	 * @param userAccountId 用户账号id
	 * @return 用户详细信息
	 */
	@Override
	public UserDetailDO getByUserAccountId(Long userAccountId) {
		return userDetailMapper.getByUserAccountId(userAccountId);
	}
	
	/**
	 * 更新用户详细信息
	 * @param userDetail 用户详细信息
	 */
	@Override
	public void updateByUserAccountId(UserDetailDO userDetail) {
		userDetailMapper.updateByUserAccountId(userDetail); 
	}

}
