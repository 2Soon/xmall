package org.xianghao.xmall.wms.controller;

import org.xianghao.xmall.common.util.ObjectUtils;
import org.xianghao.xmall.wms.domain.GoodsAllocationDTO;
import org.xianghao.xmall.wms.domain.GoodsAllocationQuery;
import org.xianghao.xmall.wms.domain.GoodsAllocationVO;
import org.xianghao.xmall.wms.service.GoodsAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 货位管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/wms/goods/allocation")  
public class GoodsAllocationController {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsAllocationController.class);

	/**
	 * 货位管理service组件
	 */
	@Autowired
	private GoodsAllocationService goodsAllocationService;
	
	/**
	 * 分页查询货位
	 * @param query 查询条件
	 * @return 货位
	 */
	@GetMapping("/") 
	public List<GoodsAllocationVO> listByPage(GoodsAllocationQuery query) {
		try {
			return ObjectUtils.convertList(
					goodsAllocationService.listByPage(query), 
					GoodsAllocationVO.class); 
		} catch (Exception e) {
			logger.error("error", e);
			return new ArrayList<GoodsAllocationVO>();
		}
	}
	
	/**
	 * 新增货位
	 * @param goodsAllocation 货位
	 */
	@PostMapping("/") 
	public Boolean save(@RequestBody GoodsAllocationVO goodsAllocation) {
		try {
			goodsAllocationService.save(goodsAllocation.clone(GoodsAllocationDTO.class));   
			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}
	
	/**
	 * 根据id查询货位
	 * @param id 货位id
	 * @return 货位
	 */
	@GetMapping("/{id}")
	public GoodsAllocationVO getById(@PathVariable("id") Long id) {
		try {
			return goodsAllocationService.getById(id).clone(GoodsAllocationVO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 更新货位
	 * @param goodsAllocation 货位
	 */
	@PutMapping("/{id}")  
	public Boolean update(@RequestBody GoodsAllocationVO goodsAllocation) {
		try {
			goodsAllocationService.update(goodsAllocation.clone(GoodsAllocationDTO.class));  
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
