package org.xianghao.xmall.membership.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.membership.dao.MemberPointDAO;
import org.xianghao.xmall.membership.domain.MemberPointDO;
import org.xianghao.xmall.membership.mapper.MemberPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 会员积分管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class MemberPointDAOImpl implements MemberPointDAO {
	
	/**
	 * 会员积分管理mapper组件
	 */
	@Autowired
	private MemberPointMapper memberPointMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;

	/**
	 * 根据用户账号id查询会员积分
	 * @param userAccountId 用户账号id
	 * @return 会员积分
	 */
	@Override
	public MemberPointDO getByUserAccountId(Long userAccountId) throws Exception {
		MemberPointDO memberPoint = memberPointMapper.getByUserAccountId(userAccountId);
		
		if(memberPoint == null) {
			memberPoint = new MemberPointDO();
			memberPoint.setUserAccountId(userAccountId); 
			memberPoint.setPoint(0L); 
			save(memberPoint);
		}
		
		return memberPoint;
	}
	
	/**
	 * 新增会员积分
	 * @param memberPoint 会员积分
	 */
	@Override
	public void save(MemberPointDO memberPoint) throws Exception {
		memberPoint.setGmtCreate(dateProvider.getCurrentTime()); 
		memberPoint.setGmtModified(dateProvider.getCurrentTime()); 
		memberPointMapper.save(memberPoint); 
	}
	
	/**
	 * 更新会员积分
	 * @param memberPoint 会员积分
	 */
	@Override
	public void update(MemberPointDO memberPoint) throws Exception {
		memberPoint.setGmtModified(dateProvider.getCurrentTime()); 
		memberPointMapper.update(memberPoint); 
	}
	
}
