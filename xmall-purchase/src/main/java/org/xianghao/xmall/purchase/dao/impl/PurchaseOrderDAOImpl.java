package org.xianghao.xmall.purchase.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.purchase.dao.PurchaseOrderDAO;
import org.xianghao.xmall.purchase.domain.PurchaseOrderDO;
import org.xianghao.xmall.purchase.domain.PurchaseOrderQuery;
import org.xianghao.xmall.purchase.mapper.PurchaseOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购单管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {

	/**
	 * 采购单管理mapper组件
	 */
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 新增采购单
	 * @param purchaseOrder 采购单
	 */
	@Override
	public Long save(PurchaseOrderDO purchaseOrder) throws Exception {
		purchaseOrder.setGmtCreate(dateProvider.getCurrentTime()); 
		purchaseOrder.setGmtModified(dateProvider.getCurrentTime()); 
		purchaseOrderMapper.save(purchaseOrder); 
		return purchaseOrder.getId();
	}
	
	/**
	 * 分页查询采购单
	 * @param query 查询条件
	 * @return 采购单
	 */
	@Override
	public List<PurchaseOrderDO> listByPage(PurchaseOrderQuery query) throws Exception {
		return purchaseOrderMapper.listByPage(query);
	}
	
	/**
	 * 根据id查询采购单
	 * @param id 采购单id
	 * @return 采购单
	 */
	@Override
	public PurchaseOrderDO getById(Long id) throws Exception {
		return purchaseOrderMapper.getById(id);
	}
	
	/**
	 * 更新采购单
	 * @param purchaseOrder 采购单
	 */
	@Override
	public void update(PurchaseOrderDO purchaseOrder) throws Exception {
		purchaseOrder.setGmtModified(dateProvider.getCurrentTime()); 
		purchaseOrderMapper.update(purchaseOrder); 
	}
	
	/**
	 * 更新采购单的状态
	 * @param id 采购单id
	 * @param status 采购单状态
	 * @throws Exception
	 */
	@Override
	public void updateStatus(Long id, Integer status) throws Exception {
		PurchaseOrderDO purchaseOrder = getById(id);
		purchaseOrder.setStatus(status); 
		update(purchaseOrder); 
	}
	
}
