package org.xianghao.xmall.order.api;

import org.xianghao.xmall.api.api.promotion.PromotionApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 促销服务
 * @author xianghao
 *
 */
@FeignClient("xmall-promotion")
public interface PromotionService extends PromotionApi {

}
