package org.xianghao.xmall.promotion.controller;

import org.xianghao.xmall.api.domain.promotion.PromotionActivityDTO;
import org.xianghao.xmall.common.util.CloneDirection;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.promotion.domain.PromotionActivityQuery;
import org.xianghao.xmall.promotion.domain.PromotionActivityVO;
import org.xianghao.xmall.promotion.service.PromotionActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 促销活动管理contorller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/promotion/activity") 
public class PromotionActivityController {
	
	private static final Logger logger = LoggerFactory.getLogger(
			PromotionActivityController.class);

	/**
	 * 促销活动管理service组件
	 */
	@Autowired
	private PromotionActivityService promotionActivityService;
	
	/**
	 * 分页查询促销活动
	 * @param query 查询条件
	 * @return 促销活动
	 */
	@GetMapping("/")  
	public List<PromotionActivityVO> listByPage(
			PromotionActivityQuery query) throws Exception {
		try {
			return ObjectUtils.convertList(promotionActivityService.listByPage(query), 
					PromotionActivityVO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return new ArrayList<PromotionActivityVO>();
		}
	}
	
	/**
	 * 根据id查询促销活动
	 * @param id 促销活动id
	 * @return 促销活动
	 */
	@GetMapping("/{id}") 
	public PromotionActivityVO getById(@PathVariable("id") Long id) throws Exception {
		try {
			return promotionActivityService.getById(id).clone(
					PromotionActivityVO.class, CloneDirection.OPPOSITE);
		} catch (Exception e) {
			logger.error("error", e); 
			return new PromotionActivityVO();
		} 
	}
	
	/**
	 * 新增促销活动
	 * @param activity 促销活动
	 */
	@PostMapping("/") 
	public Boolean save(@RequestBody PromotionActivityVO activity) throws Exception {
		try {
			promotionActivityService.save(activity.clone(PromotionActivityDTO.class,
					CloneDirection.FORWARD));  
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 更新促销活动
	 * @param activity 促销活动
	 */
	@PutMapping("/{id}")  
	public Boolean update(@RequestBody PromotionActivityVO activity) throws Exception {
		try {
			promotionActivityService.update(activity.clone(PromotionActivityDTO.class, 
					CloneDirection.FORWARD));
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 删除促销活动
	 * @param id 促销活动id
	 */
	@DeleteMapping("/{id}")
	public Boolean remove(@PathVariable("id") Long id) throws Exception {
		try {
			promotionActivityService.remove(id); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
