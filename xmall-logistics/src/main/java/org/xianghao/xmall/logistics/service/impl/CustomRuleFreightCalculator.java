package org.xianghao.xmall.logistics.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.common.json.JsonExtractor;
import org.xianghao.xmall.logistics.domain.FreightTemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义规则运费计算器
 * @author xianghao
 *
 */
@Component
public class CustomRuleFreightCalculator implements FreightCalculator {

	@Autowired
	private JsonExtractor jsonExtractor;
	
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
		String province = getProvinceFromAddress(order.getDeliveryAddress());
		Double totalGrossWeight = orderItem.getGoodsGrossWeight() * orderItem.getPurchaseQuantity();
		
		JSONArray rules = JSONArray.parseArray(freightTemplate.getRule());
		
		for(int i = 0; i < rules.size(); i++) {
			JSONObject rule = rules.getJSONObject(i);
			String provinces = jsonExtractor.getString(rule, "provinces"); 
			
			if(!provinces.contains(province)) {
				continue;
			}
			
			Double threshold = jsonExtractor.getDouble(rule, "threshold"); 
			Double thresholdFreight = jsonExtractor.getDouble(rule, "threshold_freight"); 
			Double incrStep = jsonExtractor.getDouble(rule, "incr_step"); 
			Double incrFreight = jsonExtractor.getDouble(rule, "incr_freight"); 
			
			if(totalGrossWeight <= threshold) {
				return thresholdFreight;
			} else {
				return thresholdFreight + (totalGrossWeight - threshold) / incrStep * incrFreight;
			}
		}
		
		return 0.0;
	}
	
	/**
	 * 从地址中提取省份
	 * @param address 地址
	 * @return 省份
	 */
	private String getProvinceFromAddress(String address) {
		return address.substring(0, address.indexOf("省"));  
	}
	
}
