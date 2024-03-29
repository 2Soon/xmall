package org.xianghao.xmall.wms.service.impl;

import org.xianghao.xmall.api.constant.wms.ReturnGoodsInputOrderApproveResult;
import org.xianghao.xmall.api.constant.wms.ReturnGoodsInputOrderStatus;
import org.xianghao.xmall.api.constant.wms.WmsStockUpdateEvent;
import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderDTO;
import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderItemDTO;
import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderPutOnItemDTO;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.wms.api.CustomerService;
import org.xianghao.xmall.wms.api.MembershipService;
import org.xianghao.xmall.wms.api.PayService;
import org.xianghao.xmall.wms.api.ScheduleService;
import org.xianghao.xmall.wms.dao.ReturnGoodsInputOrderDAO;
import org.xianghao.xmall.wms.dao.ReturnGoodsInputOrderItemDAO;
import org.xianghao.xmall.wms.dao.ReturnGoodsInputOrderPutOnItemDAO;
import org.xianghao.xmall.wms.domain.*;
import org.xianghao.xmall.wms.service.ReturnGoodsInputOrderService;
import org.xianghao.xmall.wms.stock.WmsStockUpdater;
import org.xianghao.xmall.wms.stock.WmsStockUpdaterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 退货入库单管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnGoodsInputOrderServiceImpl implements ReturnGoodsInputOrderService {

	/**
	 * 退货入库单管理DAO组件
	 */
	@Autowired
	private ReturnGoodsInputOrderDAO returnGoodsInputOrderDAO;
	/**
	 * 退货入库单条目管理DAO组件
	 */
	@Autowired
	private ReturnGoodsInputOrderItemDAO returnGoodsInputOrderItemDAO;
	/**
	 * 退货入库单上架条目管理DAO组件
	 */
	@Autowired
	private ReturnGoodsInputOrderPutOnItemDAO putOnItemDAO;
	/**
	 * 客服中心接口
	 */
	@Autowired
	private CustomerService customerService;
	/**
	 * 支付中心接口
	 */
	@Autowired
	private PayService payService;
	/**
	 * 库存更新组件工厂
	 */
	@Autowired
	private WmsStockUpdaterFactory stockUpdaterFactory;
	/**
	 * 调度服务
	 */
	@Autowired
	private ScheduleService scheduleService;
	/**
	 * 会员中心接口
	 */
	@Autowired
	private MembershipService membershipService;
	
	/**
	 * 新增退货入库单
	 * @param returnGoodsInputOrder 退货入库单
	 * @throws Exception
	 */
	@Override
	public void save(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception {
		returnGoodsInputOrder.setStatus(ReturnGoodsInputOrderStatus.EDITING); 
		Long returnGoodsInputOrderId = returnGoodsInputOrderDAO.save(
				returnGoodsInputOrder.clone(ReturnGoodsInputOrderDO.class)); 
		
		for(ReturnGoodsInputOrderItemDTO item : returnGoodsInputOrder.getItems()) {
			item.setReturnGoodsInputOrderId(returnGoodsInputOrderId); 
			returnGoodsInputOrderItemDAO.save(item.clone(ReturnGoodsInputOrderItemDO.class));  
		}
	}
	
	/**
	 * 分页查询退货入库单
	 * @param query 查询条件
	 * @return 退货入库单
	 */
	@Override
	public List<ReturnGoodsInputOrderDTO> listByPage(
			ReturnGoodsInputOrderQuery query) throws Exception {
		return ObjectUtils.convertList(
				returnGoodsInputOrderDAO.listByPage(query), 
				ReturnGoodsInputOrderDTO.class);
	}
	
	/**
	 * 根据id查询退货入库单
	 * @param id 退货入库单id
	 * @return 退货入库单
	 * @throws Exception
	 */
	@Override
	public ReturnGoodsInputOrderDTO getById(Long id) throws Exception {
		ReturnGoodsInputOrderDTO returnGoodsInputOrder = returnGoodsInputOrderDAO.getById(id)
				.clone(ReturnGoodsInputOrderDTO.class); 
		
		List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItems = ObjectUtils.convertList(
				returnGoodsInputOrderItemDAO.listByReturnGoodsInputOrderId(id), 
				ReturnGoodsInputOrderItemDTO.class);  
		returnGoodsInputOrder.setItems(returnGoodsInputOrderItems);

		for(ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItem : returnGoodsInputOrderItems) { 
			List<ReturnGoodsInputOrderPutOnItemDTO> putOnItems = ObjectUtils.convertList(
					putOnItemDAO.listByReturnGoodsInputOrderItemId(returnGoodsInputOrderItem.getId()), 
					ReturnGoodsInputOrderPutOnItemDTO.class);
			returnGoodsInputOrderItem.setPutOnItems(putOnItems);
		}
		
		return returnGoodsInputOrder;
	}
	
	/**
	 * 更新退货入库单
	 * @param returnGoodsInputOrder 退货入库单
	 * @throws Exception 
	 */
	@Override
	public void update(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception {
		returnGoodsInputOrder.setStatus(ReturnGoodsInputOrderStatus.EDITING);
		returnGoodsInputOrderDAO.update(returnGoodsInputOrder.clone(ReturnGoodsInputOrderDO.class));  
		
		for(ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItem : returnGoodsInputOrder.getItems()) {
			returnGoodsInputOrderItemDAO.update(returnGoodsInputOrderItem.clone(ReturnGoodsInputOrderItemDO.class));  
		}
	}
	
	/**
	 * 批量新增退货入库单上架条目
	 * @param putOnItems 上架条目
	 * @throws Exception
	 */
	@Override
	public void batchSavePutOnItems(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception {
		for(ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItem : returnGoodsInputOrder.getItems()) {
			for(ReturnGoodsInputOrderPutOnItemDTO putOnItem : returnGoodsInputOrderItem.getPutOnItems()) {
				putOnItemDAO.save(putOnItem.clone(ReturnGoodsInputOrderPutOnItemDO.class));   
			}
		}
	}
	
	/**
	 * 退货入库单提交审核
	 * @param id 退货入库单id
	 * @throws Exception
	 */
	@Override
	public void submitApprove(Long id) throws Exception {
		returnGoodsInputOrderDAO.updateStatus(id, ReturnGoodsInputOrderStatus.WAIT_FOR_APPROVE);
	}
	
	/**
	 * 审核退货入库单
	 * @param id 退货入库单id
	 * @param approveResult 审核结果
	 * @throws Exception
	 */
	@Override
	public void approve(Long id, Integer approveResult) throws Exception {
		if(ReturnGoodsInputOrderApproveResult.REJECTED.equals(approveResult)) {
			returnGoodsInputOrderDAO.updateStatus(id, ReturnGoodsInputOrderStatus.EDITING);
			return;
		}
		
		ReturnGoodsInputOrderDTO returnGoodsInputOrder = getById(id);
		returnGoodsInputOrderDAO.updateStatus(id, ReturnGoodsInputOrderStatus.FINISHED); 
		
		customerService.informReturnGoodsInputFinishedEvent(returnGoodsInputOrder.getReturnGoodsWorksheetId());
		
		Boolean refundResult = payService.refund(returnGoodsInputOrder);
		if(refundResult) {
			customerService.informRefundFinishedEvent(returnGoodsInputOrder.getReturnGoodsWorksheetId());
		}
		
		WmsStockUpdater stockUpdater = stockUpdaterFactory.create(
				WmsStockUpdateEvent.RETURN_GOODS_INPUT, returnGoodsInputOrder);
		stockUpdater.update();
		
		scheduleService.informReturnGoodsInputFinished(returnGoodsInputOrder);
		
		membershipService.informFinishReturnGoodsEvent(returnGoodsInputOrder.getUserAccountId(), 
				returnGoodsInputOrder.getPayableAmount());
	}
	
}
