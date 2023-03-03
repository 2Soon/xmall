package org.xianghao.xmall.api.api.commodity;

import org.xianghao.xmall.api.domain.commodity.GoodsDTO;
import org.xianghao.xmall.api.domain.commodity.GoodsSkuDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 商品中心对外提供的接口
 * @author xianghao
 *
 */
@RequestMapping("/commodity")  
public interface CommodityApi {

	/**
	 * 根据id查询商品sku
	 * @param goodsSkuId 商品sku id
	 * @return 商品sku DTO
	 */
	@RequestMapping(value = "/goodsSku/{goodsSkuId}", method = RequestMethod.GET)
	GoodsSkuDTO getGoodsSkuById(@PathVariable("goodsSkuId") Long goodsSkuId);
	
	/**
	 * 根据id查询商品
	 * @param goodsId 商品id
	 * @return 商品
	 */
	@RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.GET)
	GoodsDTO getGoodsById(@PathVariable("goodsId") Long goodsId);
	
}
