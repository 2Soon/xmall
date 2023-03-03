package org.xianghao.xmall.membership.service.impl;

import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.membership.dao.MemberLevelDetailDAO;
import org.xianghao.xmall.membership.domain.MemberLevelDetailDTO;
import org.xianghao.xmall.membership.domain.MemberLevelDetailQuery;
import org.xianghao.xmall.membership.service.MemberLevelDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员等级明细管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberLevelDetailServiceImpl implements MemberLevelDetailService {

	/**
	 * 会员等级明细管理DAO组件
	 */
	@Autowired
	private MemberLevelDetailDAO memberLevelDetailDAO;
	
	/**
	 * 分页查询会员等级变更明细 
	 * @param query 查询调价你
	 * @return 会员等级变更明细
	 */
	@Override
	public List<MemberLevelDetailDTO> listByPage(
			MemberLevelDetailQuery query) throws Exception {
		return ObjectUtils.convertList(memberLevelDetailDAO.listByPage(query), 
				MemberLevelDetailDTO.class); 
	}
	
}
