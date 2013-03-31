package com.tavenli.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_app_menu")
public class MenuEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "menuName")
	private String menuName;
	@Column(name = "menuCode")
	private String menuCode;
	@Column(name = "parentId")
	private int parentId;
	@Column(name = "menuUrl")
	private String menuUrl;
	@Column(name = "urlTarget")
	private String urlTarget;
	@Column(name = "navMenu")
	private int navMenu;
	@Column(name = "sort")
	private int sort;
	@Column(name = "remark")
	private String remark;
	@Column(name = "createTime")
	private Date createTime;
	@Column(name = "lastUpdate")
	private Date lastUpdate;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,	CascadeType.MERGE}, mappedBy = "menus")
	private Set<RoleEntity> roles;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getUrlTarget() {
		return urlTarget;
	}
	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget;
	}	
	public int getNavMenu() {
		return navMenu;
	}
	public void setNavMenu(int navMenu) {
		this.navMenu = navMenu;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Set<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
	
	
}
