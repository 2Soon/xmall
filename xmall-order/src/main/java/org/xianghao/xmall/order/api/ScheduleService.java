package org.xianghao.xmall.order.api;

import org.xianghao.xmall.api.api.schedule.ScheduleApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 调度服务
 * @author xianghao
 *
 */
@FeignClient("eshop-schedule")
public interface ScheduleService extends ScheduleApi {

}
