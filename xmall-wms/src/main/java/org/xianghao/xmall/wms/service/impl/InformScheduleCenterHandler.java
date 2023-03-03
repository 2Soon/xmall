package org.xianghao.xmall.wms.service.impl;

import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.wms.api.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通知调度中心的handler
 * @author xianghao
 *
 */
@Component
public class InformScheduleCenterHandler extends AbstractPurchaseInputOrderHandler {

	/**
	 * 调度中心接口
	 */
	@Autowired
	private ScheduleService scheduleService;
	
	/**
	 * 执行处理逻辑
	 */
	@Override
	public PurchaseInputOrderHandlerResult doExecute(
			PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
		scheduleService.informPurchaseInputFinished(purchaseInputOrder);
		return new PurchaseInputOrderHandlerResult(true); 
	}

}
