package org.xianghao.xmall.membership.service;

import org.xianghao.xmall.api.domain.membership.UserAccountDTO;
import org.xianghao.xmall.membership.domain.UserAccountDO;

/**
 * 用户账号管理service接口
 * @author xianghao
 *
 */
public interface UserAccountService {

	/**
	 * 新增用户账号
	 * @param userAccount 用户账号
	 * @return 用户账号
	 * @throws Exception
	 */
	UserAccountDTO save(UserAccountDTO userAccount) throws Exception;
	
	/**
	 * 为登录来统计是否有对应的账号在
	 * @param userAccount 用户账号
	 * @return 用户账号
	 * @throws Exception
	 */
	UserAccountDTO getForLogin(UserAccountDTO userAccount) throws Exception;
	
	/**
	 * 根据id查询用户账号 
	 * @param id 用户账号id
	 * @return 用户账号
	 * @throws Exception
	 */
	UserAccountDTO getById(Long id) throws Exception;
	
	/**
	 * 更新密码
	 * @param userAccount 用户账号
	 * @throws Exception
	 */
	void updatePassword(UserAccountDO userAccount) throws Exception;
	
}
