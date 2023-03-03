package org.xianghao.xmall.api.api.schedule;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.purchase.PurchaseOrderDTO;
import org.xianghao.xmall.api.domain.schedule.SaleDeliveryScheduleResult;
import org.xianghao.xmall.api.domain.schedule.ScheduleReturnGoodsWorksheetDTO;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调度中心对外提供的接口
 * @author xianghao
 *
 */
@RequestMapping("/schedule")  
public interface ScheduleApi {
	
	/**
	 * 通知库存中心，“采购入库完成”事件发生了
	 * @param purchaseInputOrderDTO 采购入库单DTO
	 * @return 处理结果
	 */
	@RequestMapping(value = "/informPurchaseInputFinished", method = RequestMethod.PUT)
	Boolean informPurchaseInputFinished(
			@RequestBody PurchaseInputOrderDTO purchaseInputOrderDTO);
	
	/**
	 * 通知库存中心，“提交订单”事件发生了
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@RequestMapping(value = "/informSubmitOrderEvent", method = RequestMethod.PUT)
	Boolean informSubmitOrderEvent(@RequestBody OrderInfoDTO orderDTO);
	
	/**
	 * 通知库存中心，“支付订单”事件发生了
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@RequestMapping(value = "/informPayOrderEvent", method = RequestMethod.PUT)
	Boolean informPayOrderEvent(@RequestBody OrderInfoDTO orderDTO);
	
	/**
	 * 通知库存中心，“取消订单”事件发生了
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@RequestMapping(value = "/informCancelOrderEvent", method = RequestMethod.PUT)
	Boolean informCancelOrderEvent(@RequestBody OrderInfoDTO orderDTO);
	
	/**
	 * 通知库存中心，“完成退货入库”事件发生了
	 * @param returnGoodsInputOrderDTO 退货入库单DTO
	 * @return 处理结果
	 */
	@RequestMapping(value = "/informReturnGoodsInputFinished", method = RequestMethod.PUT)
	Boolean informReturnGoodsInputFinished(
			@RequestBody ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO);
	
	/**
	 * 调度采购入库
	 * @param purchaseOrderDTO 采购单DTO
	 * @return 处理结果
	 */
	@RequestMapping(value = "/schedulePurchaseInput", method = RequestMethod.PUT)
	Boolean schedulePurchaseInput(@RequestBody PurchaseOrderDTO purchaseOrderDTO);

	/**
	 * 调度销售出库
	 * @param orderDTO 订单DTO
	 * @return 处理结果
	 */
	@RequestMapping(value = "/scheduleSaleDelivery", method = RequestMethod.PUT)
	Boolean scheduleSaleDelivery(@RequestBody OrderInfoDTO orderDTO);
	
	/**
	 * 调度退货入库
	 * @param ScheduleReturnGoodsWorksheetDTO 调度服务的退货工单
	 * @return 处理结果
	 */
	@RequestMapping(value = "/scheduleReturnGoodsInput", method = RequestMethod.PUT)
	Boolean scheduleReturnGoodsInput(@RequestBody ScheduleReturnGoodsWorksheetDTO scheduleReturnGoodsWorksheetDTO);
	
	/**
	 * 获取调度结果
	 * @param orderInfoId 订单id
	 * @param goodsSkuId 商品sku id
	 * @return 调度结果
	 */
	@RequestMapping(value = "/getScheduleResult", method = RequestMethod.GET)
	SaleDeliveryScheduleResult getScheduleResult(
			@RequestParam("orderInfoId") Long orderInfoId, 
			@RequestParam("goodsSkuId") Long goodsSkuId);
	
}
