package org.xianghao.xmall.finance.controller;

import org.xianghao.xmall.common.util.CloneDirection;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.finance.domain.PurchaseSettlementOrderDTO;
import org.xianghao.xmall.finance.domain.PurchaseSettlementOrderQuery;
import org.xianghao.xmall.finance.domain.PurchaseSettlementOrderVO;
import org.xianghao.xmall.finance.service.PurchaseSettlementOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 采购结算单管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/finance/purchaseSettlementOrder")   
public class PurchaseSettlementOrderController {

	private static final Logger logger = LoggerFactory.getLogger(
			PurchaseSettlementOrderController.class);
	
	/**
	 * 采购结算单管理service组件
	 */
	@Autowired
	private PurchaseSettlementOrderService purchaseSettlementOrderService;
	
	/**
	 * 分页查询采购结算单
	 * @return 采购结算单
	 * @throws Exception
	 */
	@GetMapping("/")  
	public List<PurchaseSettlementOrderVO> listByPage(PurchaseSettlementOrderQuery query) {
		try { 
			return ObjectUtils.convertList(
					purchaseSettlementOrderService.listByPage(query), 
					PurchaseSettlementOrderVO.class); 
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 根据id查询采购结算单
	 * @return 采购结算单
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public PurchaseSettlementOrderVO getById(@PathVariable("id")  Long id) {  
		try {
			return purchaseSettlementOrderService.getById(id).clone(
					PurchaseSettlementOrderVO.class, CloneDirection.OPPOSITE); 
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 更新采购结算单
	 * @param purchaseSettlementOrder 采购结算单
	 * @throws Exception
	 */
	@PutMapping("/{id}")  
	public Boolean update(@RequestBody PurchaseSettlementOrderVO purchaseSettlementOrder) {
		try {
			purchaseSettlementOrderService.update(purchaseSettlementOrder.clone(
					PurchaseSettlementOrderDTO.class, CloneDirection.FORWARD));  
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 对采购结算单提交审核
	 * @param id 采购结算单id
	 * @throws Exception 
	 */
	@PutMapping("/submitApprove/{id}") 
	public Boolean submitApprove(@PathVariable("id") Long id) throws Exception {
		try {
			purchaseSettlementOrderService.submitApprove(id);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 对采购结算单进行审核
	 * @param id 采购结算单id
	 * @throws Exception 
	 */
	@PutMapping("/approve/{id}") 
	public Boolean submitApprove(@PathVariable("id") Long id, Integer approveResult) throws Exception {
		try {
			purchaseSettlementOrderService.approve(id, approveResult); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
