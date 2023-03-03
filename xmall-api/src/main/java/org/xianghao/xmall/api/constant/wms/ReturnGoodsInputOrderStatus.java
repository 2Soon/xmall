package org.xianghao.xmall.api.constant.wms;

/**
 * 退货入库单状态
 * @author xianghao
 *
 */
public class ReturnGoodsInputOrderStatus {

	/**
	 * 编辑中
	 */
	public static final Integer EDITING = 1;
	/**
	 * 待审核
	 */
	public static final Integer WAIT_FOR_APPROVE = 2;
	/**
	 * 已完成
	 */
	public static final Integer FINISHED = 3;
	
	private ReturnGoodsInputOrderStatus() {
		
	}
	
}
