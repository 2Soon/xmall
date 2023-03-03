package org.xianghao.xmall.wms.dao.impl;

import org.xianghao.xmall.wms.dao.SendOutOrderDAO;
import org.xianghao.xmall.wms.domain.SendOutOrderDO;
import org.xianghao.xmall.wms.mapper.SendOutOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 发货单管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class SendOutOrderDAOImpl implements SendOutOrderDAO {

	/**
	 * 发货单管理mapper组件
	 */
	@Autowired
	private SendOutOrderMapper sendOutOrderMapper;
	
	/**
	 * 新增发货单
	 * @param order
	 */
	@Override
	public Long save(SendOutOrderDO sendOutOrder) throws Exception {
		sendOutOrderMapper.save(sendOutOrder); 
		return sendOutOrder.getId();
	}
	
	/**
	 * 根据id查询发货单
	 * @param id 发货单id
	 * @return 发货单
	 */
	@Override
	public SendOutOrderDO getBySaleDeliveryOrderId(Long saleDeliveryOrderId) throws Exception {
		return sendOutOrderMapper.getBySaleDeliveryOrderId(saleDeliveryOrderId);
	}
	
}
