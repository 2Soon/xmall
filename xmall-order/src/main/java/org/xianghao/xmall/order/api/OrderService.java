package org.xianghao.xmall.order.api;

import org.xianghao.xmall.api.api.order.OrderApi;
import org.xianghao.xmall.order.constant.PublishedComment;
import org.xianghao.xmall.order.constant.ReturnGoodsApplyStatus;
import org.xianghao.xmall.order.dao.ReturnGoodsApplyDAO;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.order.service.OrderInfoService;
import org.xianghao.xmall.order.state.LoggedOrderStateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单中心接口
 * @author xianghao
 *
 */
@RestController
public class OrderService implements OrderApi {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	/**
	 * 订单状态管理器
	 */
	@Autowired
	private LoggedOrderStateManager orderStateManager;
	/**
	 * 订单管理service组件
	 */
	@Autowired
	private OrderInfoService orderInfoService;
	/**
	 * 库存中心接口
	 */
	@Autowired
	private InventoryService inventoryService;
	/**
	 * 调度中心接口
	 */
	@Autowired
	private ScheduleService scheduleService;
	/**
	 * 会员中心接口
	 */
	@Autowired
	private MembershipService membershipService;
	/**
	 * 退货申请管理DAO
	 */
	@Autowired
	private ReturnGoodsApplyDAO returnGoodsApplyDAO;
	
	/**
	 * 通知订单中心，“商品完成发货”事件发生了
	 * @param orderId 订单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informGoodsDeliveryFinishedEvent(Long orderId) {
		try {
			OrderInfoDTO order = orderInfoService.getById(orderId);
			orderStateManager.finishDelivery(order);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知订单中心，“退货工单审核不通过”事件发生了
	 * @param orderId 订单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informReturnGoodsWorksheetRejectedEvent(Long orderId) {
		try {
			OrderInfoDTO order = orderInfoService.getById(orderId);
			orderStateManager.rejectReturnGoodsApply(order);
			returnGoodsApplyDAO.updateStatus(orderId, ReturnGoodsApplyStatus.REJECTED); 
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 通知订单中心，“退货工单审核通过”事件发生了
	 * @param orderId 订单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informReturnGoodsWorsheetApprovedEvent(Long orderId) {
		try {
			OrderInfoDTO order = orderInfoService.getById(orderId);
			orderStateManager.passedReturnGoodsApply(order); 
			returnGoodsApplyDAO.updateStatus(orderId, ReturnGoodsApplyStatus.PASSED); 
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 通知订单中心，“确认收到退货商品”事件发生了
	 * @param orderId 订单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informReturnGoodsReceivedEvent(Long orderId) {
		try {
			OrderInfoDTO order = orderInfoService.getById(orderId);
			orderStateManager.confirmReceivedReturnGoods(order); 
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 通知订单中心，“退货入库单审核通过”事件发生了
	 * @param orderId 订单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informReturnGoodsInputOrderApprovedEvent(Long orderId) {
		try {
			OrderInfoDTO order = orderInfoService.getById(orderId);
			orderStateManager.finishedInputReturnGoods(order); 
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 通知订单中心，“完成退款”事件发生了
	 * @param orderId 订单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informRefundFinishedEvent(Long orderId) {
		try {
			OrderInfoDTO order = orderInfoService.getById(orderId);
			orderStateManager.finishedRefund(order); 
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 通知订单中心，“支付订单成功了”
	 * @param orderInfoId 订单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informPayOrderSuccessed(Long orderInfoId) {
		try {
			OrderInfoDTO order = orderInfoService.getById(orderInfoId);
			orderStateManager.pay(order);
			
			inventoryService.informPayOrderEvent(order);  
			scheduleService.scheduleSaleDelivery(order);  
			membershipService.informPayOrderEvent(order.getUserAccountId(), order.getPayableAmount());
			
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知订单中心，“订单发表评论”事件发生了
	 * @param orderId 订单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informPublishCommentEvent(Long orderId) {
		try {
			OrderInfoDTO order = orderInfoService.getById(orderId);
			order.setPublishedComment(PublishedComment.YES);  
			orderInfoService.update(order); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知订单中心，“订单批量发表评论”事件发生了
	 * @param orderIds 订单id集合
	 * @return 处理结果
	 */
	@Override
	public Boolean informBatchPublishCommentEvent(List<Long> orderIds) {
		try {
			for(Long orderId : orderIds) {
				informPublishCommentEvent(orderId);
			}
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 从订单中心获取，确认收货时间超过了7天，而且还没有发表评论的订单
	 * @return 订单信息DTO集合
	 */
	@Override
	public List<OrderInfoDTO> listNotPublishedCommentOrders() {
		try {
			return orderInfoService.listNotPublishedCommentOrders();
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	} 
	
	/**
	 * 根据id查询订单
	 * @param orderInfoId 订单id
	 * @return 订单
	 */
	@Override
	public OrderInfoDTO getOrderById(Long orderInfoId) {
		try {
			return orderInfoService.getById(orderInfoId); 
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
}
