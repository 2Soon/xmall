package org.xianghao.xmall.purchase.service.impl;

import org.xianghao.xmall.api.constant.purchase.PurchaseOrderApproveResult;
import org.xianghao.xmall.api.constant.purchase.PurchaseOrderStatus;
import org.xianghao.xmall.api.domain.purchase.PurchaseOrderDTO;
import org.xianghao.xmall.api.domain.purchase.PurchaseOrderItemDTO;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.purchase.api.ScheduleService;
import org.xianghao.xmall.purchase.dao.PurchaseOrderDAO;
import org.xianghao.xmall.purchase.dao.PurchaseOrderItemDAO;
import org.xianghao.xmall.purchase.domain.*;
import org.xianghao.xmall.purchase.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购单管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	/**
	 * 采购单管理DAO组件
	 */
	@Autowired
	private PurchaseOrderDAO purchaseOrderDAO;
	/**
	 * 采购单条目管理DAO组件
	 */
	@Autowired
	private PurchaseOrderItemDAO purchaseOrderItemDAO;
	/**
	 * 调度服务
	 */
	@Autowired
	private ScheduleService scheduleService;
	
	/**
	 * 新增采购单
	 * @param purchaseOrder 采购单
	 */
	@Override
	public void save(PurchaseOrderDTO purchaseOrder) throws Exception {
		purchaseOrder.setStatus(PurchaseOrderStatus.EDITING);
		Long purchaseOrderId = purchaseOrderDAO.save(purchaseOrder.clone(
				PurchaseOrderDO.class));  
		purchaseOrder.setId(purchaseOrderId);
		batchSavePurchaseOrderItems(purchaseOrder); 
	}
	
	/**
	 * 分页查询采购单
	 * @return 采购单
	 * @throws Exception
	 */
	@Override
	public List<PurchaseOrderDTO> listByPage(
			PurchaseOrderQuery query) throws Exception {
		return ObjectUtils.convertList(
				purchaseOrderDAO.listByPage(query), 
				PurchaseOrderDTO.class); 
	}
	
	/**
	 * 根据id查询采购单
	 * @return 采购单
	 * @throws Exception
	 */
	@Override
	public PurchaseOrderDTO getById(Long id) throws Exception {
		PurchaseOrderDTO purchaseOrder = purchaseOrderDAO.getById(id)
				.clone(PurchaseOrderDTO.class); 
		
		List<PurchaseOrderItemDTO> purchaseOrderItems = ObjectUtils.convertList(
				purchaseOrderItemDAO.listByPurchaseOrderId(id), 
				PurchaseOrderItemDTO.class);  
		purchaseOrder.setItems(purchaseOrderItems);
		
		return purchaseOrder;
	}
	
	/**
	 * 更新采购单
	 * @param purchaseOrder 采购单
	 * @throws Exception
	 */
	@Override
	public void update(PurchaseOrderDTO purchaseOrder) throws Exception {
		purchaseOrder.setStatus(PurchaseOrderStatus.EDITING);
		purchaseOrderDAO.update(purchaseOrder.clone(PurchaseOrderDO.class)); 
		purchaseOrderItemDAO.removeByPurchaseOrderId(purchaseOrder.getId()); 
		batchSavePurchaseOrderItems(purchaseOrder); 
	}
	
	/**
	 * 批量新增采购单条目
	 * @param purchaseOrder
	 * @throws Exception
	 */
	private void batchSavePurchaseOrderItems(PurchaseOrderDTO purchaseOrder) throws Exception {
		List<PurchaseOrderItemDO> purchaseOrderItems = ObjectUtils.convertList(
				purchaseOrder.getItems(), PurchaseOrderItemDO.class);
		purchaseOrderItemDAO.batchSave(purchaseOrder.getId(), purchaseOrderItems);
	}
	
	/**
	 * 提交审核
	 * @param id 采购单id
	 * @throws Exception
	 */
	@Override
	public void submitApprove(Long id) throws Exception {
		purchaseOrderDAO.updateStatus(id, PurchaseOrderStatus.WAIT_FOR_APPROVE); 
	}
	
	/**
	 * 审核采购单
	 * @param id 采购单id
	 * @param approveResult 审核结果
	 * @throws Exception
	 */
	@Override
	public void approve(Long id, Integer approveResult) throws Exception {
		if(PurchaseOrderApproveResult.REJECTED.equals(approveResult)) {
			purchaseOrderDAO.updateStatus(id, PurchaseOrderStatus.EDITING); 
			return;
		}
		
		purchaseOrderDAO.updateStatus(id, PurchaseOrderStatus.APPROVED);
		scheduleService.schedulePurchaseInput(getById(id));  
	}
	
	/**
	 * 更新采购单的状态 
	 * @param id 采购单id
	 * @param status 采购单状态
	 * @throws Exception
	 */
	@Override
	public void updateStatus(Long id, Integer status) throws Exception {
		purchaseOrderDAO.updateStatus(id, status); 
	}
	
}
