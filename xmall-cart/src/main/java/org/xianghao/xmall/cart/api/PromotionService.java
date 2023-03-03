package org.xianghao.xmall.cart.api;

import org.springframework.cloud.netflix.feign.FeignClient;

import org.xianghao.xmall.api.api.promotion.PromotionApi;

/**
 * 促销服务
 * @author xianghao
 *
 */
@FeignClient("xmall-promotion")
public interface PromotionService extends PromotionApi {

}
