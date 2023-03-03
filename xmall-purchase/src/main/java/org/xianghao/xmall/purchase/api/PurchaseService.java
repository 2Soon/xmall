package org.xianghao.xmall.purchase.api;

import org.xianghao.xmall.api.api.purchase.PurchaseApi;
import org.xianghao.xmall.api.constant.purchase.PurchaseOrderStatus;
import org.xianghao.xmall.api.domain.purchase.SupplierDTO;
import org.xianghao.xmall.purchase.service.PurchaseOrderService;
import org.xianghao.xmall.purchase.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 采购中心接口
 * @author xianghao
 *
 */
@RestController
public class PurchaseService implements PurchaseApi {
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseService.class);
	
	/**
	 * 供应商管理service组件
	 */
	@Autowired
	private SupplierService supplierService;
	/**
	 * 采购单管理service组件
	 */
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	/**
	 * 通知采购中心，“创建采购入库单”事件发生了
	 * @param purcaseOrderId 采购单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informCreatePurchaseInputOrderEvent(Long purchaseOrderId) {
		try {
			purchaseOrderService.updateStatus(purchaseOrderId, PurchaseOrderStatus.WAIT_FOR_INPUT);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知采购中心，“完成采购入库单”事件发生了
	 * @param purcaseOrderId 采购单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informFinishedPurchaseInputOrderEvent(Long purchaseOrderId) {
		try {
			purchaseOrderService.updateStatus(purchaseOrderId, PurchaseOrderStatus.FINISHED_INPUT);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知采购中心，“创建采购结算单”事件发生了
	 * @param purcaseOrderId 采购单id
	 * @return 处理结果
	 */
	@Override
	public Boolean informCreatePurchaseSettlementOrderEvent(Long purchaseOrderId) {
		try {
			purchaseOrderService.updateStatus(purchaseOrderId, PurchaseOrderStatus.WAIT_FOR_SETTLEMENT);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 通知采购中心，“完成采购结算单”事件发生了
	 * @param purchaseOrderId
	 * @return
	 */
	@Override
	public Boolean informFinishedPurchaseSettlementOrderEvent(Long purchaseOrderId) {
		try {
			purchaseOrderService.updateStatus(purchaseOrderId, PurchaseOrderStatus.FINISHED);
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 根据结算周期来查询供应商
	 * @param settlementPeriod 结算周期
 	 * @return 供应商
	 */
	@Override
	public List<SupplierDTO> listSuppliersBySettlementPeriod(Integer settlementPeriod) {
		try {
			return supplierService.listBySettlementPeriod(settlementPeriod);
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
}
