package org.xianghao.xmall.membership.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.membership.dao.MemberPointDetailDAO;
import org.xianghao.xmall.membership.domain.MemberPointDetailDO;
import org.xianghao.xmall.membership.domain.MemberPointDetailQuery;
import org.xianghao.xmall.membership.mapper.MemberPointDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员积分变更明细管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class MemberPointDetailDAOImpl implements MemberPointDetailDAO {

	/**
	 * 会员积分明细管理mapper组件
	 */
	@Autowired
	private MemberPointDetailMapper memberPointDetailMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 分页查询会员积分变更明细 
	 * @param query 查询调价你
	 * @return 会员积分变更明细
	 */
	@Override
	public List<MemberPointDetailDO> listByPage(MemberPointDetailQuery query) throws Exception {
		return memberPointDetailMapper.listByPage(query);
	}
	
	/**
	 * 新增会员积分明细
	 * @param memberPointDetail 会员积分明细
	 */
	@Override
	public void save(MemberPointDetailDO memberPointDetail) throws Exception {
		memberPointDetail.setGmtCreate(dateProvider.getCurrentTime());  
		memberPointDetail.setGmtModified(dateProvider.getCurrentTime()); 
		memberPointDetailMapper.save(memberPointDetail); 
	}
	
}
