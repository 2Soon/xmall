package org.xianghao.xmall.wms.dao.impl;

import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.wms.dao.ReturnGoodsInputOrderPutOnItemDAO;
import org.xianghao.xmall.wms.domain.ReturnGoodsInputOrderPutOnItemDO;
import org.xianghao.xmall.wms.mapper.ReturnGoodsInputOrderPutOnItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 退货入库单上架条目管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class ReturnGoodsInputOrderPutOnItemDAOImpl implements ReturnGoodsInputOrderPutOnItemDAO {

	/**
	 * 上架条目管理mapper组件
	 */
	@Autowired
	private ReturnGoodsInputOrderPutOnItemMapper putOnItemMapper;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 根据退货入库单条目id查询上架条目
	 * @param returnGoodsInputOrderItemId 退货入库单条目id
	 * @return 上架条目
	 */
	@Override
	public List<ReturnGoodsInputOrderPutOnItemDO> listByReturnGoodsInputOrderItemId(
			Long returnGoodsInputOrderItemId) throws Exception {
		return putOnItemMapper.listByReturnGoodsInputOrderItemId(returnGoodsInputOrderItemId);
	}
	
	/**
	 * 新增退货入库单上架条目
	 * @param putOnItem 上架条目
	 */
	@Override
 	public void save(ReturnGoodsInputOrderPutOnItemDO putOnItem) throws Exception {
 		putOnItem.setGmtCreate(dateProvider.getCurrentTime());
 		putOnItem.setGmtModified(dateProvider.getCurrentTime()); 
 		putOnItemMapper.save(putOnItem); 
 	}
	
}
