package org.xianghao.xmall.membership.service;

import org.xianghao.xmall.membership.domain.UserDetailDTO;

/**
 * 用户详细信息管理service接口
 * @author xianghao
 *
 */
public interface UserDetailService {

	/**
	 * 根据用户账号id查询用户详细信息
	 * @param userAccountId 用户账号id
	 * @return 用户详细信息
	 * @throws Exception
	 */
	UserDetailDTO getByUserAccountId(Long userAccountId) throws Exception;
	
	/**
	 * 更新用户详细信息
	 * @param userDetail 用户详细信息
	 * @throws Exception
	 */
	void updateByUserAccountId(UserDetailDTO userDetail) throws Exception;
	
}
