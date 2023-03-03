package org.xianghao.xmall.api.api.fiance;

import org.xianghao.xmall.api.domain.wms.PurchaseInputOrderDTO;
import org.xianghao.xmall.api.domain.wms.SaleDeliveryOrderDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 财务中心对外提供的接口
 * @author xianghao
 *
 */
public interface FinanceApi {

	/**
	 * 创建采购结算单
	 * @param purchaseInputOrderDTO 采购入库单DTO
	 * @return 处理结果
	 */
	@RequestMapping(value = "/createPurchaseSettlementOrder",method = RequestMethod.POST)
	Boolean createPurchaseSettlementOrder(@Param("purchaseInputOrder") PurchaseInputOrderDTO purchaseInputOrder);
	
	/**
	 * 给物流公司打款
	 * @param saleDeliveryOrderDTO 销售出库单
	 * @return 处理结果
	 */
	@RequestMapping(value = "payForLogisticsCompany",method = RequestMethod.PUT)
	Boolean payForLogisticsCompany(@Param("saleDeliveryOrder") SaleDeliveryOrderDTO saleDeliveryOrder);
	
}
