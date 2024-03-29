package org.xianghao.xmall.wms.service.impl;

import org.xianghao.xmall.api.constant.wms.SaleDeliveryOrderApproveResult;
import org.xianghao.xmall.api.constant.wms.SaleDeliveryOrderStatus;
import org.xianghao.xmall.api.domain.wms.*;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.wms.api.FinanceService;
import org.xianghao.xmall.wms.api.OrderService;
import org.xianghao.xmall.wms.dao.*;
import org.xianghao.xmall.wms.domain.*;
import org.xianghao.xmall.wms.service.SaleDeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 销售出库单管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SaleDeliveryOrderServiceImpl implements SaleDeliveryOrderService {

	/**
	 * 销售出库单管理DAO组件
	 */
	@Autowired
	private SaleDeliveryOrderDAO saleDeliveryOrderDAO;
	/**
	 * 销售出库单条目管理DAO组件
	 */
	@Autowired
	private SaleDeliveryOrderItemDAO saleDeliveryOrderItemDAO;
	/**
	 * 销售出库单拣货条目管理DAO组件
	 */
	@Autowired
	private SaleDeliveryOrderPickingItemDAO pickingItemDAO;
	/**
	 * 销售出库单发货明细管理DAO组件
	 */
	@Autowired
	private SaleDeliveryOrderSendOutDetailDAO sendOutDetailDAO;
	/**
	 * 发货单管理DAO组件
	 */
	@Autowired
	private SendOutOrderDAO sendOutOrderDAO;
	/**
	 * 发货单条目管理DAO组件
	 */
	@Autowired
	private SendOutOrderItemDAO sendOutOrderItemDAO;
	/**
	 * 物流单管理DAO组件
	 */
	@Autowired
	private LogisticOrderDAO logisticOrderDAO;
	/**
	 * 财务中心接口
	 */
	@Autowired
	private FinanceService financeService;
	/**
	 * 订单中心接口
	 */
	@Autowired
	private OrderService orderService;
	
	/**
	 * 新增销售出库单
	 * @param saleDeliveryOrder 销售出库单
	 * @throws Exception
	 */
	@Override
	public void save(SaleDeliveryOrderDTO saleDeliveryOrder) throws Exception {
		// 新增销售出库单
		Long saleDeliveryOrderId = saleDeliveryOrderDAO.save(
				saleDeliveryOrder.clone(SaleDeliveryOrderDO.class));  
		
		// 新增销售出库单条目
		for(SaleDeliveryOrderItemDTO saleDeliveryOrderItem : saleDeliveryOrder.getSaleDeliveryOrderItems()) {
			saleDeliveryOrderItem.setSaleDeliveryOrderId(saleDeliveryOrderId); 
			Long saleDeliveryOrderItemId = saleDeliveryOrderItemDAO.save(
					saleDeliveryOrderItem.clone(SaleDeliveryOrderItemDO.class));
			
			// 新增销售出库单拣货条目
			for(SaleDeliveryOrderPickingItemDTO pickingItem : saleDeliveryOrderItem.getPickingItems()) {
				pickingItem.setSaleDeliveryOrderItemId(saleDeliveryOrderItemId); 
				pickingItemDAO.save(pickingItem.clone(SaleDeliveryOrderPickingItemDO.class));  
			}
			
			// 新增销售出库单发货明细
			for(SaleDeliveryOrderSendOutDetailDTO sendOutDetail : saleDeliveryOrderItem.getSendOutItems()) {
				sendOutDetail.setSaleDeliveryOrderItemId(saleDeliveryOrderItemId); 
				sendOutDetailDAO.save(sendOutDetail.clone(SaleDeliveryOrderSendOutDetailDO.class));  
			}
		}
		
		// 新增发货单
		SendOutOrderDTO sendOutOrder = saleDeliveryOrder.getSendOutOrder();
		sendOutOrder.setSaleDeliveryOrderId(saleDeliveryOrderId); 
		Long sendOutOrderId = sendOutOrderDAO.save(sendOutOrder.clone(SendOutOrderDO.class)); 
		
		// 新增发货单条目
		for(SendOutOrderItemDTO sendOutOrderItem : sendOutOrder.getSendOutOrderItems()) {
			sendOutOrderItem.setSendOutOrderId(sendOutOrderId); 
			sendOutOrderItemDAO.save(sendOutOrderItem.clone(SendOutOrderItemDO.class));  
		}
		
		// 新增物流单
		LogisticOrderDTO logisticOrder = saleDeliveryOrder.getLogisticOrder();
		logisticOrder.setSaleDeliveryOrderId(saleDeliveryOrderId); 
		logisticOrderDAO.save(logisticOrder.clone(LogisticOrderDO.class)); 
	}
	
	/**
	 * 分页查询销售出库单
	 * @param query 查询条件
	 * @return 销售出库单
	 */
	@Override
	public List<SaleDeliveryOrderDTO> listByPage(SaleDeliveryOrderQuery query) throws Exception {
		return ObjectUtils.convertList(
				saleDeliveryOrderDAO.listByPage(query), 
				SaleDeliveryOrderDTO.class); 
	}
	
	/**
	 * 根据id查询销售出库单
	 * @param id 销售出库单id
	 * @return
	 * @throws Exception
	 */
	@Override
	public SaleDeliveryOrderDTO getById(Long id) throws Exception {
		// 查询销售出库单自己的数据
		SaleDeliveryOrderDTO saleDeliveryOrder = saleDeliveryOrderDAO.getById(id)
				.clone(SaleDeliveryOrderDTO.class);
	
		// 查询销售出库单条目
		List<SaleDeliveryOrderItemDTO> saleDeliveryOrderItems = ObjectUtils.convertList(
				saleDeliveryOrderItemDAO.listBySaleDeliveryOrderId(id), 
				SaleDeliveryOrderItemDTO.class);
		saleDeliveryOrder.setSaleDeliveryOrderItems(saleDeliveryOrderItems); 
		
		for(SaleDeliveryOrderItemDTO saleDeliveryOrderItem : saleDeliveryOrderItems) {  
			// 查询拣货条目
			List<SaleDeliveryOrderPickingItemDTO> pickingItems = ObjectUtils.convertList(
					pickingItemDAO.listBySaleDeliveryOrderItemId(saleDeliveryOrderItem.getId()),
					SaleDeliveryOrderPickingItemDTO.class);
			saleDeliveryOrderItem.setPickingItems(pickingItems); 
			  
			// 查询发货明细
			List<SaleDeliveryOrderSendOutDetailDTO> sendOutDetails = ObjectUtils.convertList(
					sendOutDetailDAO.listBySaleDeliveryOrderItemId(saleDeliveryOrderItem.getId()), 
					SaleDeliveryOrderSendOutDetailDTO.class);
			saleDeliveryOrderItem.setSendOutItems(sendOutDetails); 
		}
		
		// 查询发货单
		SendOutOrderDTO sendOutOrder = sendOutOrderDAO.getBySaleDeliveryOrderId(id)
				.clone(SendOutOrderDTO.class);
		saleDeliveryOrder.setSendOutOrder(sendOutOrder); 
		 
		List<SendOutOrderItemDTO> sendOutOrderItems = ObjectUtils.convertList(
				sendOutOrderItemDAO.listByOrderInfoId(sendOutOrder.getId()), 
				SendOutOrderItemDTO.class);
		sendOutOrder.setSendOutOrderItems(sendOutOrderItems); 
		
		// 查询物流单
		LogisticOrderDTO logisticOrder = logisticOrderDAO.getBySaleDeliveryOrderId(id)
				.clone(LogisticOrderDTO.class);
		saleDeliveryOrder.setLogisticOrder(logisticOrder); 
		
		return saleDeliveryOrder;
	}
	
	/**
	 * 根据id查询销售出库单
	 * @param id 销售出库单id
	 * @return 销售出库单
	 */
	@Override
	public SaleDeliveryOrderDTO getByOrderId(Long orderId) throws Exception {
		return saleDeliveryOrderDAO.getByOrderId(orderId).clone(SaleDeliveryOrderDTO.class); 
	}
	
	/**
	 * 更新销售出库单的发货时间
	 * @param saleDeliveryOrder 销售出库单
	 */
	@Override
	public void updateDeliveryTime(Long id, Date deliveryTime) throws Exception {
		SaleDeliveryOrderDTO saleDeliveryOrder = getById(id);
		saleDeliveryOrder.setDeliveryTime(deliveryTime); 
		saleDeliveryOrderDAO.update(saleDeliveryOrder.clone(SaleDeliveryOrderDO.class));  
	}
	
	/**
	 * 对销售出库单提交审核
	 * @param id 销售出库单id
	 * @throws Exception
	 */
	@Override
	public void submitApprove(Long id) throws Exception {
		SaleDeliveryOrderDTO saleDeliveryOrder = getById(id);
		saleDeliveryOrder.setStatus(SaleDeliveryOrderStatus.WAIT_FOR_APPROVE);
		saleDeliveryOrderDAO.update(saleDeliveryOrder.clone(SaleDeliveryOrderDO.class));   
	}
	
	/**
	 * 审核销售出库单 
	 * @param id 销售出库单id
	 * @param approveResult 审核结果
	 * @throws Exception
	 */
	@Override
	public void approve(Long id, Integer approveResult) throws Exception {
		SaleDeliveryOrderDTO saleDeliveryOrder = getById(id);
		
		if(SaleDeliveryOrderApproveResult.REJECTED.equals(approveResult)) {
			saleDeliveryOrder.setStatus(SaleDeliveryOrderStatus.EDITING);
			saleDeliveryOrderDAO.update(saleDeliveryOrder.clone(SaleDeliveryOrderDO.class));   
			return;
		}
		
		saleDeliveryOrder.setStatus(SaleDeliveryOrderStatus.FINISHED);
		saleDeliveryOrderDAO.update(saleDeliveryOrder.clone(SaleDeliveryOrderDO.class));   
		
		financeService.payForLogisticsCompany(saleDeliveryOrder);
		
		orderService.informGoodsDeliveryFinishedEvent(saleDeliveryOrder.getOrderId());
	}
	
}
