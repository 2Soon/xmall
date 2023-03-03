package org.xianghao.xmall.logistics.service.impl;

import org.xianghao.xmall.logistics.constant.FreightTemplateType;
import org.xianghao.xmall.logistics.domain.FreightTemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 运费计算器工厂
 * @author xianghao
 *
 */
@Component
public class FreightCalculatorFactory {

	/**
	 * 固定运费计算器
	 */
	@Autowired
	private FixedFreightCalculator fixedFreightCalculator;
	/**
	 * 满X元包邮运费计算器
	 */
	@Autowired
	private ReachFreeFreightCalculator reachFreeFreightCalculator;
	/**
	 * 自定义规则运费计算器
	 */
	@Autowired
	private CustomRuleFreightCalculator customRuleFreightCalculator;
	/**
	 * 默认运费计算器
	 */
	@Autowired
	private DefaultLogisticsFreightCalculator defaultFreightCalculator;
	
	/**
	 * 获取运费模板对应的运费计算器
	 * @param freightTemplate 运费模板
	 * @return 运费计算器
	 */
	public FreightCalculator get(FreightTemplateDTO freightTemplate) {
		if(FreightTemplateType.FIXED.equals(freightTemplate.getType())) {
			return fixedFreightCalculator;
		} else if(FreightTemplateType.REACH_FREE.equals(freightTemplate.getType())) {
			return reachFreeFreightCalculator;
		} else if(FreightTemplateType.CUSTOM_RULE.equals(freightTemplate.getType())) {
			return customRuleFreightCalculator;
		}
		return defaultFreightCalculator;
	}
	
}
