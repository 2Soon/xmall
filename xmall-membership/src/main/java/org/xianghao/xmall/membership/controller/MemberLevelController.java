package org.xianghao.xmall.membership.controller;

import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.membership.domain.MemberLevelDetailQuery;
import org.xianghao.xmall.membership.domain.MemberLevelDetailVO;
import org.xianghao.xmall.membership.service.MemberLevelDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员等级管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/membership/member/level") 
public class MemberLevelController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberLevelController.class);

	/**
	 * 会员等级明细管理service组件
	 */
	@Autowired
	private MemberLevelDetailService memberLevelDetailService;
	
	/**
	 * 分页查询会员等级变更明细 
	 * @param query 查询调价你
	 * @return 会员等级变更明细
	 */
	@GetMapping("/")  
	public List<MemberLevelDetailVO> listByPage(MemberLevelDetailQuery query) {
		try {
			return ObjectUtils.convertList(
					memberLevelDetailService.listByPage(query), 
					MemberLevelDetailVO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return new ArrayList<MemberLevelDetailVO>();
		}
	}
	
}
