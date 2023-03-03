package org.xianghao.xmall.schedule.api;

import org.xianghao.xmall.api.api.wms.WmsApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 仓储服务
 * @author xianghao
 *
 */
@FeignClient("xmall-wms")
public interface WmsService extends WmsApi {

}
