package org.xianghao.xmall.wms.dao;

import org.xianghao.xmall.wms.domain.SaleDeliveryOrderItemDO;

import java.util.List;

/**
 * 销售出库单管理DAO接口
 * @author xianghao
 *
 */
public interface SaleDeliveryOrderItemDAO {

	/**
	 * 新增销售出库单条目
	 * @param saleDeliveryOrderItem 销售出库单条目
	 * @return 销售出库单条目id
	 * @throws Exception
	 */
	Long save(SaleDeliveryOrderItemDO saleDeliveryOrderItem) throws Exception;
	
	/**
	 * 根据销售出库单id查询销售出库单条目
	 * @param saleDeliveryOrderId 销售出库单idi
	 * @return 销售出库单条目
	 * @throws Exception
	 */
	List<SaleDeliveryOrderItemDO> listBySaleDeliveryOrderId(
			Long saleDeliveryOrderId) throws Exception;
	
}
