package org.xianghao.xmall.wms.service.impl;

import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.wms.api.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通知财务中心的handler
 * @author xianghao
 *
 */
@Component
public class InformFinanceCenterHandler extends AbstractPurchaseInputOrderHandler {

	/**
	 * 财务服务
	 */
	@Autowired
	private FinanceService financeService;
	
	/**
	 * 执行处理结果
	 */
	@Override
	public PurchaseInputOrderHandlerResult doExecute(
			PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
		financeService.createPurchaseSettlementOrder(purchaseInputOrder);
		return new PurchaseInputOrderHandlerResult(true); 
	}

}
