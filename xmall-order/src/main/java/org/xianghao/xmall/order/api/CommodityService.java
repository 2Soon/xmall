package org.xianghao.xmall.order.api;

import org.xianghao.xmall.api.api.commodity.CommodityApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 商品服务
 * @author xianghao
 *
 */
@FeignClient("eshop-commodity")
public interface CommodityService extends CommodityApi {

}
