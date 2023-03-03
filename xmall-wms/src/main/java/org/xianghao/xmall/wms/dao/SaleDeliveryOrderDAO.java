package org.xianghao.xmall.wms.dao;

import org.xianghao.xmall.wms.domain.SaleDeliveryOrderDO;
import org.xianghao.xmall.wms.domain.SaleDeliveryOrderQuery;

import java.util.List;

/**
 * 销售出库单管理DAO接口
 * @author xianghao
 *
 */
public interface SaleDeliveryOrderDAO {

	/**
	 * 新增销售出库单
	 * @param saleDeliveryOrder
	 * @return 销售出库单id
	 * @throws Exception
	 */
	Long save(SaleDeliveryOrderDO saleDeliveryOrder) throws Exception;
	
	/**
	 * 分页查询销售出库单
	 * @param query 查询条件
	 * @return 销售出库单
	 * @throws Exception
	 */
	List<SaleDeliveryOrderDO> listByPage(SaleDeliveryOrderQuery query) throws Exception;
	
	/**
	 * 根据id查询销售出库单
	 * @param id 销售出库单id
	 * @return 销售出库单
	 * @throws Exception
	 */
	SaleDeliveryOrderDO getById(Long id) throws Exception;
	
	/**
	 * 根据订单id查询销售出库单
	 * @param orderId 订购单id
	 * @return 销售出库单
	 * @throws Exception
	 */
	SaleDeliveryOrderDO getByOrderId(Long orderId) throws Exception;
	
	/**
	 * 更新销售出库单
	 * @param saleDeliveryOrder 销售出库单
	 * @throws Exception
	 */
	void update(SaleDeliveryOrderDO saleDeliveryOrder) throws Exception;
	
}
