package org.xianghao.xmall.purchase.controller;

import org.xianghao.xmall.api.domain.purchase.PurchaseOrderDTO;
import org.xianghao.xmall.common.util.CloneDirection;
import org.xianghao.xmall.common.util.ObjectUtils;
 import org.xianghao.xmall.purchase.domain.PurchaseOrderQuery;
import org.xianghao.xmall.purchase.domain.PurchaseOrderVO;
import org.xianghao.xmall.purchase.service.PurchaseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 采购单管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/purchase/order")   
public class PurchaseOrderController {

	private static final Logger logger = LoggerFactory.getLogger(
			PurchaseOrderController.class);
	
	/**
	 * 采购单管理service组件
	 */
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	/**
	 * 分页查询采购单
	 * @return 采购单
	 * @throws Exception
	 */
	@GetMapping("/")  
	public List<PurchaseOrderVO> listByPage(PurchaseOrderQuery query) {
		try { 
			return ObjectUtils.convertList(
					purchaseOrderService.listByPage(query), 
					PurchaseOrderVO.class); 
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 根据id查询采购单
	 * @return 采购单
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public PurchaseOrderVO getById(@PathVariable("id")  Long id) {  
		try {
			return purchaseOrderService.getById(id).clone(
					PurchaseOrderVO.class, CloneDirection.OPPOSITE); 
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 新增采购单
	 * @param purchaseOrder 采购单
	 * @throws Exception
	 */
	@PostMapping("/")  
	public Boolean save(@RequestBody PurchaseOrderVO purchaseOrder) {
		try {
			purchaseOrderService.save(purchaseOrder.clone(
					PurchaseOrderDTO.class, CloneDirection.FORWARD));
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}

	/**
	 * 更新采购单
	 * @param purchaseOrder 采购单
	 * @throws Exception
	 */
	@PutMapping("/{id}")
	public Boolean update(@RequestBody PurchaseOrderDTO purchaseOrder) { 
		try {
			purchaseOrderService.update(purchaseOrder.clone(
					PurchaseOrderDTO.class, CloneDirection.FORWARD));
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 提交审核
	 * @param id 采购单id
	 * @throws Exception
	 */
	@PutMapping("/submitApprove/{id}")
	public Boolean submitApprove(@PathVariable("id") Long id) {
		try {
			purchaseOrderService.submitApprove(id);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 审核采购单
	 * @param id 采购单id
	 * @param approveResult 审核结果
	 * @throws Exception
	 */
	@PutMapping("/approve/{id}")  
	public Boolean approve(@PathVariable("id") Long id, Integer approveResult) throws Exception {
		try {
			purchaseOrderService.approve(id, approveResult); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
