package org.xianghao.xmall.order.controller;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.common.util.CloneDirection;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.order.domain.*;
import org.xianghao.xmall.order.service.OrderInfoService;
import org.xianghao.xmall.order.state.LoggedOrderStateManager;
import org.xianghao.xmall.api.domain.promotion.CouponDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/order")  
public class OrderInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderInfoController.class);

	/**
	 * 订单管理service组件
	 */
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private LoggedOrderStateManager orderStateManager;
	
	@PutMapping("/test/{id}")
	public Boolean test(@PathVariable("id") Long id) {
		try {
			orderStateManager.confirmReceipt(orderInfoService.getById(id));  
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 计算订单价格
	 * @param order
	 * @param deliveryAddress
	 * @return
	 */
	@PostMapping("/price")  
	public OrderInfoVO calculateOrderPrice(@RequestBody OrderInfoVO order) {
		try {
			OrderInfoDTO resultOrder = orderInfoService.calculateOrderPrice(
					order.clone(OrderInfoDTO.class, CloneDirection.FORWARD));  
			return resultOrder.clone(OrderInfoVO.class, CloneDirection.OPPOSITE);
		} catch (Exception e) {
			logger.error("error", e); 
			return order;
		}
	}
	
	/**
	 * 计算优惠券抵扣的金额
	 * @param order 
	 * @param coupon
	 * @return
	 */
	@PostMapping("/coupon")  
	public OrderInfoVO calculateCouponDiscountPrice(
			@RequestBody CalculateCouponeDiscountPriceVO vo) {
		try {
			OrderInfoVO order = vo.getOrder();
			CouponDTO coupon = vo.getCoupon();
			
			OrderInfoDTO resultOrder = orderInfoService.calculateCouponDiscountPrice(
					order.clone(OrderInfoDTO.class, CloneDirection.FORWARD), 
					coupon);
			
			return resultOrder.clone(OrderInfoVO.class, CloneDirection.OPPOSITE);
		} catch (Exception e) {
			logger.error("error", e); 
			return vo.getOrder();
		}
	}
	
	/**
	 * 新建订单
	 * @param order
	 * @return
	 */
	@PostMapping("/")
	public OrderInfoVO save(@RequestBody OrderInfoVO order) {
		try {
			OrderInfoDTO resultOrder = orderInfoService.save(order.clone(
					OrderInfoDTO.class, CloneDirection.FORWARD));
			return resultOrder.clone(OrderInfoVO.class, CloneDirection.OPPOSITE);
		} catch (Exception e) {
			logger.error("error", e); 
			return order;
		}
	}
	
	/**
	 * 分页查询订单
	 * @param query 查询条件 
	 * @return 订单
	 * @throws Exception
	 */
	@GetMapping("/") 
	public List<OrderInfoVO> listByPage(OrderInfoQuery query) {
		try {
			List<OrderInfoDTO> orders = orderInfoService.listByPage(query);
			return ObjectUtils.convertList(orders, OrderInfoVO.class, CloneDirection.OPPOSITE);
		} catch (Exception e) {
			logger.error("error", e); 
			return new ArrayList<OrderInfoVO>();
		}
	}
	
	/**
	 * 分页查询订单
	 * @param query 查询条件 
	 * @return 订单
	 * @throws Exception
	 */
	@GetMapping("/{id}") 
	public OrderInfoVO getById(@PathVariable("id") Long id) {
		try {
			return orderInfoService.getById(id).clone(OrderInfoVO.class, CloneDirection.OPPOSITE);  
		} catch (Exception e) {
			logger.error("error", e); 
			return new OrderInfoVO();
		}
	}
	
	/**
	 * 取消订单
	 * @param id 订单id
	 * @return 处理结果
	 * @throws Exception
	 */
	@PutMapping("/cancel/{id}")  
	public Boolean cancel(@PathVariable("id") Long id) {
		try {
			return orderInfoService.cancel(id);
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 支付订单
	 * @param id 订单id
	 * @return 支付二维码
	 */
	@PutMapping("/pay/{id}")  
	public String pay(@PathVariable("id") Long id) {
		try {
			return orderInfoService.pay(id);
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 手动确认收货
	 * @param id 订单id
	 * @throws Exception
	 */
	@PutMapping("/confirmReceipt/{id}")
	public Boolean manualConfirmReceipt(@PathVariable("id") Long id) {
		try {
			return orderInfoService.manualConfirmReceipt(id);
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 申请退货
	 * @param apply 退货申请
	 * @return 处理结果
	 * @throws Exception
	 */
	@PutMapping("/applyReturnGoods/{id}")  
	public Boolean applyReturnGoods(@RequestBody ReturnGoodsApplyDTO apply) {
		try {
			return orderInfoService.applyReturnGoods(apply);
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 根据订单id查询退货申请 
	 * @param orderInfoId 订单id
	 * @return 退货申请
	 * @throws Exception
	 */
	@GetMapping("/returnGoodsApply/{orderInfoId}")  
	public ReturnGoodsApplyVO getByOrderInfoId(@PathVariable("orderInfoId") Long orderInfoId) {
		try {
			return orderInfoService.getByOrderInfoId(orderInfoId).clone(ReturnGoodsApplyVO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 更新退货物流单号
	 * @param orderInfoId 订单id
	 * @param returnGoodsLogisticCode 退货物流单号
	 * @throws Exception
	 */
	@PutMapping("/returnGoodsLogisticCode/{orderInfoId}")
	public Boolean updateReturnGoodsLogisticCode(@PathVariable("orderInfoId") Long orderInfoId, 
			String returnGoodsLogisticCode) {
		try {
			orderInfoService.updateReturnGoodsLogisticCode(orderInfoId, returnGoodsLogisticCode); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
