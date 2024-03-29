package org.xianghao.xmall.pay.schedule;

import org.xianghao.xmall.common.constant.CollectionSize;
import org.xianghao.xmall.pay.constant.PayTransactionStatus;
import org.xianghao.xmall.pay.dao.PayTransactionDAO;
import org.xianghao.xmall.pay.domain.PayTransactionDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 未支付订单超时检查任务
 * @author xianghao
 *
 */
@Component
public class UnpayedTimeoutTask {

	private static final Logger logger = LoggerFactory.getLogger(UnpayedTimeoutTask.class);
	
	/**
	 * 支付交易流水管理DAO组件
	 */
	@Autowired
	private PayTransactionDAO payTransactionDAO;
	
	@Scheduled(fixedRate = 10 * 1000)
	public void execute() {
		try {
			List<PayTransactionDO> payTransactions = listUnpayedTransactions();
			
			for(PayTransactionDO payTransaction : payTransactions) {  
				if(System.currentTimeMillis() - payTransaction.getGmtCreate().getTime() > 30 * 60 * 1000) {
					payTransaction.setStatus(PayTransactionStatus.CLOSED); 
					payTransactionDAO.update(payTransaction); 
				}
			}
		} catch (Exception e) {
			logger.error("error", e); 
		}
	}
	
	/**
	 * 查询未支付的支付宝交易流水
	 * @return 交易流水
	 */ 
	private List<PayTransactionDO> listUnpayedTransactions() throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>(CollectionSize.DEFAULT);
		parameters.put("status", PayTransactionStatus.UNPAYED);
		return payTransactionDAO.listByCondition(parameters);
	}
	
}
