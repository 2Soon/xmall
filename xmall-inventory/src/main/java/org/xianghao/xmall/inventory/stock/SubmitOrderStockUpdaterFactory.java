package org.xianghao.xmall.inventory.stock;

import org.xianghao.xmall.api.domain.order.OrderInfoDTO;
import org.xianghao.xmall.api.domain.order.OrderItemDTO;
import org.xianghao.xmall.common.constant.CollectionSize;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.inventory.dao.GoodsStockDAO;
import org.xianghao.xmall.inventory.domain.GoodsStockDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提交订单库存更新组件工厂
 * @author xianghao
 *
 */
@Component
public class SubmitOrderStockUpdaterFactory<T>  
		extends AbstractStockUpdaterFactory<T> {

	/**
	 * 构造函数
	 * @param goodsStockDAO 商品库存管理模块DAO组件
	 * @param dateProvider 日期辅助组件
	 */
	@Autowired
	public SubmitOrderStockUpdaterFactory(
			GoodsStockDAO goodsStockDAO, 
			DateProvider dateProvider) {  
		super(goodsStockDAO, dateProvider);
	}
	
	/**
	 * 获取要更新库存的商品sku id的集合
	 */
	@Override
	protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
		OrderInfoDTO orderInfoDTO = (OrderInfoDTO) parameter;
		
		List<Long> goodsSkuIds = new ArrayList<Long>();
		
		List<OrderItemDTO> orderItemDTOs = orderInfoDTO.getOrderItems();
		for(OrderItemDTO orderItemDTO : orderItemDTOs) {
			goodsSkuIds.add(orderItemDTO.getGoodsSkuId());
		}
		
		return goodsSkuIds;
	}

	/**
	 * 创建商品库存更新组件
	 * @param goodsStockDOs 商品库存DO对象集合
	 * @param parameter 订单DTO对象
	 * @return 商品库存更新组件
	 */
	@Override
	protected StockUpdater create(List<GoodsStockDO> goodsStockDOs, 
			T parameter) throws Exception {
		OrderInfoDTO orderInfoDTO = (OrderInfoDTO) parameter;
		Map<Long, OrderItemDTO> orderItemDTOMap = new HashMap<Long, OrderItemDTO>(CollectionSize.DEFAULT);
		for(OrderItemDTO orderItemDTO : orderInfoDTO.getOrderItems()) {
			orderItemDTOMap.put(orderItemDTO.getGoodsSkuId(), orderItemDTO);
		}
		
		return new SubmitOrderStockUpdater(goodsStockDOs, goodsStockDAO, 
				dateProvider, orderItemDTOMap);
	} 

}
