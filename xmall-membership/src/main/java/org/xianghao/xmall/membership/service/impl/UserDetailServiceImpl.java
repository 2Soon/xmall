package org.xianghao.xmall.membership.service.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.membership.dao.UserDetailDAO;
import org.xianghao.xmall.membership.domain.UserDetailDO;
import org.xianghao.xmall.membership.domain.UserDetailDTO;
import org.xianghao.xmall.membership.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户详细信息管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailServiceImpl implements UserDetailService {

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
	 * 根据用户账号id查询用户详细信息
	 * @param userAccountId 用户账号id
	 * @return 用户详细信息
	 */
	@Override
	public UserDetailDTO getByUserAccountId(Long userAccountId) throws Exception {
		return userDetailDAO.getByUserAccountId(userAccountId).clone(UserDetailDTO.class); 
	}
	
	/**
	 * 更新用户详细信息
	 * @param userDetail 用户详细信息
	 */
	@Override
	public void updateByUserAccountId(UserDetailDTO userDetail) throws Exception {
		userDetail.setGmtModified(dateProvider.getCurrentTime());
		userDetailDAO.updateByUserAccountId(userDetail.clone(UserDetailDO.class));   
	}
	
}
