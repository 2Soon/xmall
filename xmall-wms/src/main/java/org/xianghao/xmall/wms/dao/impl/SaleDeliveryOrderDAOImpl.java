package org.xianghao.xmall.wms.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.wms.dao.SaleDeliveryOrderDAO;
import org.xianghao.xmall.wms.domain.SaleDeliveryOrderDO;
import org.xianghao.xmall.wms.domain.SaleDeliveryOrderQuery;
import org.xianghao.xmall.wms.mapper.SaleDeliveryOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 销售出库单管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class SaleDeliveryOrderDAOImpl implements SaleDeliveryOrderDAO {
	
	/**
	 * 销售出库单管理mapper组件
	 */
	@Autowired
	private SaleDeliveryOrderMapper saleDeliveryOrderMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 新增销售出库单
	 * @param saleDeliveryOrder
	 */
	@Override
	public Long save(SaleDeliveryOrderDO saleDeliveryOrder) throws Exception {
		saleDeliveryOrderMapper.save(saleDeliveryOrder); 
		return saleDeliveryOrder.getId();
	}
	
	/**
	 * 分页查询销售出库单
	 * @param query 查询条件
	 * @return 销售出库单
	 */
	@Override
	public List<SaleDeliveryOrderDO> listByPage(SaleDeliveryOrderQuery query) throws Exception {
		return saleDeliveryOrderMapper.listByPage(query);
	}
	
	/**
	 * 根据id查询销售出库单
	 * @param id 销售出库单id
	 * @return 销售出库单
	 */
	@Override
	public SaleDeliveryOrderDO getById(Long id) throws Exception {
		return saleDeliveryOrderMapper.getById(id);
	}
	
	/**
	 * 根据id查询销售出库单
	 * @param id 销售出库单id
	 * @return 销售出库单
	 */
	@Override
	public SaleDeliveryOrderDO getByOrderId(Long orderId) throws Exception {
		return saleDeliveryOrderMapper.getByOrderId(orderId);
	}
	
	/**
	 * 更新销售出库单
	 * @param saleDeliveryOrder 销售出库单
	 */
	@Override
	public void update(SaleDeliveryOrderDO saleDeliveryOrder) throws Exception {
		saleDeliveryOrder.setGmtModified(dateProvider.getCurrentTime());
		saleDeliveryOrderMapper.update(saleDeliveryOrder); 
	}
	
}
