package org.xianghao.xmall.cart.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xianghao.xmall.cart.dao.ShoppingCartDAO;
import org.xianghao.xmall.cart.domain.ShoppingCartDO;
import org.xianghao.xmall.cart.mapper.ShoppingCartMapper;

/**
 * 购物车管理模块的DAO组件
 * @author xianghao
 *
 */
@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO {
	
	/**
	 * 购物车管理模块的mapper组件
	 */
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	/**
	 * 根据用户账号id查询购物车
	 * @param userAccountId 用户账号id
	 * @return 购物车
	 */
	@Override
	public ShoppingCartDO getShoppingCartByUserAccountId(Long userAccountId) throws Exception {
		return shoppingCartMapper.getShoppingCartByUserAccountId(userAccountId);
	}
	
	/**
	 * 新增购物车
	 * @param shoppingCartDO 购物车DO对象
	 */
	@Override
	public Long saveShoppingCart(ShoppingCartDO shoppingCartDO) throws Exception {
		shoppingCartMapper.saveShoppingCart(shoppingCartDO); 
		return shoppingCartDO.getId();
	}

}
