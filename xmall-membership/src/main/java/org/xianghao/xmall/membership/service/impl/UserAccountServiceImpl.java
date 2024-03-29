package org.xianghao.xmall.membership.service.impl;

import org.xianghao.xmall.api.domain.membership.UserAccountDTO;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.membership.dao.UserAccountDAO;
import org.xianghao.xmall.membership.dao.UserDetailDAO;
import org.xianghao.xmall.membership.domain.UserAccountDO;
import org.xianghao.xmall.membership.domain.UserDetailDO;
import org.xianghao.xmall.membership.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户账号管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserAccountServiceImpl implements UserAccountService {

	/**
	 * 用户账号管理DAO组件
	 */
	@Autowired
	private UserAccountDAO userAccountDAO;
	/**
	 * 用户详细信息管理DAO组件
	 */
	@Autowired
	private UserDetailDAO userDetailDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 新增用户账号
	 * @param userAccount 用户账号
	 */
	@Override
	public UserAccountDTO save(UserAccountDTO userAccount) throws Exception {
		userAccount.setGmtCreate(dateProvider.getCurrentTime());
		userAccount.setGmtModified(dateProvider.getCurrentTime());  
 		UserAccountDO resultUserAccount = userAccountDAO.save(
				userAccount.clone(UserAccountDO.class));  
 		
 		UserDetailDO userDetail = new UserDetailDO();
 		userDetail.setUserAccountId(resultUserAccount.getId()); 
 		userDetail.setGmtCreate(dateProvider.getCurrentTime()); 
 		userDetail.setGmtModified(dateProvider.getCurrentTime()); 
 		userDetailDAO.save(userDetail);
 		
		return resultUserAccount.clone(UserAccountDTO.class);  
	}
	
	/**
	 * 为登录来统计是否有对应的账号在
	 * @param userAccount 用户账号
	 * @return
	 */
	@Override
	public UserAccountDTO getForLogin(UserAccountDTO userAccount) throws Exception {
		UserAccountDO resultUserAccount = userAccountDAO.getForLogin(
				userAccount.clone(UserAccountDO.class));  
		return resultUserAccount.clone(UserAccountDTO.class);
	}
	
	/**
	 * 根据id查询用户账号 
	 * @param id 用户账号id
	 * @return 用户账号
	 */
	@Override
	public UserAccountDTO getById(Long id) throws Exception {
		return userAccountDAO.getById(id).clone(UserAccountDTO.class);
	} 
	
	/**
	 * 更新密码
	 * @param userAccount 用户账号
	 */
	@Override
	public void updatePassword(UserAccountDO userAccount) throws Exception {
		userAccount.setGmtModified(dateProvider.getCurrentTime()); 
		userAccountDAO.updatePassword(userAccount); 
	}
	
}
