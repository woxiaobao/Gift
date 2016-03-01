package com.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 模块对象
 * @author lvbaolin 2015-06-18
 *
 */
@Entity
@Table(name="T_MODULE")
public class Module {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(length=82)
	private Long id;//id
	
	
	@Column(length=22)
	private String name;//模块名称
	
	@Column(length=22)
	private String permissions;//权限名
	
	@Column(length=82)
	private Long parentId;//父模块id
	
	@Column(length=5)
	private int sortTop=0;

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

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public int getSortTop() {
		return sortTop;
	}

	public void setSortTop(int sortTop) {
		this.sortTop = sortTop;
	}
	
	
}
