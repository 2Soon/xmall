package org.xianghao.xmall.wms.service.impl;

import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.wms.api.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通知采购中心的handler
 * @author xianghao
 *
 */
@Component
public class InformPurchaseCenterHandler extends AbstractPurchaseInputOrderHandler {
	
	/**
	 * 采购服务
	 */
	@Autowired
	private PurchaseService purchaseService;
	
	/**
	 * 执行处理逻辑
	 */
	@Override
	public PurchaseInputOrderHandlerResult doExecute(
			PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
		purchaseService.informFinishedPurchaseInputOrderEvent(
				purchaseInputOrder.getPurchaseOrderId());
		return new PurchaseInputOrderHandlerResult(true); 
	}

}
