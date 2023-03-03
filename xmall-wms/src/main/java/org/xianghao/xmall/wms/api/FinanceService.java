package org.xianghao.xmall.wms.api;

import org.xianghao.xmall.api.api.fiance.FinanceApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 财务服务
 * @author xianghao
 *
 */
@FeignClient("xmall-finance")
public interface FinanceService extends FinanceApi {

}
