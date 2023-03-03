package org.xianghao.xmall.wms.api;

import org.xianghao.xmall.api.api.purchase.PurchaseApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 采购服务
 * @author xianghao
 *
 */
@FeignClient("xmall-purchase")
public interface PurchaseService extends PurchaseApi {

}
