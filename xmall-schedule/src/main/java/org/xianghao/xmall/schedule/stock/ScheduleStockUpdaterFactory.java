package org.xianghao.xmall.schedule.stock;

import org.xianghao.xmall.common.bean.SpringApplicationContext;
import org.xianghao.xmall.schedule.constant.StockUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 库存更新命令工厂接口
 * @author xianghao
 *
 */
@Component
public class ScheduleStockUpdaterFactory { 

	/**
	 * spring容器
	 */
	@Autowired
	private SpringApplicationContext context;
	
	/**
	 * 创建一个库存更新命令
	 * @param parameter 参数对象
	 * @return 库存更新命令
	 */
	public ScheduleStockUpdater create(Integer stockUpdateEvent, Object parameter) { 
		ScheduleStockUpdater stockUpdater = null;
		
		if(StockUpdateEvent.SUBMIT_ORDER.equals(stockUpdateEvent)) {
			stockUpdater = context.getBean(SubmitOrderScheduleStockUpdater.class);
		} else if(StockUpdateEvent.CANCEL_ORDER.equals(stockUpdateEvent)) {
			stockUpdater = context.getBean(CancelOrderScheduleStockUpdater.class);
		} else if(StockUpdateEvent.PAY_ORDER.equals(stockUpdateEvent)) {
			stockUpdater = context.getBean(PayOrderScheduleStockUpdater.class);
		} else if(StockUpdateEvent.PURCHASE_INPUT.equals(stockUpdateEvent)) {
			stockUpdater = context.getBean(PurchaseInputScheduleStockUpdater.class);
		} else if(StockUpdateEvent.RETURN_GOODS_INPUT.equals(stockUpdateEvent)) {
			stockUpdater = context.getBean(ReturnGoodsInputScheduleStockUpdater.class);
		}
		
		stockUpdater.setParameter(parameter); 
		
		return stockUpdater;
	}
	
}
