package org.xianghao.xmall.schedule.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.xianghao.xmall.api.api.inventory.InventoryApi;

/**
 * 库存服务
 * @author xianghao
 *
 */
@FeignClient("xmall-inventory")
public interface InventoryService extends InventoryApi {

}
