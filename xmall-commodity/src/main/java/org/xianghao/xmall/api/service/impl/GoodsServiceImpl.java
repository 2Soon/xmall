package org.xianghao.xmall.api.service.impl;

import org.xianghao.xmall.api.constant.GoodsStatus;
import org.xianghao.xmall.api.dao.*;
import org.xianghao.xmall.api.domain.*;
import org.xianghao.xmall.api.domain.commodity.GoodsDTO;
import org.xianghao.xmall.api.service.GoodsService;
import org.xianghao.xmall.api.state.GoodsStateManager;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

	/**
	 * 商品管理DAO组件
	 */
	@Autowired
	private GoodsDAO goodsDAO;
	/**
	 * 商品状态管理器
	 */
	@Autowired
	private GoodsStateManager goodsStateManager;
	/**
	 * 商品图片管理DAO
	 */
	@Autowired
	private GoodsPictureDAO goodsPictureDAO;
	/**
	 * 商品详情管理DAO
	 */
	@Autowired
	private GoodsDetailDAO goodsDetailDAO;
	/**
	 * 商品详情图片管理DAO
	 */
	@Autowired
	private GoodsDetailPictureDAO goodsDetailPictureDAO;
	/**
	 * 商品属性值管理DAO
	 */
	@Autowired
	private GoodsPropertyValueDAO goodsPropertyValueDAO;
	/**
	 * 商品sku管理DAO
	 */
	@Autowired
	private GoodsSkuDAO goodsSkuDAO;
	/**
	 * 商品sku属性值管理DAO
	 */
	@Autowired
	private GoodsSkuSalePropertyValueDAO goodsSkuSalePropertyValueDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 分页查询商品
	 * @param query 查询条件
	 * @return 商品
	 */
	@Override
	public List<GoodsDTO> listByPage(GoodsQuery query) throws Exception {
		return ObjectUtils.convertList(goodsDAO.listByPage(query), GoodsDTO.class); 
	}
	
	/**
	 * 根据id查询商品
	 * @param id 商品id
	 * @return 商品
	 */
	@Override
	public GoodsDTO getById(Long id) throws Exception {
		return goodsDAO.getById(id).clone(GoodsDTO.class);
	}
	
	/**
	 * 新增商品
	 * @param goods 商品
	 */
	@Override
	public Long save(GoodsDTO goods) throws Exception {
		goods.setStatus(GoodsStatus.UNKNOWN); 
		goods.setGmtCreate(dateProvider.getCurrentTime()); 
		goods.setGmtModified(dateProvider.getCurrentTime()); 
		Long goodsId = goodsDAO.save(goods.clone(GoodsDO.class)); 
		goods.setId(goodsId); 
		goodsStateManager.create(goods); 
		return goodsId;
	}
	
	/**
	 * 更新商品
	 * @param goods 商品
	 */
	@Override
	public Boolean update(GoodsDTO goods) throws Exception {
		if(!goodsStateManager.canEdit(goods)) {
			return false;
		}
		
		goodsDAO.update(goods.clone(GoodsDO.class));  
		goodsStateManager.edit(goods); 
		
		return true;
	}
	
	/**
	 * 审核商品
	 * @param goods 商品
	 * @return 处理结果
	 * @throws Exception
	 */
	@Override
	public Boolean approve(Long goodsId, Integer approveResult) throws Exception {
		GoodsDTO goods = goodsDAO.getById(goodsId).clone(GoodsDTO.class);
		if(!goodsStateManager.canApprove(goods)) {
			return false;
		}
		goodsStateManager.approve(goods, approveResult);
		return true;
	}
	
	/**
	 * 执行上架操作
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean putOnShelves(Long goodsId) throws Exception {
		GoodsDTO goods = goodsDAO.getById(goodsId).clone(GoodsDTO.class);
		if(!goodsStateManager.canPutOnShelves(goods)) {
			return false;
		}
		goodsStateManager.putOnShelves(goods);
		return true;
	}
	
	/**
	 * 执行下架操作
	 * @param goods 商品
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean pullOffShelves(Long goodsId) throws Exception {
		GoodsDTO goods = goodsDAO.getById(goodsId).clone(GoodsDTO.class);
		if(!goodsStateManager.canPullOffShelves(goods))   {
			return false;
		}
		goodsStateManager.pullOffShelves(goods); 
		return true;
	}
	
	/**
	 * 执行删除操作
	 * @param goodsId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean remove(Long id) throws Exception {
		GoodsDTO goods = goodsDAO.getById(id).clone(GoodsDTO.class);
		
		if(!goodsStateManager.canRemove(goods)) {
			return false;
		}
		
		goodsPictureDAO.removeByGoodsId(id); 
		
		GoodsDetailDO goodsDetail = goodsDetailDAO.getByGoodsId(id);
		goodsDetailPictureDAO.removeByGoodsDetailId(goodsDetail.getId());  
		goodsDetailDAO.remove(goodsDetail.getId()); 
		
		goodsPropertyValueDAO.removeByGoodsId(id); 
		
		List<GoodsSkuDO> goodsSkus = goodsSkuDAO.listByGoodsId(id);
		for(GoodsSkuDO goodsSku : goodsSkus) {
			goodsSkuSalePropertyValueDAO.removeByGoodsSkuId(goodsSku.getId()); 
		}
		goodsSkuDAO.removeByGoodsId(id); 
		
		goodsDAO.remove(id); 
		
		return true;
	}
	
}
