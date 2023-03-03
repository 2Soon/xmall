package org.xianghao.xmall.customer.service.impl;

import org.xianghao.xmall.api.domain.customer.ReturnGoodsWorksheetDTO;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.schedule.ScheduleReturnGoodsWorksheetDTO;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.customer.api.OrderService;
import org.xianghao.xmall.customer.api.ScheduleService;
import org.xianghao.xmall.customer.constant.ReturnGoodsWorksheetApproveResult;
import org.xianghao.xmall.customer.constant.ReturnGoodsWorksheetStatus;
import org.xianghao.xmall.customer.dao.ReturnGoodsWorksheetDAO;
import org.xianghao.xmall.customer.domain.ReturnGoodsWorksheetDO;
import org.xianghao.xmall.customer.domain.ReturnGoodsWorksheetQuery;
import org.xianghao.xmall.customer.service.ReturnGoodsWorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 退货工单管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnGoodsWorksheetServiceImpl implements ReturnGoodsWorksheetService {

	/**
	 * 退货工单管理DAO组件
	 */
	@Autowired
	private ReturnGoodsWorksheetDAO returnGoodsWorksheetDAO;
	/**
	 * 订单服务
	 */
	@Autowired
	private OrderService orderService;
	/**
	 * 调度中心接口
	 */
	@Autowired
	private ScheduleService scheduleService;
	
	/**
	 * 分页查询退货工单
	 * @param query 查询条件
	 * @return 退货工单
	 */
	@Override
	public List<ReturnGoodsWorksheetDTO> listByPage(
			ReturnGoodsWorksheetQuery query) throws Exception {
		return ObjectUtils.convertList(returnGoodsWorksheetDAO.listByPage(query), 
				ReturnGoodsWorksheetDTO.class);
	}
	
	/**
	 * 根据id查询退货工单
	 * @param id 退货工单id
	 * @return 退货工单
	 */
	@Override
	public ReturnGoodsWorksheetDTO getById(Long id) throws Exception {
		return returnGoodsWorksheetDAO.getById(id).clone(ReturnGoodsWorksheetDTO.class);
	}
	
	/**
	 * 审核退货工单
	 * @param id 退货工单id
	 * @param approveResult 审核结果
	 * @throws Exception
	 */
	@Override
	public void approve(Long id, Integer approveResult) throws Exception {
		ReturnGoodsWorksheetDO worksheet = returnGoodsWorksheetDAO.getById(id);
		
		if(ReturnGoodsWorksheetApproveResult.PASSED.equals(approveResult)) {
			worksheet.setStatus(ReturnGoodsWorksheetStatus.WAIT_FOR_SEND_OUT_RETURN_GOODS);
			orderService.informReturnGoodsWorsheetApprovedEvent(worksheet.getOrderInfoId());
		} else if(ReturnGoodsWorksheetApproveResult.REJECTED.equals(approveResult)) {
			worksheet.setStatus(ReturnGoodsWorksheetStatus.APPROVE_REJECTED);  
			orderService.informReturnGoodsWorksheetRejectedEvent(worksheet.getOrderInfoId()); 
		}
		
		returnGoodsWorksheetDAO.updateStatus(worksheet); 
	}
	
	/**
	 * 确认退货工单已经收到了退货商品
	 * @param id 退货工单id
	 * @throws Exception
	 */
	@Override
	public void confirmReceivedReturnGoods(Long id) throws Exception {
		ReturnGoodsWorksheetDO worksheet = returnGoodsWorksheetDAO.getById(id);
		worksheet.setStatus(ReturnGoodsWorksheetStatus.WAIT_FOR_RETURN_GOODS_INPUT);  
		returnGoodsWorksheetDAO.updateStatus(worksheet); 
		
		orderService.informReturnGoodsReceivedEvent(worksheet.getOrderInfoId());
		
		OrderInfoDTO order = orderService.getOrderById(worksheet.getOrderInfoId());
		ReturnGoodsWorksheetDTO returnGoodsWorksheet = worksheet.clone(ReturnGoodsWorksheetDTO.class);
		
		ScheduleReturnGoodsWorksheetDTO scheduleReturnGoodsWorksheet = new ScheduleReturnGoodsWorksheetDTO();
		scheduleReturnGoodsWorksheet.setOrder(order); 
		scheduleReturnGoodsWorksheet.setReturnGoodsWorksheetDTO(returnGoodsWorksheet);
		
		scheduleService.scheduleReturnGoodsInput(scheduleReturnGoodsWorksheet); 
	}
	
}
