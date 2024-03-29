package org.xianghao.xmall.customer.service;

import org.xianghao.xmall.api.domain.customer.ReturnGoodsWorksheetDTO;
import org.xianghao.xmall.customer.domain.ReturnGoodsWorksheetQuery;

import java.util.List;

/**
 * 退货工单管理service接口
 * @author xianghao
 *
 */
public interface ReturnGoodsWorksheetService {
	
	/**
	 * 分页查询退货工单
	 * @param query 查询条件
	 * @return 退货工单
	 * @throws Exception
	 */
	List<ReturnGoodsWorksheetDTO> listByPage(ReturnGoodsWorksheetQuery query) throws Exception;
	
	/**
	 * 根据id查询退货工单
	 * @param id 退货工单id
	 * @return 退货工单
	 * @throws Exception
	 */
	ReturnGoodsWorksheetDTO getById(Long id) throws Exception;
	
	/**
	 * 审核退货工单
	 * @param id 退货工单id
	 * @param approveResult 审核结果
	 * @throws Exception
	 */
	void approve(Long id, Integer approveResult) throws Exception;
	
	/**
	 * 确认退货工单已经收到了退货商品
	 * @param id 退货工单id
	 * @throws Exception
	 */
	void confirmReceivedReturnGoods(Long id) throws Exception;
	
}
