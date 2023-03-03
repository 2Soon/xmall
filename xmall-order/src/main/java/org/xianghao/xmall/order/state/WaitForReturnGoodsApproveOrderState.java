package org.xianghao.xmall.order.state;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.order.constant.OrderStatus;
import org.xianghao.xmall.order.dao.OrderInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 等待退货申请审核状态
 * @author xianghao
 *
 */
@Component
public class WaitForReturnGoodsApproveOrderState extends AbstractOrderState {

	@Autowired
	public WaitForReturnGoodsApproveOrderState(DateProvider dateProvider, OrderInfoDAO orderInfoDAO) {
		super(dateProvider, orderInfoDAO);
	}

	@Override
	protected Integer getOrderStatus(OrderInfoDTO order) throws Exception {
		return OrderStatus.WAIT_FOR_RETURN_GOODS_APPROVE;
	}
	
}
