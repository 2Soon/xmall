package org.xianghao.xmall.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.xianghao.xmall.api.domain.commodity.GoodsSkuDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.api.domain.promotion.PromotionActivityDTO;
import org.xianghao.xmall.common.json.JsonExtractor;
import org.xianghao.xmall.order.api.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 满赠类型的促销胡活动的处理器
 * @author xianghao
 *
 */
@Component
public class ReachGiftPromotionActivityCalculator 
		extends AbstractGiftPromotionActivityCalculator 
		implements PromotionActivityCalculator {

	/**
	 * 商品服务
	 */
	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private JsonExtractor jsonExtractor;
	
	/**
	 * 计算促销活动的减免金额
	 */
	@Override
	public PromotionActivityResult calculate(OrderItemDTO item,
											 PromotionActivityDTO promotionActivity) throws Exception {
		Double totalAmount = item.getPurchaseQuantity() * item.getPurchasePrice();
		
		JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
		Double thresholdAmount = jsonExtractor.getDouble(rule, "thresholdAmount"); 
		JSONArray giftGoodsSkuIds = rule.getJSONArray("giftGoodsSkuIds");
		
		if(totalAmount > thresholdAmount) {
			PromotionActivityResult result = new PromotionActivityResult();
			
			for(int i = 0; i < giftGoodsSkuIds.size(); i++) {
				Long goodsSkuId = giftGoodsSkuIds.getLong(i);
				GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(goodsSkuId);
				result.getOrderItems().add(createOrderItem(goodsSku));
			} 
			
			return result;
		}
		
		return new PromotionActivityResult(); 
	}
	
}
