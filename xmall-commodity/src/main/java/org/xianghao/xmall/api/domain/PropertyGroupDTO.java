package org.xianghao.xmall.api.domain;

import org.xianghao.xmall.common.util.AbstractObject;

import java.util.Date;
import java.util.List;

/**
 * 属性分组DO
 * @author xianghao
 *
 */
public class PropertyGroupDTO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 属性分组名称
	 */
	private String name;
	/**
	 * 类目id
	 */
	private Long categoryId;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 属性分组与属性的关联关系
	 */
	private List<PropertyGroupRelationshipDTO> relations;
	/**
	 * 属性分组关联的属性
	 */
	private List<PropertyDTO> properties;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public List<PropertyGroupRelationshipDTO> getRelations() {
		return relations;
	}
	public void setRelations(List<PropertyGroupRelationshipDTO> relations) {
		this.relations = relations;
	}
	public List<PropertyDTO> getProperties() {
		return properties;
	}
	public void setProperties(List<PropertyDTO> properties) {
		this.properties = properties;
	}
	
	@Override
	public String toString() {
		return "PropertyGroupDTO [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", gmtCreate="
				+ gmtCreate + ", gmtModified=" + gmtModified + ", relations=" + relations + ", properties=" + properties
				+ "]";
	}
	
}
