package org.xianghao.xmall.pay.api;

import org.xianghao.xmall.api.api.pay.PayApi;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderDTO;
import org.xianghao.xmall.pay.api.thirdparty.PayThirdPartyApi;
import org.xianghao.xmall.pay.constant.PayTransactionStatus;
import org.xianghao.xmall.pay.domain.PayTransactionBuilder;
import org.xianghao.xmall.pay.domain.PayTransactionDTO;
import org.xianghao.xmall.pay.service.PayTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付服务
 * @author xianghao
 *
 */
@RestController
public class PayService implements PayApi {
	
	private static final Logger logger = LoggerFactory.getLogger(PayService.class);

	/**
	 * 支付接口
	 */
	@Autowired
	private PayThirdPartyApi payApi;
	/**
	 * 支付交易流水管理service组件
	 */
	@Autowired
	private PayTransactionService payTransactionService;
	
	/**
	 * 获取支付二维码
	 * @param order 订单
	 * @return 支付二维码
	 */
	@Override
	public String getQrCode(OrderInfoDTO order) {
		try {
			String qrCode = payApi.getQrCode(order.getPayType(), 
					order.getOrderNo(), order.getPayableAmount()); ;
			
			PayTransactionDTO payTransaction = PayTransactionBuilder.get()
					.buildOrderRelatedData(order) 
					.initStatus()
					.create();
			payTransactionService.save(payTransaction); 
			
			return qrCode;
		} catch (Exception e) {
			logger.error("error", e); 
		}
		return null;
	}
	
	/**
	 * 进行退款
	 * @param returnGoodsInputOrder 退货入库单
	 * @return 退款结果
	 */
	@Override
	public Boolean refund(ReturnGoodsInputOrderDTO returnGoodsInputOrder) {
		try {
			PayTransactionDTO payTransaction = payTransactionService.getByOrderNo(
					returnGoodsInputOrder.getOrderNo());
			
			payTransaction.setStatus(PayTransactionStatus.REFUND); 
			payTransactionService.update(payTransaction); 
			
			Integer transactionChannel = payTransaction.getTransactionChannel();
			String orderNo = returnGoodsInputOrder.getOrderNo();
			Double refundAmount = returnGoodsInputOrder.getPayableAmount();
			
			return payApi.refund(transactionChannel, orderNo, refundAmount);
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
