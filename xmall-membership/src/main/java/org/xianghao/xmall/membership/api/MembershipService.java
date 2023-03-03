package org.xianghao.xmall.membership.api;

import org.xianghao.xmall.api.api.membership.MembershipApi;
import org.xianghao.xmall.api.domain.membership.UserAccountDTO;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.membership.dao.UserAccountDAO;
import org.xianghao.xmall.membership.service.impl.FirstLoginMembershipUpdater;
import org.xianghao.xmall.membership.service.impl.PayOrderMembershipUpdater;
import org.xianghao.xmall.membership.service.impl.PublishCommentMembershipUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员服务
 * @author xianghao
 *
 */
@RestController
public class MembershipService implements MembershipApi {
	
	private static final Logger logger = LoggerFactory.getLogger(
			MembershipService.class);
	
	/**
	 * 用户账号DAO组件
	 */
	@Autowired
	private UserAccountDAO userAccountDAO;
	/**
	 * 每天第一次登录会员信息更新组件
	 */
	@Autowired
	private FirstLoginMembershipUpdater firstLoginMembershipUpdater;
	/**
	 * 支付订单会员信息更新组件
	 */
	@Autowired
	private PayOrderMembershipUpdater payOrderMembershipUpdater;
	/**
	 * 发表评论会员信息更新组件
	 */
	@Autowired
	private PublishCommentMembershipUpdater publishCommentMembershipUpdater;
	
	/**
	 * 通知会员中心，“用户今日第一次登录”事件发生了
	 * @param userAccountId 用户账号ID
	 * @return 处理结果
	 */
	@Override
	public Boolean informFirstLoginDailyEvent(Long userAccountId) {
		try {
			firstLoginMembershipUpdater.execute(userAccountId, null);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知会员中心，“支付订单”事件发生了
	 * @param userAccountId 用户账号id
	 * @param totalOrderAmount 订单总金额
	 * @return 处理结果
	 */
	@Override
	public Boolean informPayOrderEvent(Long userAccountId, Double totalOrderAmount) {
		try {
			payOrderMembershipUpdater.execute(userAccountId, totalOrderAmount);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知会员中心，“完成退货”事件发生了
	 * @param userAccountId 用户账号id
	 * @param totalOrderAmount 订单总金额
	 * @return 处理结果
	 */
	@Override
	public Boolean informFinishReturnGoodsEvent(Long userAccountId, Double totalOrderAmount) {
		try {
			payOrderMembershipUpdater.undo(userAccountId, totalOrderAmount);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知会员中心，“发表评论”事件发生了
	 * @param userAccountId 用户账号id
	 * @param showPictures 是否晒图
	 * @return 处理结果
	 */
	@Override
	public Boolean informPublishCommentEvent(Long userAccountId, Boolean showPictures) {
		try {
			publishCommentMembershipUpdater.execute(userAccountId, showPictures);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知会员中心，“删除评论”事件发生了
	 * @param userAccountId 用户账号id
	 * @param showPictures 是否晒图
	 * @return 处理结果
	 */
	@Override
	public Boolean informRemoveCommentEvent(Long userAccountId, Boolean showPictures) {
		try {
			publishCommentMembershipUpdater.undo(userAccountId, showPictures);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 查询所有的用户账户
	 * @return
	 */
	@Override
	public List<UserAccountDTO> listAllUserAccounts() {
		try {
			return ObjectUtils.convertList(userAccountDAO.listAll(), UserAccountDTO.class);
		} catch (Exception e) { 
			logger.error("error", e); 
			return new ArrayList<UserAccountDTO>();
		}
	}

}
