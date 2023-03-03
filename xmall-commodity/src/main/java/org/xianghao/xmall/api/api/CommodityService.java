package org.xianghao.xmall.api.api;

import org.xianghao.xmall.api.api.commodity.CommodityApi;
import org.xianghao.xmall.api.domain.commodity.GoodsDTO;
import org.xianghao.xmall.api.domain.commodity.GoodsSkuDTO;
import org.xianghao.xmall.api.service.GoodsService;
import org.xianghao.xmall.api.service.GoodsSkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品服务
 * @author xianghao
 *
 */
@RestController
public class CommodityService implements CommodityApi {
	
	private static final Logger logger = LoggerFactory.getLogger(CommodityService.class);
	
	/**
	 * 商品sku管理service组件
	 */
	@Autowired
	private GoodsSkuService goodsSkuService;
	/**
	 * 商品管理service组件
	 */
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 根据id查询商品sku
	 * @param goodsSkuId 商品sku id
	 * @return 商品sku DTO
	 */
	@Override
	public GoodsSkuDTO getGoodsSkuById(Long goodsSkuId) {
		try {
			return goodsSkuService.getById(goodsSkuId);
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 根据id查询商品
	 * @param goodsId 商品id
	 * @return 商品
	 */
	@Override
	public GoodsDTO getGoodsById(Long goodsId) {
		try {
			return goodsService.getById(goodsId); 
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
}
