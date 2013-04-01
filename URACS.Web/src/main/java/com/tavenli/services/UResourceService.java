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
import com.tavenli.model.NodeData;
import com.tavenli.model.TreeData;

@Service
public class UResourceService {

	private static Logger logger = LoggerFactory.getLogger(UResourceService.class);
	
	@Autowired
	private UCenterService uCenterService;
	
		
	public void reloadResourceForUser(HttpServletRequest request){
		
		//取得所有可显示的菜单
		List<MenuEntity> allMenus = this.uCenterService.getAllNavMenus();
				
		List<MenuInfo> menus = this.loadMenuInfos(allMenus);
		
		request.getSession().setAttribute("menus", menus);
		
	}
	
	public List<MenuInfo> loadMenuInfos(List<MenuEntity> allMenus){
		
		List<MenuInfo> menus = new ArrayList<MenuInfo>();
				
		for (MenuEntity menu : allMenus) {
			//先遍历出第1级菜单
			if(menu.getParentId()==0){
				MenuInfo currentMenu = new MenuInfo(menu.getId(), menu.getMenuName(),menu.getMenuCode(), menu.getMenuUrl());
				menus.add(currentMenu);
				this.loadChildMenus(currentMenu, allMenus);
			}
			
		}
		
		return menus;		
		
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
	
	
	public List<TreeData> getTreeData(){
		
		List<TreeData> treeDatas = new ArrayList<TreeData>();
		
		//取得所有菜单
		List<MenuEntity> allMenus = this.uCenterService.getAllMenus();
		
		List<MenuInfo> menus = this.loadMenuInfos(allMenus);
		
		
		
		for (MenuInfo menu : menus) {
			//
			TreeData treeData = new TreeData();
			NodeData nodeData = new NodeData();
			nodeData.setTitle(menu.getMenuName());
			nodeData.getAttr().put("id", menu.getMenuId());
			
			treeData.getData().add(nodeData);			
			this.loadTreeChild(treeData, menu.getChildMenus());
			treeDatas.add(treeData);
		}
		
		
		return treeDatas;
		
		
	}
	
	private void loadTreeChild(TreeData treeData,List<MenuInfo> menus){
		
		
		for (MenuInfo menu : menus) {
			TreeData child = new TreeData();
			NodeData childNode = new NodeData();
			childNode.setTitle(menu.getMenuName());
			childNode.getAttr().put("id", menu.getMenuId());
			child.getData().add(childNode);
			
			//递归
			this.loadTreeChild(child, menu.getChildMenus());
			treeData.getChildren().add(child);
		}
		
		
		
				
	}
	

	
	
}
