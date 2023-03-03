package org.xianghao.xmall.api.domain.schedule;

import org.xianghao.xmall.api.domain.customer.ReturnGoodsWorksheetDTO;
import org.xianghao.xmall.api.domain.order.OrderInfoDTO;

/**
 * @description: 调度服务的退货工单
 * @packagename: org.xianghao.xmall.schedule.domain
 * @author: xianghao
 * @date: 2023-02-14 17:37
 **/
public class ScheduleReturnGoodsWorksheetDTO {
    private OrderInfoDTO order;
    private ReturnGoodsWorksheetDTO returnGoodsWorksheetDTO;

    public OrderInfoDTO getOrder() {
        return order;
    }

    public void setOrder(OrderInfoDTO order) {
        this.order = order;
    }

    public ReturnGoodsWorksheetDTO getReturnGoodsWorksheetDTO() {
        return returnGoodsWorksheetDTO;
    }

    public void setReturnGoodsWorksheetDTO(ReturnGoodsWorksheetDTO returnGoodsWorksheetDTO) {
        this.returnGoodsWorksheetDTO = returnGoodsWorksheetDTO;
    }
}
