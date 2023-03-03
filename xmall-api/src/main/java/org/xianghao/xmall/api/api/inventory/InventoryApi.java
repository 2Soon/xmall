package org.xianghao.xmall.api.api.inventory;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderDTO;
import org.springframework.web.bind.annotation.*;

/**
 * 库存中心对外提供的接口
 * @author xianghao
 *
 */
@RequestMapping("/inventory")  
public interface InventoryApi {

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
	 * 查询商品sku的库存
	 * @param goodsSkuId 商品sku id
	 * @return 商品sku的库存
	 */
	@RequestMapping(value = "/getSaleStockQuantity/{goodsSkuId}", method = RequestMethod.GET)
	Long getSaleStockQuantity(@PathVariable("goodsSkuId") Long goodsSkuId);
	
	/**
	 * 设置销售库存
	 * @param goodsSkuId 商品sku id
	 * @param saleStockQuantity 销售库存
	 * @return 处理结果
	 */
	@RequestMapping(value = "/setSaleStockQuantity", method = RequestMethod.PUT)
	Boolean setSaleStockQuantity(@RequestParam("goodsSkuId") Long goodsSkuId, 
			@RequestParam("saleStockQuantity") Long saleStockQuantity);
	
}
