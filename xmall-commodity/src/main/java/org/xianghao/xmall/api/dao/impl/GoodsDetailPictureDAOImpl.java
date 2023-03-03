package org.xianghao.xmall.api.dao.impl;

import org.xianghao.xmall.api.dao.GoodsDetailPictureDAO;
import org.xianghao.xmall.api.domain.GoodsDetailPictureDO;
import org.xianghao.xmall.api.mapper.GoodsDetailPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品详情图片管理DAO组件
 * @author xianghao
 *
 */
@Repository
public class GoodsDetailPictureDAOImpl implements GoodsDetailPictureDAO {

	/**
	 * 商品详情图片管理mapper组件
	 */
	@Autowired
	private GoodsDetailPictureMapper goodsDetailPictureMapper;
	
	/**
	 * 根据id查询商品图片
	 * @param id 商品图片id
	 * @return 商品图片
	 */
	@Override
	public GoodsDetailPictureDO getById(Long id) {
		return goodsDetailPictureMapper.getById(id);
	}
	
	/**
	 * 根据商品详情id查询商品详情图片
	 * @param id 商品图片id
	 * @return 商品图片
	 */
	@Override
	public List<GoodsDetailPictureDO> listByGoodsDetailId(Long goodsDetailId) {
		return goodsDetailPictureMapper.listByGoodsDetailId(goodsDetailId);
	}
	
	/**
	 * 新增商品详情图片
	 * @param picture 图片
	 */
	@Override
	public Long save(GoodsDetailPictureDO picture) {
		goodsDetailPictureMapper.save(picture); 
		return picture.getId();
	}
	
	/**
	 * 根据商品id删除图片
	 * @param goodsId 商品id
	 */
	@Override
	public void removeByGoodsDetailId(Long goodsDetailId) {
		goodsDetailPictureMapper.removeByGoodsDetailId(goodsDetailId);
	}
	
}
