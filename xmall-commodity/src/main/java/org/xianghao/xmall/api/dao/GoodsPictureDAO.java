package org.xianghao.xmall.api.dao;

import org.xianghao.xmall.api.domain.GoodsPictureDO;

import java.util.List;

/**
 * 商品图片管理mapper组件
 * @author xianghao
 *
 */
public interface GoodsPictureDAO {

	/**
	 * 新增商品图片
	 * @param picture 图片
	 */
	void save(GoodsPictureDO picture);
	
	/**
	 * 根据商品id查询商品图片id
	 * @param goodsId 商品id
	 * @return 商品图片id
	 */
	List<Long> listIdsByGoodsId(Long goodsId);
	
	/**
	 * 根据id查询商品图片
	 * @param goodsId 商品图片id
	 * @return 商品图片
	 */
	List<GoodsPictureDO> listByGoodsId(Long goodsId);
	
	/**
	 * 根据id查询商品图片
	 * @param id 商品图片id
	 * @return 商品图片
	 */
	GoodsPictureDO getById(Long id);
	
	/**
	 * 根据商品id删除图片
	 * @param goodsId 商品id
	 */
	void removeByGoodsId(Long goodsId);
	
}
