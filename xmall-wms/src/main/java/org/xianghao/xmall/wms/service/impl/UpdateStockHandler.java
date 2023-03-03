package org.xianghao.xmall.wms.service.impl;

import org.xianghao.xmall.api.constant.wms.WmsStockUpdateEvent;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.wms.stock.WmsStockUpdater;
import org.xianghao.xmall.wms.stock.WmsStockUpdaterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 更新本地库存的handler
 * @author xianghao
 *
 */
@Component
public class UpdateStockHandler extends AbstractPurchaseInputOrderHandler {

	/**
	 * 库存更新组件工厂
	 */
	@Autowired
	private WmsStockUpdaterFactory stockUpdaterFactory;
	
	/**
	 * 执行处理逻辑
	 */
	@Override
	public PurchaseInputOrderHandlerResult doExecute(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
		WmsStockUpdater stockUpdater = stockUpdaterFactory.create(
				WmsStockUpdateEvent.PURCHASE_INPUT, purchaseInputOrder);
		stockUpdater.update();
		return new PurchaseInputOrderHandlerResult(true); 
	}

}
