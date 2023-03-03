package org.xianghao.xmall.cart.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.xianghao.xmall.api.api.commodity.CommodityApi;

/**
 * 商品服务
 * @author xianghao
 *
 */
@FeignClient("eshop-commodity")
public interface CommodityService extends CommodityApi {

}
