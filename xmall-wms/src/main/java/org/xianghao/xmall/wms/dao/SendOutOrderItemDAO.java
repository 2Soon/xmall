package org.xianghao.xmall.wms.dao;

import org.xianghao.xmall.wms.domain.SendOutOrderItemDO;

import java.util.List;

/**
 * 发货单条目管理DAO接口
 * @author xianghao
 *
 */
public interface SendOutOrderItemDAO {

	/**
	 * 新增发货单条目
	 * @param sendOutOrderItem 发货单条目
	 * @throws Exception
	 */
	void save(SendOutOrderItemDO sendOutOrderItem) throws Exception;
	
	/**
	 * 查询发货单条目
	 * @param sendOutOrderId 发货单id
	 * @return 发货单条目
	 * @throws Exception
	 */
	List<SendOutOrderItemDO> listByOrderInfoId(Long sendOutOrderId) throws Exception;
	
}
