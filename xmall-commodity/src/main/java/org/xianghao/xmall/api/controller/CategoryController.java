package org.xianghao.xmall.api.controller;

import org.xianghao.xmall.api.domain.*;
import org.xianghao.xmall.api.service.CategoryService;
import org.xianghao.xmall.common.util.CloneDirection;
import org.xianghao.xmall.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 类目管理controller组件
 * @author xianghao
 *
 */
@RestController
@RequestMapping("/commodity/category") 
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	/**
	 * 类目管理service组件
	 */
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 查询根类目
	 * @return 根类目集合
	 */
	@GetMapping("/root") 
	public List<CategoryVO> listRoots() {
		try {
			List<CategoryDTO> categories = categoryService.listRoots(); 
			List<CategoryVO> resultCategories = ObjectUtils.convertList(
					categories, CategoryVO.class);
			return resultCategories;
		} catch (Exception e) {
			logger.error("error", e); 
			return new ArrayList<CategoryVO>();
		}
	}
	
	/**
	 * 查询子类目
	 * @return 子类目集合
	 */
	@GetMapping("/children/{id}")  
	public List<CategoryVO> listChildren(@PathVariable("id") Long id) {  
		try {
			List<CategoryDTO> categories = categoryService.listChildren(id);
			List<CategoryVO> resultCategories = ObjectUtils.convertList(
					categories, CategoryVO.class);
			return resultCategories;
		} catch (Exception e) {
			logger.error("error", e); 
			return new ArrayList<CategoryVO>();
		}
	}
	
	/**
	 * 新增类目
	 * @param categoryVO 类目
	 * @return 处理结果
	 */
	@PostMapping("/")
	public Boolean save(@RequestBody CategoryVO category) { 
		try {
			// 转换类目基本信息
			CategoryDTO targetCategory = category.clone(CategoryDTO.class);
			
			// 转换类目与属性的关联关系
			List<CategoryPropertyRelationshipDTO> targetPropertyRelations = ObjectUtils.convertList(
					category.getPropertyRelations(), CategoryPropertyRelationshipDTO.class);
			targetCategory.setPropertyRelations(targetPropertyRelations);  
			
			// 转换属性分组
			if(category.getPropertyGroups() != null) {
				List<PropertyGroupDTO> targetPropertyGroups = new ArrayList<PropertyGroupDTO>();
				targetCategory.setPropertyGroups(targetPropertyGroups); 
				
				for(PropertyGroupVO group : category.getPropertyGroups()) {
					PropertyGroupDTO targetGroup = group.clone(PropertyGroupDTO.class);
					targetGroup.setRelations(ObjectUtils.convertList(
							group.getRelations(), PropertyGroupRelationshipDTO.class)); 
					targetPropertyGroups.add(targetGroup);
				}
			}
			
			// 执行类目新增的操作
			categoryService.save(targetCategory);
			
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 根据id查询类目
	 * @param id 类目id
	 * @return 类目
	 */
	@GetMapping("/{id}")  
	public CategoryVO getById(@PathVariable("id") Long id) {
		try {
			CategoryDTO category = categoryService.getById(id);
			CategoryVO resultCategory = category.clone(
					CategoryVO.class, CloneDirection.OPPOSITE);
			return resultCategory;
		} catch (Exception e) { 
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 更新类目
	 * @param category 类目
	 * @return 处理结果
	 */
	@PutMapping("/{id}") 
	public Boolean update(@RequestBody CategoryVO category) {
		try {
			CategoryDTO targetCategory = category.clone(
					CategoryDTO.class, CloneDirection.FORWARD);
			categoryService.update(targetCategory); 
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 删除类目
	 * @param id 类目id
	 * @return 处理结果
	 */
	@DeleteMapping("/{id}")  
	public Boolean remove(@PathVariable("id") Long id) {
		try {
			return categoryService.remove(id);
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
