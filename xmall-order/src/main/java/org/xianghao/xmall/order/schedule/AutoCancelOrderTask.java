package org.xianghao.xmall.order.schedule;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.order.api.InventoryService;
import org.xianghao.xmall.order.dao.OrderInfoDAO;
import org.xianghao.xmall.order.dao.OrderItemDAO;
import org.xianghao.xmall.order.domain.OrderInfoDO;
import org.xianghao.xmall.order.state.LoggedOrderStateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 自动取消订单任务
 * @author xianghao
 *
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class AutoCancelOrderTask {
	
	private static final Logger logger = LoggerFactory.getLogger(AutoCancelOrderTask.class);
	
	/**
	 * 订单管理DAO组件
	 */
	@Autowired
	private OrderInfoDAO orderInfoDAO;
	/**
	 * 订单条目管理DAO组件
	 */
	@Autowired
	private OrderItemDAO orderItemDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	/**
	 * 订单状态管理组件
	 */
	@Autowired
	private LoggedOrderStateManager orderStateManager;
	/**
	 * 库存服务
	 */
	@Autowired
	private InventoryService inventoryService;
	
	/**
	 * 执行任务逻辑
	 */
	 @Scheduled(fixedRate = 1 * 60 * 1000)
	 public void execute() {
		 try {
			 for(OrderInfoDO orderInfoDO : orderInfoDAO.listAllUnpayed()) { 
				 OrderInfoDTO order = getOrderInfoDTO(orderInfoDO);
				 
				 if(dateProvider.getCurrentTime().getTime() - 
						 order.getGmtCreate().getTime() <= 24 * 60 * 60 * 1000) {
					 continue;
				 }
				 
				 orderStateManager.cancel(order);
				 inventoryService.informCancelOrderEvent(order); 
			 }
		} catch (Exception e) {
			logger.error("error", e); 
		}
	 }
	 
	 /**
	  * 将订单DO转换为订单DTO
	  * @param orderInfoDO 订单DO 
	  * @return 订单DTO
	  * @throws Exception
	  */
	 private OrderInfoDTO getOrderInfoDTO(OrderInfoDO orderInfoDO) throws Exception {
		 OrderInfoDTO orderInfoDTO = orderInfoDO.clone(OrderInfoDTO.class);
		 List<OrderItemDTO> orderItems = ObjectUtils.convertList(
				orderItemDAO.listByOrderInfoId(orderInfoDTO.getId()), OrderItemDTO.class);
		 orderInfoDTO.setOrderItems(orderItems);
		return orderInfoDTO;	
	 }
	
}
