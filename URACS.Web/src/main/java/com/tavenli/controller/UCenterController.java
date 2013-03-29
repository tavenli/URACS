package com.tavenli.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tavenli.entity.MenuEntity;
import com.tavenli.model.MenuInfo;
import com.tavenli.services.UCenterService;
import com.tavenli.utils.ValidatorUtil;


@Controller
@RequestMapping("/u")
public class UCenterController {

	private static Logger logger = LoggerFactory.getLogger(UCenterController.class);
	
	@Autowired
	private UCenterService uCenterService;
	
	
	@RequestMapping(value = { "/", "/index", "/main" })	
	public String index(Model model,HttpServletRequest request,HttpServletResponse response) {
		
		this.loadMenus(request);
		
		String userIp = ValidatorUtil.getIpAddr(request);
		
		int totalUserCount = 0;
		
		model.addAttribute("userIp", userIp);
		model.addAttribute("totalUserCount", totalUserCount);
		
		return "ucenter/index";

	}
	
	@RequestMapping("/accessDenied")
	public String accessDenied(){
		
		return "ucenter/not_permission";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		
		return "redirect:/j_spring_security_logout";
		//return "logout";
	}
	
	private void loadMenus(HttpServletRequest request){
		
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
	
	
	
}