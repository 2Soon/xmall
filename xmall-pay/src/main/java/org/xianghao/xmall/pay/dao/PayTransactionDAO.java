package org.xianghao.xmall.pay.dao;

import org.xianghao.xmall.pay.domain.PayTransactionDO;
import org.xianghao.xmall.pay.domain.PayTransactionQuery;

import java.util.List;
import java.util.Map;

/**
 * 支付交易流水管理DAO接口
 * @author xianghao
 *
 */
public interface PayTransactionDAO {

	/**
	 * 新增支付交易流水
	 * @param payTransaction 支付交易流水
	 * @throws Exception
	 */
	void save(PayTransactionDO payTransaction) throws Exception;
	
	/**
	 * 根据条件查询支付交易流水
	 * @param parameters 查询条件
	 * @return 支付交易流水
	 * @throws Exception
	 */
	List<PayTransactionDO> listByCondition(Map<String, Object> parameters) throws Exception; 
	
	/**
	 * 更新支付交易流水
	 * @param payTransaction 支付交易流水
	 * @throws Exception
	 */
	void update(PayTransactionDO payTransaction) throws Exception;
	
	/**
	 * 分页查询支付交易流水
	 * @param query 查询条件
	 * @return 支付交易流水
	 * @throws Exception
	 */
	List<PayTransactionDO> listByPage(PayTransactionQuery query) throws Exception; 
	
}
