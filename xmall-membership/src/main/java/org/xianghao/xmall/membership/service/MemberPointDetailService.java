package org.xianghao.xmall.membership.service;

import org.xianghao.xmall.membership.domain.MemberPointDetailDTO;
import org.xianghao.xmall.membership.domain.MemberPointDetailQuery;

import java.util.List;

/**
 * 会员积分明细管理service接口
 * @author xianghao
 *
 */
public interface MemberPointDetailService {

	/**
	 * 分页查询会员积分变更明细 
	 * @param query 查询调价你
	 * @return 会员积分变更明细
	 * @throws Exception
	 */
	List<MemberPointDetailDTO> listByPage(MemberPointDetailQuery query) throws Exception;
	
}
