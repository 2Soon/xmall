package org.xianghao.xmall.finance.schedule;

import org.xianghao.xmall.api.constant.purchase.SettlementPeriod;
import org.xianghao.xmall.api.domain.purchase.SupplierDTO;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.finance.api.PurchaseService;
import org.xianghao.xmall.finance.dao.PurchaseSettlementOrderDAO;
import org.xianghao.xmall.finance.domain.PurchaseSettlementOrderDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 周结算任务
 * @author xianghao
 *
 */
@Component
public class WeekSettlementTask {

	private static final Logger logger = LoggerFactory.getLogger(WeekSettlementTask.class);
	
	/**
	 * 采购中心接口
	 */
	@Autowired
	private PurchaseService purchaseService;
	/**
	 * 采购结算单管理DAO组件
	 */
	@Autowired
	private PurchaseSettlementOrderDAO purchaseSettlementOrderDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 每周运行一次
	 * 
	 * cron
	 * 
	 * 秒 分 时 日 月 周
	 * 
	 * 每个月的每周的周一的0点0分0秒，会跑一次
	 * 
	 */
	@Scheduled(cron = "0 0 0 ? * MON")
	public void execute() {
		try {
			List<SupplierDTO> suppliers = purchaseService.listSuppliersBySettlementPeriod(
					SettlementPeriod.WEEK);
			
			for(SupplierDTO supplier : suppliers) { 
				Date endTime = dateProvider.getCurrentTime();
				Date startTime = new Date(endTime.getTime() - 7 * 24 * 60 * 60 * 1000);  
				
				List<PurchaseSettlementOrderDO> purchaseSettlementOrders = purchaseSettlementOrderDAO
						.listFinishedBySettlementPeriod(supplier.getId(), startTime, endTime);
				
				Double totalSettlementAmount = 0.0;
				for(PurchaseSettlementOrderDO purchaseSettlementOrder : purchaseSettlementOrders) {
					totalSettlementAmount += purchaseSettlementOrder.getTotalSettlementAmount();
				}
				
				payForSupplier(supplier.getBankName(), supplier.getBankAccount(), 
						supplier.getBankAccountHolder(), totalSettlementAmount); 
			}
		} catch (Exception e) {
			logger.error("error", e); 
		}
	}
	
	/**
	 * 向供应商支付采购货款
	 * @param bankName 银行名称
	 * @param bankAccount 银行账号
	 * @param bankAccountHolder 银行账号持有人
	 * @param totalSettlementAmount 总结算金额
	 */
	private void payForSupplier(String bankName, String bankAccount, 
			String bankAccountHolder, Double totalSettlementAmount) {
		
	}
	
}
