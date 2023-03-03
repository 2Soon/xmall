package org.xianghao.xmall.order.api;

import org.xianghao.xmall.api.api.inventory.InventoryApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 库存服务
 * @author xianghao
 *
 */
@FeignClient("xmall-inventory")
public interface InventoryService extends InventoryApi {

}
