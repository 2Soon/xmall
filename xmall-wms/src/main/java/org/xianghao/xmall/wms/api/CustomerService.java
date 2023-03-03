package org.xianghao.xmall.wms.api;

import org.xianghao.xmall.api.api.customer.CustomerApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 客服服务
 * @author xianghao
 *
 */
@FeignClient("xmall-customer")
public interface CustomerService extends CustomerApi {

}
