package org.xianghao.xmall.finance.service.impl;

import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.finance.api.WmsService;
import org.xianghao.xmall.finance.constant.PurchaseSettlementOrderApproveResult;
import org.xianghao.xmall.finance.constant.PurchaseSettlementOrderStatus;
import org.xianghao.xmall.finance.dao.PurchaseSettlementOrderDAO;
import org.xianghao.xmall.finance.dao.PurchaseSettlementOrderItemDAO;
import org.xianghao.xmall.finance.domain.*;
import org.xianghao.xmall.finance.service.PurchaseSettlementOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购结算单管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseSettlementOrderServiceImpl implements PurchaseSettlementOrderService {

	/**
	 * 采购结算单管理DAO组件
	 */
	@Autowired
	private PurchaseSettlementOrderDAO purchaseSettlementOrderDAO;
	/**
	 * 采购结算单条目管理DAO组件
	 */
	@Autowired
	private PurchaseSettlementOrderItemDAO purchaseSettlementOrderItemDAO;
	/**
	 * wms服务
	 */
	@Autowired
	private WmsService wmsService;
	
	/**
	 * 新增采购结算单
	 * @param purchaseSettlementOrder 采购结算单
	 */
	@Override
	public void save(PurchaseSettlementOrderDTO purchaseSettlementOrder) throws Exception {
		// 新增采购结算单
		purchaseSettlementOrder.setStatus(PurchaseSettlementOrderStatus.EDITING);
		
		Double totalSettlementAmount = 0.0;
		for(PurchaseSettlementOrderItemDTO item : purchaseSettlementOrder.getItems()) {
			totalSettlementAmount += item.getArrivalCount() * item.getPurchasePrice();
		}
		purchaseSettlementOrder.setTotalSettlementAmount(totalSettlementAmount); 
		
		Long purchaseSettlementOrderId = purchaseSettlementOrderDAO.save(
				purchaseSettlementOrder.clone(PurchaseSettlementOrderDO.class));  
		
		// 新增采购结算单条目
		List<PurchaseSettlementOrderItemDO> purchaseSettlementOrderItems = ObjectUtils.convertList(
				purchaseSettlementOrder.getItems(), PurchaseSettlementOrderItemDO.class);
		purchaseSettlementOrderItemDAO.batchSave(purchaseSettlementOrderId, 
				purchaseSettlementOrderItems);
		
		// 通知wms中心采购结算单创建了
		wmsService.informCreatePurchaseSettlementOrderEvent(
				purchaseSettlementOrder.getPurchaseInputOrderId());
	}
	
	/**
	 * 分页查询采购结算单
	 * @return 采购结算单
	 * @throws Exception
	 */
	@Override
	public List<PurchaseSettlementOrderDTO> listByPage(
			PurchaseSettlementOrderQuery query) throws Exception {
		return ObjectUtils.convertList(
				purchaseSettlementOrderDAO.listByPage(query), 
				PurchaseSettlementOrderDTO.class); 
	}
	
	/**
	 * 根据id查询采购结算单
	 * @return 采购结算单
	 * @throws Exception
	 */
	@Override
	public PurchaseSettlementOrderDTO getById(Long id) throws Exception {
		PurchaseSettlementOrderDTO purchaseSettlementOrder = purchaseSettlementOrderDAO.getById(id)
				.clone(PurchaseSettlementOrderDTO.class); 
		
		List<PurchaseSettlementOrderItemDTO> purchaseSettlementOrderItems = ObjectUtils.convertList(
				purchaseSettlementOrderItemDAO.listByPurchaseSettlementOrderId(id), 
				PurchaseSettlementOrderItemDTO.class);  
		purchaseSettlementOrder.setItems(purchaseSettlementOrderItems);
		
		return purchaseSettlementOrder;
	}
	
	/** 
	 * 更新采购结算单
	 * @param purchaseSettlementOrder 采购结算单
	 * @throws Exception
	 */
	@Override
	public void update(PurchaseSettlementOrderDTO purchaseSettlementOrder) throws Exception {
		purchaseSettlementOrderDAO.update(purchaseSettlementOrder.clone(PurchaseSettlementOrderDO.class));  
		purchaseSettlementOrderDAO.updateStatus(purchaseSettlementOrder.clone(PurchaseSettlementOrderDO.class)); 
	}
	
	/**
	 * 对采购结算单提交审核
	 * @param id 采购结算单id
	 * @throws Exception 
	 */
	@Override
	public void submitApprove(Long id) throws Exception {
		purchaseSettlementOrderDAO.updateStatus(id, PurchaseSettlementOrderStatus.WAIT_FOR_APPROVE);  
	}
	
	/**
	 * 审核采购结算单
	 * @param id 采购结算单id
	 * @param approveResult 审核结果
	 * @throws Exception
	 */
	@Override
	public void approve(Long id, Integer approveResult) throws Exception {
		if(PurchaseSettlementOrderApproveResult.REJECTED.equals(approveResult)) {
			purchaseSettlementOrderDAO.updateStatus(id, PurchaseSettlementOrderStatus.EDITING);  
			return;
		}
		
		if(PurchaseSettlementOrderApproveResult.PASSED.equals(approveResult)) {
			PurchaseSettlementOrderDO purchaseSettlementOrder = purchaseSettlementOrderDAO.getById(id);
			purchaseSettlementOrderDAO.updateStatus(id, PurchaseSettlementOrderStatus.FINISHED);
			wmsService.informFinishedPurchaseSettlementOrderEvent(purchaseSettlementOrder.getPurchaseInputOrderId());
		}
	}
	
}
