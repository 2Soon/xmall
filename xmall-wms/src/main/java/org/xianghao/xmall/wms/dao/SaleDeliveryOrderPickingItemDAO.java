package org.xianghao.xmall.wms.dao;

import org.xianghao.xmall.wms.domain.SaleDeliveryOrderPickingItemDO;

import java.util.List;

/**
 * 销售出库单拣货条目管理DAO接口
 * @author xianghao
 *
 */
public interface SaleDeliveryOrderPickingItemDAO {

	/**
	 * 新增销售出库单拣货条目
	 * @param pickingItem 拣货条目
	 * @throws Exception
	 */
	void save(SaleDeliveryOrderPickingItemDO pickingItem) throws Exception;
	
	/**
	 * 根据销售出库单条目id查询拣货条目
	 * @param saleDeliveryOrderItemId 销售出库单条目id
	 * @return 拣货条目
	 * @throws Exception
	 */
	List<SaleDeliveryOrderPickingItemDO> listBySaleDeliveryOrderItemId(
			Long saleDeliveryOrderItemId) throws Exception;
	
}
