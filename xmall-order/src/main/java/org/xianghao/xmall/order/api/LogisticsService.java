package org.xianghao.xmall.order.api;

import org.xianghao.xmall.api.api.logistics.LogisticsApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 物流服务
 * @author xianghao
 *
 */
@FeignClient("xmall-logistics")
public interface LogisticsService extends LogisticsApi {

}
