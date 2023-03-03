package org.xianghao.xmall.membership.controller;

import org.xianghao.xmall.api.domain.membership.UserAccountDTO;
import org.xianghao.xmall.membership.domain.UserAccountDO;
import org.xianghao.xmall.membership.domain.UserAccountVO;
import org.xianghao.xmall.membership.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户账号管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/membership/user/account")   
public class UserAccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

	/**
	 * 用户账号管理service组件
	 */
	@Autowired
	private UserAccountService userAccountService;
	
	/**
	 * 用户注册
	 * @param userAccount 用户账号
	 */
	@PostMapping("/register")  
	public UserAccountVO register(@RequestBody UserAccountVO userAccount) {
		try {
			UserAccountDTO resultUserAccount = userAccountService.save(
					userAccount.clone(UserAccountDTO.class));
			return resultUserAccount.clone(UserAccountVO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return userAccount;
		}
	}
	
	/**
	 * 为登录来统计是否有对应的账号在
	 * @param userAccount 用户账号
	 * @return
	 */
	@GetMapping("/login")  
	public UserAccountVO login(UserAccountVO userAccount) {
		try {
			UserAccountDTO resultUserAccount = userAccountService.getForLogin(
					userAccount.clone(UserAccountDTO.class));
			return resultUserAccount.clone(UserAccountVO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return userAccount;
		}
	}
	
	/**
	 * 更新密码
	 * @param userAccount 用户账号
	 */
	@PutMapping("/{id}")  
	public Boolean updatePassword(@RequestBody UserAccountDO userAccount) {
		try {
			userAccountService.updatePassword(userAccount); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
