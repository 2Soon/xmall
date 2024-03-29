package org.xianghao.xmall.api.service.impl;

import org.xianghao.xmall.api.dao.GoodsPictureDAO;
import org.xianghao.xmall.api.domain.GoodsPictureDO;
import org.xianghao.xmall.api.domain.GoodsPictureDTO;
import org.xianghao.xmall.api.service.GoodsPictureService;
import org.xianghao.xmall.common.constant.PathType;
import org.xianghao.xmall.common.util.DateProvider;
import org.xianghao.xmall.common.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 商品图片管理service组件
 * @author xianghao
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsPictureServiceImpl implements GoodsPictureService {

	/**
	 * 商品图片管理DAO组件
	 */
	@Autowired
	private GoodsPictureDAO goodsPictureDAO;
	/**
	 * 图片上传路径的类型
	 */
	@Value("${commodity.goods.image.upload.picture.path.type}") 
	private String uploadDirPathType;
	/**
	 * 图片上传路径
	 */
	@Value("${commodity.goods.image.upload.picture.path.value}")  
	private String uploadDirPath;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 根据商品id查询商品图片id
	 * @param goodsId 商品id
	 * @return 商品图片id
	 */
	@Override
	public List<Long> listIdsByGoodsId(Long goodsId) throws Exception {
		return goodsPictureDAO.listIdsByGoodsId(goodsId);
	}
	
	/**
	 * 根据id查询商品图片
	 * @param id 商品图片id
	 * @return 商品图片
	 */
	@Override
	public GoodsPictureDTO getById(Long id) throws Exception {
		return goodsPictureDAO.getById(id).clone(GoodsPictureDTO.class);
	}
	
	/**
	 * 批量新增商品图片
	 * @param goodsId 商品id
	 * @param pictures 商品图片
	 * @throws Exception
	 */
	@Override
	public void batchSave(Long goodsId, 
			MultipartFile[] pictures) throws Exception {
		for(MultipartFile picture : pictures) {
			String picturePath = uploadPicture(picture);
			saveGoodsPicture(goodsId, picturePath); 
		}
	}
	
	/**
	 * 根据商品id删除图片
	 * @param goodsId 商品id
	 */
	@Override
	public void batchRemoveByGoodsId(Long goodsId) throws Exception {
		List<GoodsPictureDO> pictures = goodsPictureDAO.listByGoodsId(goodsId);
		for(GoodsPictureDO picture : pictures) {
			FileUtils.deleteFile(picture.getPicturePath());
		}
		goodsPictureDAO.removeByGoodsId(goodsId);  
	}
	
	/**
	 * 新增商品图片
	 * @param goodsId 商品id 
	 * @param picturePath 图片存储路径
	 * @throws Exception
	 */
	private void saveGoodsPicture(Long goodsId, 
			String picturePath) throws Exception {
		GoodsPictureDO picture = new GoodsPictureDO();
		picture.setGoodsId(goodsId); 
		picture.setPicturePath(picturePath); 
		picture.setGmtCreate(dateProvider.getCurrentTime()); 
		picture.setGmtModified(dateProvider.getCurrentTime()); 
		
		goodsPictureDAO.save(picture); 
	}
	
	/**
	 * 上传图片
	 * @param picture 图片
	 * @return 图片的存储路径
	 * @throws Exception
	 */
	private String uploadPicture(MultipartFile picture) throws Exception {
		String realUploadDirPath = getRealUploadDirPath();
		return FileUtils.uploadFile(picture, realUploadDirPath);
	}
	
	/**
	 * 获取最终的图片上传路径
	 * @return
	 * @throws Exception
	 */
	private String getRealUploadDirPath() throws Exception {
		String realUploadDirPath = uploadDirPath;
		if(PathType.RELATIVE.equals(uploadDirPathType)) {
			realUploadDirPath = FileUtils.getPathByRelative(uploadDirPath);
		}
		return realUploadDirPath;
	}
	
}
