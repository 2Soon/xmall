package org.xianghao.xmall.wms.service.impl;


import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;

/**
 * 采购入库单处理handler的抽象基类
 * @author xianghao
 *
 */
public abstract class AbstractPurchaseInputOrderHandler implements PurchaseInputOrderHandler {

	/**
	 * 下一个采购入库单处理handler
	 */
	protected PurchaseInputOrderHandler successor;
	
	/**
	 * 执行下一个handler
	 * @param purchaseInputOrder 采购入库单
	 * @return 处理结果
	 * @throws Exception
	 */
	@Override
	public Boolean execute(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
		PurchaseInputOrderHandlerResult result = doExecute(purchaseInputOrder);
		if(successor != null && result.getDoNext()) { 
			return successor.execute(purchaseInputOrder);
		} else {
			return result.getResult();
		}
	}
	
	/**
	 * 执行当前handler的处理逻辑
	 * @param purchaseInputOrder 采购入库单 
	 * @return 处理结果
	 * @throws Exception
	 */
	protected abstract PurchaseInputOrderHandlerResult doExecute(
			PurchaseInputOrderDTO purchaseInputOrder) throws Exception;  

	public PurchaseInputOrderHandler getSuccessor() {
		return successor;
	}

	public void setSuccessor(PurchaseInputOrderHandler successor) {
		this.successor = successor;
	}
	
}
