package org.xianghao.xmall.membership.controller;

import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.membership.domain.MemberPointDetailQuery;
import org.xianghao.xmall.membership.domain.MemberPointDetailVO;
import org.xianghao.xmall.membership.service.MemberPointDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员积分管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/membership/member/point") 
public class MemberPointController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberPointController.class);

	/**
	 * 会员积分明细管理service组件
	 */
	@Autowired
	private MemberPointDetailService memberPointDetailService;
	
	/**
	 * 分页查询会员积分变更明细 
	 * @param query 查询调价你
	 * @return 会员积分变更明细
	 */
	@GetMapping("/")  
	public List<MemberPointDetailVO> listByPage(MemberPointDetailQuery query) {
		try {
			return ObjectUtils.convertList(
					memberPointDetailService.listByPage(query), 
					MemberPointDetailVO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return new ArrayList<MemberPointDetailVO>();
		}
	}
	
}
