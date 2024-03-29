package org.xianghao.xmall.cart.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xianghao.xmall.api.domain.commodity.GoodsSkuDTO;
import org.xianghao.xmall.cart.api.CommodityService;
import org.xianghao.xmall.cart.api.InventoryService;
import org.xianghao.xmall.cart.api.PromotionService;
import org.xianghao.xmall.cart.dao.ShoppingCartDAO;
import org.xianghao.xmall.cart.dao.ShoppingCartItemDAO;
import org.xianghao.xmall.cart.domain.ShoppingCartDO;
import org.xianghao.xmall.cart.domain.ShoppingCartDTO;
import org.xianghao.xmall.cart.domain.ShoppingCartItemDO;
import org.xianghao.xmall.cart.domain.ShoppingCartItemDTO;
import org.xianghao.xmall.cart.service.ShoppingCartService;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.api.domain.promotion.PromotionActivityDTO;

/**
 * 购物车管理模块的service组件
 * @author xianghao
 *
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	/**
	 * 购物车管理模块的DAO组件
	 */
	@Autowired
	private ShoppingCartDAO shoppingCartDAO;
	/**
	 * 购物车条目管理模块的DAO组件
	 */
	@Autowired
	private ShoppingCartItemDAO shoppingCartItemDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	/**
	 * 商品服务
	 */
	@Autowired
	private CommodityService commodityService;
	/**
	 * 库存服务
	 */
	@Autowired
	private InventoryService inventoryService;
	/**
	 * 促销服务
	 */
	@Autowired
	private PromotionService promotionService;
	
	/**
	 * 添加购物车商品条目
	 * @param userAccountId 用户账号id
	 * @param goodsSkuId 商品sku id
	 * @return 处理结果
	 */
	@Override
	public void addShoppingCartItem(Long userAccountId, Long goodsSkuId) throws Exception {
		Date currentTime = dateProvider.getCurrentTime();
		
		// 先查询一下用户的购物车
		ShoppingCartDO shoppingCartDO = shoppingCartDAO
				.getShoppingCartByUserAccountId(userAccountId);
		
		// 如果购物车不存在，则新增一个购物车
		if(shoppingCartDO == null) {
			shoppingCartDO = new ShoppingCartDO();
			shoppingCartDO.setUserAccountId(userAccountId); 
			shoppingCartDO.setGmtCreate(currentTime);
			shoppingCartDO.setGmtModified(currentTime);  
			
			shoppingCartDAO.saveShoppingCart(shoppingCartDO);
		}
		
		// 查询一下购物车中是否存在这个商品sku对应的条目
		ShoppingCartItemDO shoppingCartItemDO = shoppingCartItemDAO
				.getShoppingCartItemByGoodsSkuId(shoppingCartDO.getId(), goodsSkuId);
		
		// 如果没有这个商品条目，则新增一个商品条目
		if(shoppingCartItemDO == null) {
			shoppingCartItemDO = new ShoppingCartItemDO(); 
			shoppingCartItemDO.setShoppingCartId(shoppingCartDO.getId()); 
			shoppingCartItemDO.setGoodsSkuId(goodsSkuId); 
			shoppingCartItemDO.setPurchaseQuantity(1L); 
			shoppingCartItemDO.setGmtCreate(currentTime);  
			shoppingCartItemDO.setGmtModified(currentTime);  
			
			shoppingCartItemDAO.saveShoppingCartItem(shoppingCartItemDO);
		} 
		// 如果购物车中已经存在这个商品条目了，则对已有的商品条目的购买数量累加1
		else {
			shoppingCartItemDO.setPurchaseQuantity(
					shoppingCartItemDO.getPurchaseQuantity() + 1L); 
			shoppingCartItemDO.setGmtModified(currentTime);  
			shoppingCartItemDAO.updateShoppingCartItem(shoppingCartItemDO);
		}
	}
	
	/**
	 * 查看用户的购物车中的数据
	 * @param userAccountId 用户账号id
	 * @return 购物车DTO对象
	 */
	@Override
	public ShoppingCartDTO getShoppingCartDTOByUserAccountId(Long userAccountId) throws Exception {
		// 根据用户账号id查询一下购物车
		ShoppingCartDO shoppingCartDO = shoppingCartDAO
				.getShoppingCartByUserAccountId(userAccountId);
		if(shoppingCartDO == null) {
			return new ShoppingCartDTO();
		}
		ShoppingCartDTO shoppingCartDTO = shoppingCartDO.clone(ShoppingCartDTO.class);
		
		// 查询购物车条目
		List<ShoppingCartItemDO> shoppingCartItemDOs = shoppingCartItemDAO
				.listShoppingCartItemByCartId(shoppingCartDTO.getId());
		if(shoppingCartItemDOs == null || shoppingCartItemDOs.size() == 0) {
			return shoppingCartDTO;
		}
		
		List<ShoppingCartItemDTO> shoppingCartItemDTOs = new ArrayList<ShoppingCartItemDTO>();
		shoppingCartDTO.setShoppingCartItemDTOs(shoppingCartItemDTOs); 
		
		// 为购物车条目填充相关的数据
		for(ShoppingCartItemDO shoppingCartItemDO : shoppingCartItemDOs) {
			ShoppingCartItemDTO item = shoppingCartItemDO.clone(ShoppingCartItemDTO.class);
			setGoodsRelatedData(item);
			setStockRelatedData(item);
			setPromotionRelatedData(item); 
			shoppingCartItemDTOs.add(item); 
		}
		
		return shoppingCartDTO;
	}
	
	/**
	 * 给购物车条目设置商品相关的数据
	 * @throws Exception
	 */
	private void setGoodsRelatedData(ShoppingCartItemDTO item) throws Exception {
		GoodsSkuDTO goodsSkuDTO = commodityService.getGoodsSkuById(
				item.getGoodsSkuId());
		
		item.setGoodsId(goodsSkuDTO.getGoodsId());  
		item.setGoodsHeight(goodsSkuDTO.getGoodsHeight()); 
		item.setGoodsLength(goodsSkuDTO.getGoodsLength()); 
		item.setGoodsName(goodsSkuDTO.getGoodsName()); 
		item.setGoodsSkuCode(goodsSkuDTO.getGoodsSkuCode()); 
		item.setGoodsWidth(goodsSkuDTO.getGoodsWidth()); 
		item.setGrossWeight(goodsSkuDTO.getGrossWeight()); 
		item.setSalePrice(goodsSkuDTO.getSalePrice()); 
		item.setSaleProperties(goodsSkuDTO.getSaleProperties());
	}
	
	/**
	 * 给购物车条目设置库存相关的数据 
	 * @param item 购物车条目
	 * @throws Exception
	 */
	private void setStockRelatedData(ShoppingCartItemDTO item) throws Exception {
		Long saleStockQuantity = inventoryService.getSaleStockQuantity(
				item.getGoodsSkuId());
		item.setSaleStockQuantity(saleStockQuantity);
	}
	
	/**
	 * 给购物车条目设置促销相关的数据
	 * @param item 购物车条目
	 * @throws Exception
	 */
	private void setPromotionRelatedData(ShoppingCartItemDTO item) throws Exception {
		List<PromotionActivityDTO> promotionActivityDTOs = promotionService
				.listByGoodsId(item.getGoodsId());
		item.setPromotionActivityDTOs(promotionActivityDTOs); 
	}
	
}
