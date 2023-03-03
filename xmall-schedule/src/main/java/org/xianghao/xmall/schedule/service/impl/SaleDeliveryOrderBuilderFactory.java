package org.xianghao.xmall.schedule.service.impl;

import org.xianghao.xmall.common.bean.SpringApplicationContext;
import org.xianghao.xmall.schedule.service.SaleDeliveryOrderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 销售出库单builder工厂
 * @author xianghao
 *
 */
@Component
public class SaleDeliveryOrderBuilderFactory {

	/**
	 * spring容器
	 */
	@Autowired
	private SpringApplicationContext context;
	
	/**
	 * 获取一个销售出库单builder
	 * @return 销售出库单builder
	 */
	public SaleDeliveryOrderBuilder get() {
		return context.getBean(DefaultSaleDeliveryOrderBuilder.class);
	}
	
}
