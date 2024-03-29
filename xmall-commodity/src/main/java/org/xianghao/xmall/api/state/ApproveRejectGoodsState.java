package org.xianghao.xmall.api.state;

import org.xianghao.xmall.api.constant.GoodsStatus;
import org.xianghao.xmall.api.dao.GoodsDAO;
import org.xianghao.xmall.api.domain.GoodsDO;
import org.xianghao.xmall.api.domain.commodity.GoodsDTO;
import org.xianghao.xmall.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 审核未通过状态
 * @author xianghao
 *
 */
@Component
public class ApproveRejectGoodsState implements GoodsState {
	
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	/**
	 * 商品管理DAO组件
	 */
	@Autowired
	private GoodsDAO goodsDAO;
	
	/**
	 * 执行商品流转到当前状态来的业务逻辑
	 * @param goods 商品
	 * @throws Exception
	 */
	@Override
	public void doTransition(GoodsDTO goods) throws Exception {
		goods.setStatus(GoodsStatus.APPROVE_REJECT);  
		goods.setGmtModified(dateProvider.getCurrentTime());  
		goodsDAO.updateStatus(goods.clone(GoodsDO.class));   
	}
	
	/**
	 * 判断当前商品能否执行编辑操作
	 * @param goods 商品
	 * @return 能否执行编辑操作
	 */
	@Override
	public Boolean canEdit(GoodsDTO goods) throws Exception {
		return true;
	}
	
	/**
	 * 判断能否执行审核操作
	 * @param goods 商品
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean canApprove(GoodsDTO goods) throws Exception {
		return false;
	}
	
	/**
	 * 判断能否执行上架操作
	 * @param goods 商品
	 * @return 能否执行上架操作
	 * @throws Exception
	 */
	@Override
	public Boolean canPutOnShelves(GoodsDTO goods) throws Exception {
		return false;
	}
	
	/**
	 * 判断能否执行下架操作
	 * @param goods 商品
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean canPullOffShelves(GoodsDTO goods) throws Exception {
		return false;
	}
	
	/**
	 * 能否执行删除操作
	 * @param goods 商品
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean canRemove(GoodsDTO goods) throws Exception {
		return true;
	}
	
}
