package com.tavenli.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavenli.entity.MenuEntity;
import com.tavenli.model.MenuInfo;

@Service
public class UResourceService {

	private static Logger logger = LoggerFactory.getLogger(UResourceService.class);
	
	@Autowired
	private UCenterService uCenterService;
	
	
	public void loadMenus(HttpServletRequest request){
		
		List<MenuInfo> menus = new ArrayList<MenuInfo>();
		
		//取得所有菜单
		List<MenuEntity> allMenus = this.uCenterService.getAllMenus();
		
		for (MenuEntity menu : allMenus) {
			//先遍历出第1级菜单
			if(menu.getParentId()==0){
				MenuInfo currentMenu = new MenuInfo(menu.getId(), menu.getMenuName(),menu.getMenuCode(), menu.getMenuUrl());
				menus.add(currentMenu);
				this.loadChildMenus(currentMenu, allMenus);
			}
			
		}
		
		request.getSession().setAttribute("menus", menus);
		
		
	}
	
	private void loadChildMenus(MenuInfo currentMenu,List<MenuEntity> allMenus){
		
		for (MenuEntity menu : allMenus) {
			//如果是当前菜单的子菜单
			if(menu.getParentId() == currentMenu.getMenuId()){
				MenuInfo childMenu = new MenuInfo(menu.getId(), menu.getMenuName(),menu.getMenuCode(), menu.getMenuUrl());
				currentMenu.addChild(childMenu);
				
				//递归
				this.loadChildMenus(childMenu, allMenus);
			}
			
		}
				
	}
	
	
	
	public void reloadResourceForUser(HttpServletRequest request){
		
		this.loadMenus(request);
		
	}
	
	
}
