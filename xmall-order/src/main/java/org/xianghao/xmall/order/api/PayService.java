package org.xianghao.xmall.order.api;

import org.xianghao.xmall.api.api.pay.PayApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 支付服务
 * @author xianghao
 *
 */
@FeignClient("xmall-pay")
public interface PayService extends PayApi {

}
