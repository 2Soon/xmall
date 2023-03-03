package org.xianghao.xmall.api.api.logistics;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.wms.LogisticOrderDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 物流服务接口
 * @author xianghao
 *
 */
@RequestMapping("/logistics")  
public interface LogisticsApi {

	/**
	 * 计算商品sku的运费
	 * @param order 订单
	 * @return 商品sku的运费
	 */
	@RequestMapping(value = "/calculateFreight", method = RequestMethod.PUT)
	Double calculateFreight(@RequestBody OrderInfoDTO order);
	
	/**
	 * 申请物流单
	 * @param order 订单
	 * @return 物流单
	 */
	@RequestMapping(value = "/applyLogisticOrder", method = RequestMethod.POST)
	LogisticOrderDTO applyLogisticOrder(@RequestBody OrderInfoDTO order);
	
	/**
	 * 获取订单的签收时间
	 * @param orderId 订单id
	 * @param orderNo 订单编号
	 * @return 签收时间
	 */
	@RequestMapping(value = "/getSignedTime", method = RequestMethod.GET)
	Date getSignedTime(@RequestParam("orderId") Long orderId, 
			@RequestParam("orderNo") String orderNo);
	
}
