package org.xianghao.xmall.wms.service.impl;

import org.xianghao.xmall.api.constant.wms.PurchaseInputOrderApproveResult;
import org.xianghao.xmall.api.constant.wms.PurchaseInputOrderStatus;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderItemDTO;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderPutOnItemDTO;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.wms.api.PurchaseService;
import org.xianghao.xmall.wms.dao.PurchaseInputOrderDAO;
import org.xianghao.xmall.wms.dao.PurchaseInputOrderItemDAO;
import org.xianghao.xmall.wms.dao.PurchaseInputOrderPutOnItemDAO;
import org.xianghao.xmall.wms.domain.*;
import org.xianghao.xmall.wms.service.PurchaseInputOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购入库单管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseInputOrderServiceImpl implements PurchaseInputOrderService {

	/**
	 * 采购入库单管理DAO组件
	 */
	@Autowired
	private PurchaseInputOrderDAO purchaseInputOrderDAO;
	/**
	 * 采购入库单条目管理DAO组件
	 */
	@Autowired
	private PurchaseInputOrderItemDAO purchaseInputOrderItemDAO;
	/**
	 * 采购入库单上架条目管理的DAO组件
	 */
	@Autowired
	private PurchaseInputOrderPutOnItemDAO purchaseInputOrderPutOnItemDAO;
	/**
	 * 采购服务
	 */
	@Autowired
	private PurchaseService purchaseService;
	/**
	 * 采购入库单handler工厂
	 */
	@Autowired
	private PurchaseInputOrderHandlerFactory handlerFactory;
	
	/**
	 * 新增采购入库单
	 * @param purchaseInputOrder 采购入库单
	 */
	@Override
	public void save(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
		Long purchaseInputOrderId = purchaseInputOrderDAO.save(
				purchaseInputOrder.clone(PurchaseInputOrderDO.class));  
		
		List<PurchaseInputOrderItemDO> purchaseInputOrderItems = ObjectUtils.convertList(
				purchaseInputOrder.getItems(), PurchaseInputOrderItemDO.class);
		purchaseInputOrderItemDAO.batchSave(purchaseInputOrderId, purchaseInputOrderItems);
		
		purchaseService.informCreatePurchaseInputOrderEvent(purchaseInputOrder.getPurchaseOrderId());
	}
	
	/**
	 * 分页查询采购入库单
	 * @return 采购入库单
	 * @throws Exception
	 */
	@Override
	public List<PurchaseInputOrderDTO> listByPage(
			PurchaseInputOrderQuery query) throws Exception {
		return ObjectUtils.convertList(
				purchaseInputOrderDAO.listByPage(query), 
				PurchaseInputOrderDTO.class); 
	}
	
	/**
	 * 根据id查询采购入库单
	 * @return 采购入库单
	 * @throws Exception
	 */
	@Override
	public PurchaseInputOrderDTO getById(Long id) throws Exception {
		PurchaseInputOrderDTO purchaseInputOrder = purchaseInputOrderDAO.getById(id)
				.clone(PurchaseInputOrderDTO.class); 
		
		List<PurchaseInputOrderItemDTO> purchaseInputOrderItems = ObjectUtils.convertList(
				purchaseInputOrderItemDAO.listByPurchaseInputOrderId(id), 
				PurchaseInputOrderItemDTO.class);  
		purchaseInputOrder.setItems(purchaseInputOrderItems);

		for(PurchaseInputOrderItemDTO purchaseInputOrderItem : purchaseInputOrderItems) { 
			List<PurchaseInputOrderPutOnItemDTO> putOnItems = ObjectUtils.convertList(
					purchaseInputOrderPutOnItemDAO.listByPurchaseInputOrderItemId(purchaseInputOrderItem.getId()), 
					PurchaseInputOrderPutOnItemDTO.class);
			purchaseInputOrderItem.setPutOnItemDTOs(putOnItems);
		}
		
		return purchaseInputOrder;
	}
	
	/** 
	 * 更新采购入库单
	 * @param purchaseInputOrder 采购入库单
	 * @throws Exception
	 */
	@Override
	public void update(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
		purchaseInputOrderDAO.update(purchaseInputOrder.clone(PurchaseInputOrderDO.class));  
		
		purchaseInputOrder.setStatus(PurchaseInputOrderStatus.EDITING);
		purchaseInputOrderDAO.updateStatus(purchaseInputOrder.clone(PurchaseInputOrderDO.class)); 
		
		for(PurchaseInputOrderItemDTO purchaseInputOrderItem : purchaseInputOrder.getItems()) {
			purchaseInputOrderItemDAO.update(purchaseInputOrderItem.clone(PurchaseInputOrderItemDO.class));  
		}
	}
	
	/**
	 * 批量新增采购入库单的上架条目
	 * @param putOnItems 上架条目
	 * @throws Exception
	 */
	@Override
	public void batchSavePutOnItems(List<PurchaseInputOrderPutOnItemDTO> putOnItems) throws Exception {
		purchaseInputOrderPutOnItemDAO.batchSave(ObjectUtils.convertList(
				putOnItems, PurchaseInputOrderPutOnItemDO.class)); 
	}
	
	/**
	 * 对采购入库单提交审核
	 * @param id 采购入库单id
	 * @throws Exception 
	 */
	@Override
	public void submitApprove(Long id) throws Exception {
		purchaseInputOrderDAO.updateStatus(id, PurchaseInputOrderStatus.WAIT_FOR_APPROVE);  
	}
	
	/**
	 * 审核采购入库单
	 * @param id 采购入库单id
	 * @param approveResult 审核结果
	 * @throws Exception
	 */
	@Override
	public void approve(Long id, Integer approveResult) throws Exception {
		if(PurchaseInputOrderApproveResult.REJECTED.equals(approveResult)) {
			purchaseInputOrderDAO.updateStatus(id, PurchaseInputOrderStatus.EDITING);  
			return;
		}
		
		if(PurchaseInputOrderApproveResult.PASSED.equals(approveResult)) {
			PurchaseInputOrderDTO purchaseInputOrder = getById(id);
			PurchaseInputOrderHandler handlerChain = handlerFactory.getHandlerChain();
			handlerChain.execute(purchaseInputOrder);
		}
	}
	
	/**
	 * 更新采购入库单状态
	 * @param id 采购入库单id
	 * @param status 采购入库单状态
	 * @throws Exception
	 */
	@Override
	public void updateStatus(Long id, Integer status) throws Exception {
		purchaseInputOrderDAO.updateStatus(id, status); 
	}
	
}
