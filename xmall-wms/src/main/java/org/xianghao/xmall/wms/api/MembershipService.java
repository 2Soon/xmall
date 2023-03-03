package org.xianghao.xmall.wms.api;

import org.xianghao.xmall.api.api.membership.MembershipApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 会员服务
 * @author xianghao
 *
 */
@FeignClient("xmall-membership")
public interface MembershipService extends MembershipApi {

}
