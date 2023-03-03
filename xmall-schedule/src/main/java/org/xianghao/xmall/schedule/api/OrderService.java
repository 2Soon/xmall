package org.xianghao.xmall.schedule.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.xianghao.xmall.api.api.order.OrderApi;

/**
 * 订单服务
 * @author xianghao
 *
 */
@FeignClient("eshop-order")
public interface OrderService extends OrderApi {

}
