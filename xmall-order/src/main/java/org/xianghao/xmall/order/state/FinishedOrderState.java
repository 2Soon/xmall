package org.xianghao.xmall.order.state;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.order.constant.OrderStatus;
import org.xianghao.xmall.order.dao.OrderInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 已完成状态
 * @author xianghao
 *
 */
@Component
public class FinishedOrderState extends AbstractOrderState {

	@Autowired
	public FinishedOrderState(DateProvider dateProvider, OrderInfoDAO orderInfoDAO) {
		super(dateProvider, orderInfoDAO);
	}

	@Override
	protected Integer getOrderStatus(OrderInfoDTO order) throws Exception {
		return OrderStatus.FINISHED;
	}
	
	@Override
	public Boolean canApplyReturnGoods(OrderInfoDTO order) throws Exception {
		return true;
	}

}
