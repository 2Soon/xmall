package org.xianghao.xmall.wms.controller;

import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderPutOnItemDTO;
import org.xianghao.xmall.common.util.CloneDirection;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.wms.domain.*;
import org.xianghao.xmall.wms.service.PurchaseInputOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 采购入库单管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/wms/purchaseInputOrder")   
public class PurchaseInputOrderController {

	private static final Logger logger = LoggerFactory.getLogger(
			PurchaseInputOrderController.class);
	
	/**
	 * 采购入库单管理service组件
	 */
	@Autowired
	private PurchaseInputOrderService purchaseInputOrderService;
	
	/**
	 * 分页查询采购入库单
	 * @return 采购入库单
	 * @throws Exception
	 */
	@GetMapping("/")  
	public List<PurchaseInputOrderVO> listByPage(PurchaseInputOrderQuery query) {
		try { 
			return ObjectUtils.convertList(
					purchaseInputOrderService.listByPage(query), 
					PurchaseInputOrderVO.class); 
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 根据id查询采购入库单
	 * @return 采购入库单
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public PurchaseInputOrderVO getById(@PathVariable("id")  Long id) {  
		try {
			return purchaseInputOrderService.getById(id).clone(
					PurchaseInputOrderVO.class, CloneDirection.OPPOSITE); 
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 更新采购入库单
	 * @param purchaseInputOrder 采购入库单
	 * @throws Exception
	 */
	@PutMapping("/{id}")  
	public Boolean update(@RequestBody PurchaseInputOrderVO purchaseInputOrder) {
		try {
			purchaseInputOrderService.update(purchaseInputOrder.clone(
					PurchaseInputOrderDTO.class, CloneDirection.FORWARD));
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 批量新增采购入库单的上架条目
	 * @param putOnItems 上架条目
	 * @throws Exception
	 */
	@PutMapping("/putOnShelves/{id}")    
	public Boolean batchSavePutOnItems(@RequestBody List<PurchaseInputOrderPutOnItemVO> putOnItems) {
		try {
			purchaseInputOrderService.batchSavePutOnItems(ObjectUtils.convertList(
					putOnItems, PurchaseInputOrderPutOnItemDTO.class));
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 对采购入库单提交审核
	 * @param id 采购入库单id
	 * @throws Exception 
	 */
	@PutMapping("/submitApprove/{id}") 
	public Boolean submitApprove(@PathVariable("id") Long id) throws Exception {
		try {
			purchaseInputOrderService.submitApprove(id);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 对采购入库单进行审核
	 * @param id 采购入库单id
	 * @throws Exception 
	 */
	@PutMapping("/approve/{id}") 
	public Boolean submitApprove(@PathVariable("id") Long id, Integer approveResult) throws Exception {
		try {
			purchaseInputOrderService.approve(id, approveResult); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
