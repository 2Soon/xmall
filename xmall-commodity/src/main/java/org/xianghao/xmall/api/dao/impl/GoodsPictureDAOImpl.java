package org.xianghao.xmall.api.dao.impl;

import org.xianghao.xmall.api.dao.GoodsPictureDAO;
import org.xianghao.xmall.api.domain.GoodsPictureDO;
import org.xianghao.xmall.api.mapper.GoodsPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品图片管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class GoodsPictureDAOImpl implements GoodsPictureDAO {
	
	/**
	 * 商品图片管理mapper组件
	 */
	@Autowired
	private GoodsPictureMapper goodsPictureMapper;
	
	/**
	 * 根据商品id查询商品图片id
	 * @param goodsId 商品id
	 * @return 商品图片id
	 */
	@Override
	public List<Long> listIdsByGoodsId(Long goodsId) {
		return goodsPictureMapper.listIdsByGoodsId(goodsId);
	}
	
	/**
	 * 根据id查询商品图片
	 * @param id 商品图片id
	 * @return 商品图片
	 */
	@Override
	public List<GoodsPictureDO> listByGoodsId(Long goodsId) {
		return goodsPictureMapper.listByGoodsId(goodsId);
	}
	
	/**
	 * 根据id查询商品图片
	 * @param id 商品图片id
	 * @return 商品图片
	 */
	@Override
	public GoodsPictureDO getById(Long id) {
		return goodsPictureMapper.getById(id);
	}
	
	/**
	 * 新增商品图片
	 * @param picture 图片
	 */
	@Override
	public void save(GoodsPictureDO picture) {
		goodsPictureMapper.save(picture); 
	}
	
	/**
	 * 根据商品id删除图片
	 * @param goodsId 商品id
	 */
	@Override
	public void removeByGoodsId(Long goodsId) {
		goodsPictureMapper.removeByGoodsId(goodsId);
	}

}
