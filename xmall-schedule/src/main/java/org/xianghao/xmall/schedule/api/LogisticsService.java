package org.xianghao.xmall.schedule.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.xianghao.xmall.api.api.logistics.LogisticsApi;

/**
 * 物流服务
 * @author xianghao
 *
 */
@FeignClient("eshop-logistics")
public interface LogisticsService extends LogisticsApi {

}
