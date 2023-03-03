package org.xianghao.xmall.wms.api;

import org.xianghao.xmall.api.api.schedule.ScheduleApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 调度服务
 * @author xianghao
 *
 */
@FeignClient("xmall-schedule")
public interface ScheduleService extends ScheduleApi {

}
