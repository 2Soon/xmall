package org.xianghao.xmall.api.service;

import org.xianghao.xmall.api.domain.GoodsDetailPictureDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 商品详情图片管理service接口
 * @author xianghao
 *
 */
public interface GoodsDetailPictureService {
	
	/**
	 * 根据id查询商品图片
	 * @param id 商品图片id
	 * @return 商品图片
	 * @throws Exception
	 */
	GoodsDetailPictureDTO getById(Long id) throws Exception;

	/**
	 * 批量上传图片
	 * @param goodsDetailId 商品详情id 
	 * @param pictures 商品详情图片  
	 * @return 商品详情图片id
	 * @throws Exception
	 */
	List<Long> batchUploadPicture(Long goodsDetailId, 
			MultipartFile[] pictures) throws Exception;
	
	/**
	 * 根据商品id删除图片
	 * @param goodsDetailId 商品id
	 * @throws Exception
	 */
	void batchRemoveByGoodsDetailId(Long goodsDetailId) throws Exception;
	
}
