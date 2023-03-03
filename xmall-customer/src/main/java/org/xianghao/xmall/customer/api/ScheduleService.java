package org.xianghao.xmall.customer.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.xianghao.xmall.api.api.schedule.ScheduleApi;

/**
 * 调度服务
 * @author xianghao
 *
 */
@FeignClient("xmall-schedule")
public interface ScheduleService extends ScheduleApi {

}
