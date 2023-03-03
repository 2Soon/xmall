package org.xianghao.xmall.wms.dao.impl;

import org.xianghao.xmall.wms.dao.SaleDeliveryOrderSendOutDetailDAO;
import org.xianghao.xmall.wms.domain.SaleDeliveryOrderSendOutDetailDO;
import org.xianghao.xmall.wms.mapper.SaleDeliveryOrderSendOutDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 销售出库单发货明细管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class SaleDeliveryOrderSendOutDetailDAOImpl implements SaleDeliveryOrderSendOutDetailDAO {

	/**
	 * 销售出库单发货明细管理Mapper组件
	 */
	@Autowired
	private SaleDeliveryOrderSendOutDetailMapper sendOutDetailMapper;
	
	/**
	 * 新增销售出库单发货明细
	 * @param sendOutDetail 销售出库单发货明细
	 */
	@Override
	public void save(SaleDeliveryOrderSendOutDetailDO sendOutDetail) throws Exception {
		sendOutDetailMapper.save(sendOutDetail); 
	}
	
	/**
	 * 根据销售出库单条目id查询发货明细
	 * @param saleDeliveryOrderItemId 销售出库单id
	 * @return 发货明细
	 */
	@Override
	public List<SaleDeliveryOrderSendOutDetailDO> listBySaleDeliveryOrderItemId(
			Long saleDeliveryOrderItemId) throws Exception {
		return sendOutDetailMapper.listBySaleDeliveryOrderItemId(saleDeliveryOrderItemId);
	}
	
}
