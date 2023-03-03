package org.xianghao.xmall.logistics.service.impl;

import org.xianghao.xmall.logistics.domain.FreightTemplateDTO;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.springframework.stereotype.Component;

/**
 * 默认运费计算器
 * @author xianghao
 *
 */
@Component
public class DefaultLogisticsFreightCalculator implements FreightCalculator {
	
	/**
	 * 计算订单条目的运费
	 * @param freightTemplate 运费模板
	 * @param order 订单
	 * @param orderItem 订单条目
	 * @return 运费
	 * @throws Exception
	 */
	@Override
	public Double calculate(FreightTemplateDTO freightTemplate, 
			OrderInfoDTO order, OrderItemDTO orderItem) throws Exception {
		return 0.0;
	}

}
