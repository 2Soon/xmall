package org.xianghao.xmall.customer.api;

import org.xianghao.xmall.api.api.order.OrderApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 订单服务
 * @author xianghao
 *
 */
@FeignClient("eshop-order")
public interface OrderService extends OrderApi {

}
