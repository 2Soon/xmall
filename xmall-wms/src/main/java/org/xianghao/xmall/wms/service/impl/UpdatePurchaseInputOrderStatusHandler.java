package org.xianghao.xmall.wms.service.impl;

import org.xianghao.xmall.api.constant.wms.PurchaseInputOrderStatus;
import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.wms.dao.PurchaseInputOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 更新采购入库单状态的handler
 * @author xianghao
 *
 */
@Component
public class UpdatePurchaseInputOrderStatusHandler extends AbstractPurchaseInputOrderHandler {
	
	/**
	 * 采购入库单管理DAO组件
	 */
	@Autowired
	private PurchaseInputOrderDAO purchaseInputOrderDAO;
	
	/**
	 * 执行处理逻辑
	 * @param purchaseInputOrder 采购入库单
	 * @return 处理结果
	 * @throws Exception
	 */
	@Override
	public PurchaseInputOrderHandlerResult doExecute(
			PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
		purchaseInputOrderDAO.updateStatus(purchaseInputOrder.getId(),
				PurchaseInputOrderStatus.FINISH_INPUT);
		return new PurchaseInputOrderHandlerResult(true);  
	}
	
}
