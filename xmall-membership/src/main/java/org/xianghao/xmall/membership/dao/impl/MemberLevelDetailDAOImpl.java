package org.xianghao.xmall.membership.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.membership.dao.MemberLevelDetailDAO;
import org.xianghao.xmall.membership.domain.MemberLevelDetailDO;
import org.xianghao.xmall.membership.domain.MemberLevelDetailQuery;
import org.xianghao.xmall.membership.mapper.MemberLevelDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员等级变更明细管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class MemberLevelDetailDAOImpl implements MemberLevelDetailDAO {

	/**
	 * 会员等级明细管理mapper组件
	 */
	@Autowired
	private MemberLevelDetailMapper memberLevelDetailMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 分页查询会员等级变更明细 
	 * @param query 查询调价你
	 * @return 会员等级变更明细
	 */
	@Override
	public List<MemberLevelDetailDO> listByPage(MemberLevelDetailQuery query) throws Exception {
		return memberLevelDetailMapper.listByPage(query);
	}
	
	/**
	 * 新增会员等级明细
	 * @param memberLevelDetail 会员等级明细
	 */
	@Override
	public void save(MemberLevelDetailDO memberLevelDetail) throws Exception {
		memberLevelDetail.setGmtCreate(dateProvider.getCurrentTime()); 
		memberLevelDetail.setGmtModified(dateProvider.getCurrentTime()); 
		memberLevelDetailMapper.save(memberLevelDetail); 
	}
	
}
