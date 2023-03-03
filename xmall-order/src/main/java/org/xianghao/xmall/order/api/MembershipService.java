package org.xianghao.xmall.order.api;

import org.xianghao.xmall.api.api.membership.MembershipApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 会员服务
 * @author xianghao
 *
 */
@FeignClient("eshop-membership")
public interface MembershipService extends MembershipApi {

}
