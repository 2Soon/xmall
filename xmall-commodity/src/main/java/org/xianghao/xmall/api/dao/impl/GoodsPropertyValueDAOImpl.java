package org.xianghao.xmall.api.dao.impl;

import org.xianghao.xmall.api.dao.GoodsPropertyValueDAO;
import org.xianghao.xmall.api.domain.GoodsPropertyValueDO;
import org.xianghao.xmall.api.mapper.GoodsPropertyValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品属性值管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class GoodsPropertyValueDAOImpl implements GoodsPropertyValueDAO {

	/**
	 * 商品属性值管理mapper组件
	 */
	@Autowired
	private GoodsPropertyValueMapper goodsPropertyValueMapper;
	
	/**
	 * 根据商品id查询属性值
	 * @param goodsId 商品id
	 * @return 属性值
	 */
	@Override
	public List<GoodsPropertyValueDO> listByGoodsId(Long goodsId) {
		return goodsPropertyValueMapper.listByGoodsId(goodsId);
	}
	
	/**
	 * 新增商品属性值
	 * @param goodsPropertyValue 商品属性值
	 */
	@Override
	public void save(GoodsPropertyValueDO goodsPropertyValue) {
		goodsPropertyValueMapper.save(goodsPropertyValue);
	}
	
	/**
	 * 根据商品id删除属性值
	 * @param goodsId 商品id
	 */
	@Override
	public void removeByGoodsId(Long goodsId) {
		goodsPropertyValueMapper.removeByGoodsId(goodsId);
	}
	
}
