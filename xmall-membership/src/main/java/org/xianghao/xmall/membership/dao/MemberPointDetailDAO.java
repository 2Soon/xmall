package org.xianghao.xmall.membership.dao;

import org.xianghao.xmall.membership.domain.MemberPointDetailDO;
import org.xianghao.xmall.membership.domain.MemberPointDetailQuery;

import java.util.List;

/**
 * 会员积分变更明细管理DAO接口
 * @author xianghao
 *
 */
public interface MemberPointDetailDAO {

	/**
	 * 分页查询会员积分变更明细 
	 * @param query 查询调价你
	 * @return 会员积分变更明细
	 * @throws Exception
	 */
	List<MemberPointDetailDO> listByPage(MemberPointDetailQuery query) throws Exception;
	
	/**
	 * 新增会员积分明细
	 * @param memberPointDetail 会员积分明细
	 * @throws Exception
	 */
	void save(MemberPointDetailDO memberPointDetail) throws Exception;
	
}
