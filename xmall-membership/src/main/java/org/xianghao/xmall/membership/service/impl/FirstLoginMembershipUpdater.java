package org.xianghao.xmall.membership.service.impl;

import org.xianghao.xmall.common.constant.CollectionSize;
import org.xianghao.xmall.membership.constant.MemberLevel;
import org.xianghao.xmall.membership.constant.UpdateMemberLevelResult;
import org.xianghao.xmall.membership.constant.UpdateMemberPointResult;
import org.xianghao.xmall.membership.dao.MemberLevelDAO;
import org.xianghao.xmall.membership.dao.MemberLevelDetailDAO;
import org.xianghao.xmall.membership.dao.MemberPointDAO;
import org.xianghao.xmall.membership.dao.MemberPointDetailDAO;
import org.xianghao.xmall.membership.domain.MemberLevelDO;
import org.xianghao.xmall.membership.domain.MemberPointDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 每天第一次登录的会员信息更新组件
 * @author xianghao
 *
 */
@Component
public class FirstLoginMembershipUpdater extends AbstractMembershipUpdater<Object, Boolean> {

	/**
	 * 每天第一次登录对成长值的累加值
	 */
	private static final Long FIRST_LOGIN_GROWTH_VALUE_UPDATE = 5L;
	/**
	 * 每天第一次登录对会员积分的累加值
	 */
	private static final Long FIRST_LOGIN_MEMBER_PONIT_UPDATE = 5L;
	
	/**
	 * 会员等级管理DAO组件
	 */
	@Autowired
	private MemberLevelDAO memberLevelDAO;
	/**
	 * 会员积分管理DAO组件
	 */
	@Autowired
	private MemberPointDAO memberPointDAO;
	
	@Autowired
	public FirstLoginMembershipUpdater(
			MemberLevelDetailDAO memberLevelDetailDAO,
			MemberPointDetailDAO memberPointDetailDAO) {
		super(memberLevelDetailDAO, memberPointDetailDAO);
	}
	
	/**
	 * 更新会员等级
	 */
	@Override
	protected Map<String, Object> updateMemberLevel(Long userAccountId, 
			Object parameter) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>(CollectionSize.DEFAULT);
		
		MemberLevelDO memberLevel = memberLevelDAO.getByUserAccountId(userAccountId);
		
		result.put(UpdateMemberLevelResult.OLD_GROWTH_VALUE, memberLevel.getGrowthValue());
		result.put(UpdateMemberLevelResult.OLD_MEMBER_LEVEL, memberLevel.getLevel());
		
		memberLevel.setGrowthValue(memberLevel.getGrowthValue() 
				+ FIRST_LOGIN_GROWTH_VALUE_UPDATE); 
		memberLevel.setLevel(MemberLevel.get(memberLevel.getGrowthValue()));  
		memberLevelDAO.update(memberLevel); 
		
		result.put(UpdateMemberLevelResult.UPDATED_GROWTH_VALUE, 
				FIRST_LOGIN_GROWTH_VALUE_UPDATE);
		result.put(UpdateMemberLevelResult.NEW_GROWTH_VALUE, memberLevel.getGrowthValue());
		result.put(UpdateMemberLevelResult.NEW_MEMBER_LEVEL, memberLevel.getLevel());
		
		return result;
	}

	/**
	 * 更新会员积分
	 */
	@Override
	protected Map<String, Object> updateMemberPoint(Long userAccountId, 
			Object parameter) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>(CollectionSize.DEFAULT);
		
		MemberPointDO memberPoint = memberPointDAO.getByUserAccountId(userAccountId);
		
		result.put(UpdateMemberPointResult.OLD_MEMBER_POINT, memberPoint.getPoint());
		
		memberPoint.setPoint(memberPoint.getPoint() + FIRST_LOGIN_MEMBER_PONIT_UPDATE); 
		memberPointDAO.update(memberPoint); 
		
		result.put(UpdateMemberPointResult.UPDATED_MEMBER_POINT, 
				FIRST_LOGIN_MEMBER_PONIT_UPDATE);
		result.put(UpdateMemberPointResult.NEW_MEMBER_POINT, memberPoint.getPoint());
		
		return result;
	}
	
	/**
	 * 获取会员等级更新原因
	 */
	@Override
	protected String getMemberLevelUpdateReason(Long userAccountId, Object parameter,
			Map<String, Object> updateMemberLevelResult) throws Exception {
		return "用户每天第一次登录";
	}

	/**
	 * 获取会员积分更新原因
	 */
	@Override
	protected String getMemberPointUpdateReason(Long userAccountId, Object parameter,
			Map<String, Object> updateMemberPointResult) throws Exception {
		return "用户每天第一次登录";
	}
	
	/**
	 * 完成更新会员信息
	 */
	@Override
	protected Boolean finishExecute(Long userAccountId, Object parameter) throws Exception {
		return true;
	}
	
	/**
	 * 撤销更新会员等级
	 */
	@Override
	protected Map<String, Object> undoUpdatedMemberLevel(Long userAccountId, 
			Object parameter) throws Exception {
		return null;
	}
	
	/**
	 * 撤销更新会员积分
	 */
	@Override
	protected Map<String, Object> undoUpdatedMemberPoint(Long userAccountId, 
			Object parameter) throws Exception {
		return null;
	}

	/**
	 * 完成撤销操作
	 */
	@Override
	protected Boolean finishUndo(Long userAccountId, Object parameter) throws Exception {
		return true;
	}
	
	/**
	 * 获取撤销会员等级原因
	 */
	@Override
	protected String getUndoMemberLevelUpdateReason(Long userAccountId, Object parameter,
			Map<String, Object> undoMemberLevelResult) throws Exception {
		return null;
	}

	/**
	 * 获取撤销会员积分原因
	 */
	@Override
	protected String getUndoMemberPointUpdateReason(Long userAccountId, Object parameter,
			Map<String, Object> undoMemberPointResult) throws Exception {
		return null;
	}

}
