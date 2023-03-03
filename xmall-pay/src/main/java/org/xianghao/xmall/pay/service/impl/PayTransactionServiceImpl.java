package org.xianghao.xmall.pay.service.impl;

import org.xianghao.xmall.common.constant.CollectionSize;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.pay.dao.PayTransactionDAO;
import org.xianghao.xmall.pay.domain.PayTransactionDO;
import org.xianghao.xmall.pay.domain.PayTransactionDTO;
import org.xianghao.xmall.pay.domain.PayTransactionQuery;
import org.xianghao.xmall.pay.service.PayTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付交易流水管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PayTransactionServiceImpl implements PayTransactionService {
	
	/**
	 * 支付交易流水管理DAO组件
	 */
	@Autowired
	private PayTransactionDAO payTransactionDAO;

	/**
	 * 新增支付交易流水
	 * @param payTransaction 支付交易流水
	 */
	@Override
	public void save(PayTransactionDTO payTransaction) throws Exception {
		payTransactionDAO.save(payTransaction.clone(PayTransactionDO.class));  
	}
	
	/**
	 * 更新支付交易流水
	 * @param payTransaction 支付交易流水
	 * @throws Exception
	 */
	@Override
	public void update(PayTransactionDTO payTransaction) throws Exception {
		payTransactionDAO.update(payTransaction.clone(PayTransactionDO.class));  
	}
	
	/**
	 * 根据订单编号查询支付交易流水
	 * @param orderNo 订单编号
	 * @return 支付交易流水
	 * @throws Exception
	 */
	@Override
	public PayTransactionDTO getByOrderNo(String orderNo) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>(CollectionSize.DEFAULT);
		parameters.put("orderNo", orderNo);
		
		List<PayTransactionDO> payTransactions = payTransactionDAO.listByCondition(parameters);
		if(!CollectionUtils.isEmpty(payTransactions)) {
			return payTransactions.get(0).clone(PayTransactionDTO.class); 
		}
		
		return null;
	}
	
	/**
	 * 分页查询支付交易流水
	 * @param query 查询条件
	 * @return 支付交易流水
	 */
	@Override
	public List<PayTransactionDTO> listByPage(PayTransactionQuery query) throws Exception {
		return ObjectUtils.convertList(
				payTransactionDAO.listByPage(query), 
				PayTransactionDTO.class); 
	}
	
}
