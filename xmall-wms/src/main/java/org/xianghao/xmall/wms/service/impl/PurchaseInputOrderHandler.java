package org.xianghao.xmall.wms.service.impl;


import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;

/**
 * 采购入库单处理的handler接口
 * @author xianghao
 *
 */
public interface PurchaseInputOrderHandler {

	/**
	 * 执行处理逻辑
	 * @param purchaseInputOrder 采购入库单
	 * @return 处理结果
	 * @throws Exception
	 */
	Boolean execute(PurchaseInputOrderDTO purchaseInputOrder) throws Exception;
	
}
