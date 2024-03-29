package org.xianghao.xmall.api.state;

import org.xianghao.xmall.api.constant.GoodsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xianghao.xmall.api.domain.commodity.GoodsDTO;

/**
 * 商品状态工厂
 * @author xianghao
 *
 */
@Component
public class GoodsStateFactory {

	/**
	 * 待审核状态
	 */
	@Autowired
	private WaitForApproveGoodsState waitForApproveGoodsState;
	/**
	 * 待上架状态
	 */
	@Autowired
	private WaitForPutOnShelvesGoodsState waitForPutOnShelvesGoodsState;
	/**
	 * 审核未通过状态
	 */
	@Autowired
	private ApproveRejectGoodsState approveRejectGoodsState;
	/**
	 * 已上架状态
	 */
	@Autowired
	private PuttedOnShelvesGoodsState puttedOnShelvesGoodsState;
	/**
	 * 默认商品状态
	 */
	@Autowired
	private DefaultGoodsState defaultGoodsState;
	
	/**
	 * 获取商品对应的状态组件
	 * @param goods 商品 
	 * @return 状态组件
	 * @throws Exception
	 */
	public GoodsState get(GoodsDTO goods) throws Exception {
		if(GoodsStatus.WAIT_FOR_APPROVE.equals(goods.getStatus())) {
			return waitForApproveGoodsState;
		} else if(GoodsStatus.WAIT_FOR_PUT_ON_SHELVES.equals(goods.getStatus())) {
			return waitForPutOnShelvesGoodsState;
		} else if(GoodsStatus.APPROVE_REJECT.equals(goods.getStatus())) {
			return approveRejectGoodsState;
		} else if(GoodsStatus.PUTTED_ON_SHELVES.equals(goods.getStatus())) {
			return puttedOnShelvesGoodsState;
		} else {
			return defaultGoodsState;
		}
	}
	
}
