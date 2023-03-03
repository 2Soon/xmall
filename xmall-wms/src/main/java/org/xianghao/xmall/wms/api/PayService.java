package org.xianghao.xmall.wms.api;

import org.xianghao.xmall.api.api.pay.PayApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 支付服务
 * @author xianghao
 *
 */
@FeignClient("eshop-pay")
public interface PayService extends PayApi {

}
