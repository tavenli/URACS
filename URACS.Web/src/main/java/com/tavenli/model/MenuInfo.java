package com.tavenli.model;

import java.util.ArrayList;
import java.util.List;

public class MenuInfo {
	
	private int menuId;
	private String menuName;
	private String menuCode;
	private String menuUrl;
	List<MenuInfo> childMenus = new ArrayList<MenuInfo>();
	
	public MenuInfo(){
		
	}
	
	public MenuInfo(String menuName,String menuUrl){
		this.menuName = menuName;
		this.menuUrl = menuUrl;
	}
	
	public MenuInfo(int menuId,String menuName,String menuCode,String menuUrl){
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuCode = menuCode;
		this.menuUrl = menuUrl;
	}
	
	public void addChild(MenuInfo child){
		this.childMenus.add(child);
	}
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
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

	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public List<MenuInfo> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<MenuInfo> childMenus) {
		this.childMenus = childMenus;
	}
	
	

}
