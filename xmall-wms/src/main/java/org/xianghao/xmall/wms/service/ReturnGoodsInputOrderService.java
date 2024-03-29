package org.xianghao.xmall.wms.service;

import org.xianghao.xmall.api.domain.wms.ReturnGoodsInputOrderDTO;
import org.xianghao.xmall.wms.domain.ReturnGoodsInputOrderQuery;

import java.util.List;

/**
 * 退货入库单管理service接口
 * @author xianghao
 *
 */
public interface ReturnGoodsInputOrderService {

	/**
	 * 新增退货入库单
	 * @param returnGoodsInputOrder 退货入库单
	 * @throws Exception
	 */
	void save(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception;
	
	/**
	 * 分页查询退货入库单
	 * @param query 查询条件
	 * @return 退货入库单
	 * @throws Exception
	 */
	List<ReturnGoodsInputOrderDTO> listByPage(ReturnGoodsInputOrderQuery query) throws Exception;
	
	/**
	 * 根据id查询退货入库单
	 * @param id 退货入库单id
	 * @return 退货入库单
	 * @throws Exception
	 */
	ReturnGoodsInputOrderDTO getById(Long id) throws Exception;
	
	/**
	 * 更新退货入库单
	 * @param returnGoodsInputOrder 退货入库单
	 * @throws Exception 
	 */
	void update(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception;
	
	/**
	 * 批量新增退货入库单上架条目
	 * @param returnGoodsInputOrder 退货入库单
	 * @throws Exception
	 */
	void batchSavePutOnItems(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception;
	
	/**
	 * 退货入库单提交审核
	 * @param id 退货入库单id
	 * @throws Exception
	 */
	void submitApprove(Long id) throws Exception;
	
	/**
	 * 审核退货入库单
	 * @param id 退货入库单id
	 * @param approveResult 审核结果
	 * @throws Exception
	 */
	void approve(Long id, Integer approveResult) throws Exception;
	
}
