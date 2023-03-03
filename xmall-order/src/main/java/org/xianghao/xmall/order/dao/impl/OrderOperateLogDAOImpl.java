package org.xianghao.xmall.order.dao.impl;

import org.xianghao.xmall.order.dao.OrderOperateLogDAO;
import org.xianghao.xmall.order.domain.OrderOperateLogDO;
import org.xianghao.xmall.order.mapper.OrderOperateLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单操作日志管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class OrderOperateLogDAOImpl implements OrderOperateLogDAO {

	/**
	 * 订单操作日志管理mapper组件
	 */
	@Autowired
	private OrderOperateLogMapper logMapper;
	
	/**
	 * 新增订单操作日志
	 * @param log 订单操作日志
	 */
	@Override
	public void save(OrderOperateLogDO log) throws Exception {
		logMapper.save(log); 
	}
	
	/**
	 * 查询订单操作日志
	 * @param orderInfoId 订单id
	 * @return 订单操作日志
	 */
	@Override
	public List<OrderOperateLogDO> listByOrderInfoId(Long orderInfoId) throws Exception {
		return logMapper.listByOrderInfoId(orderInfoId);
	}
	
}
