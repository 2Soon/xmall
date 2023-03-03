package org.xianghao.xmall.logistics.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.xianghao.xmall.api.api.wms.WmsApi;

/**
 * 仓储服务
 * @author xianghao
 *
 */
@FeignClient("eshop-wms")
public interface WmsService extends WmsApi {

}
