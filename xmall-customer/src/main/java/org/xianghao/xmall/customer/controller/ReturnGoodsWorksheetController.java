package org.xianghao.xmall.customer.controller;

import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.customer.domain.ReturnGoodsWorksheetQuery;
import org.xianghao.xmall.customer.domain.ReturnGoodsWorksheetVO;
import org.xianghao.xmall.customer.service.ReturnGoodsWorksheetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 退货工单管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/customer/return/goods/worksheet")  
public class ReturnGoodsWorksheetController {
	
	private static final Logger logger = LoggerFactory.getLogger(
			ReturnGoodsWorksheetController.class);

	/**
	 * 退货工单管理service组件
	 */
	@Autowired
	private ReturnGoodsWorksheetService returnGoodsWorksheetService;
	
	/**
	 * 分页查询退货工单
	 * @param query 查询条件
	 * @return 退货工单
	 */
	@GetMapping("/") 
	public List<ReturnGoodsWorksheetVO> listByPage(ReturnGoodsWorksheetQuery query) {
		try {
			return ObjectUtils.convertList(
					returnGoodsWorksheetService.listByPage(query), 
					ReturnGoodsWorksheetVO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return new ArrayList<ReturnGoodsWorksheetVO>();
		}
	}
	
	/**
	 * 根据id查询退货工单
	 * @param id 退货工单id
	 * @return 退货工单
	 */
	@GetMapping("/{id}")  
	public ReturnGoodsWorksheetVO getById(@PathVariable("id") Long id) {
		try {
			return returnGoodsWorksheetService.getById(id)
					.clone(ReturnGoodsWorksheetVO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return new ReturnGoodsWorksheetVO();
		}
	}
	
	/**
	 * 审核退货工单
	 * @param id 退货工单id
	 * @param approveResult 审核结果
	 * @throws Exception
	 */
	@PutMapping("/approve/{id}")
	public Boolean approve(@PathVariable("id") Long id, Integer approveResult) {
		try {
			returnGoodsWorksheetService.approve(id, approveResult); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 确认退货工单已经收到了退货商品
	 * @param id 退货工单id
	 * @throws Exception
	 */
	@PutMapping("/confirmReceivedReturnGoods/{id}")
	public Boolean confirmReceivedReturnGoods(@PathVariable("id") Long id) {
		try {
			returnGoodsWorksheetService.confirmReceivedReturnGoods(id);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
