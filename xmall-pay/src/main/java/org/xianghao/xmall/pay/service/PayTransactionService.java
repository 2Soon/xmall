package org.xianghao.xmall.pay.service;

import org.xianghao.xmall.pay.domain.PayTransactionDTO;
import org.xianghao.xmall.pay.domain.PayTransactionQuery;

import java.util.List;

/**
 * 支付交易流水管理service接口
 * @author xianghao
 *
 */
public interface PayTransactionService {

	/**
	 * 新增支付交易流水
	 * @param payTransaction 支付交易流水
	 * @throws Exception
	 */
	void save(PayTransactionDTO payTransaction) throws Exception;
	
	/**
	 * 更新支付交易流水
	 * @param payTransaction 支付交易流水
	 * @throws Exception
	 */
	void update(PayTransactionDTO payTransaction) throws Exception;
	
	/**
	 * 根据订单编号查询支付交易流水
	 * @param orderNo 订单编号
	 * @return 支付交易流水
	 * @throws Exception
	 */
	PayTransactionDTO getByOrderNo(String orderNo) throws Exception;
	
	/**
	 * 分页查询支付交易流水
	 * @param query 查询条件
	 * @return 支付交易流水
	 * @throws Exception
	 */
	List<PayTransactionDTO> listByPage(PayTransactionQuery query) throws Exception; 
	
}
