package org.xianghao.xmall.api.api.pay;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 支付中心接口
 * @author xianghao
 *
 */
@RequestMapping("/pay")  
public interface PayApi {

	/**
	 * 获取支付二维码
	 * @param order 订单
	 * @return 支付二维码
	 */
	@RequestMapping(value = "/getQrCode", method = RequestMethod.GET)
	String getQrCode(@RequestBody OrderInfoDTO order);
	
	/**
	 * 进行退款
	 * @param returnGoodsInputOrder 退货入库单
	 * @return 退款结果
	 */
	@RequestMapping(value = "/v", method = RequestMethod.PUT)
	Boolean refund(@RequestBody ReturnGoodsInputOrderDTO returnGoodsInputOrder);
	
}
