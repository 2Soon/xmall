package org.xianghao.xmall.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.xianghao.xmall.api.domain.commodity.GoodsSkuDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.api.domain.promotion.PromotionActivityDTO;
import org.xianghao.xmall.order.api.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 赠品促销类型的促销活动的价格计算组件
 * @author xianghao
 *
 */
@Component
public class DirectGiftPromotionActivityCalculator 
		extends AbstractGiftPromotionActivityCalculator 
		implements PromotionActivityCalculator {

	/**
	 * 商品服务
	 */
	@Autowired
	private CommodityService commodityService;
	
	@Override
	public PromotionActivityResult calculate(OrderItemDTO item,
											 PromotionActivityDTO promotionActivity) {
		JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
		JSONArray giftGoodsSkuIds = rule.getJSONArray("giftGoodsSkuIds");
		
		PromotionActivityResult result = new PromotionActivityResult();
		
		for(int i = 0; i < giftGoodsSkuIds.size(); i++) {
			Long goodsSkuId = giftGoodsSkuIds.getLong(i);
			GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(goodsSkuId);
			result.getOrderItems().add(createOrderItem(goodsSku));
		} 
		
		return result;
	}

}
