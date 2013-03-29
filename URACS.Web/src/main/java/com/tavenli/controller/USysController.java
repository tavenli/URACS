package com.tavenli.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tavenli.entity.MenuEntity;
import com.tavenli.entity.RoleEntity;
import com.tavenli.entity.UserEntity;
import com.tavenli.model.PageData;
import com.tavenli.security.WebUserDetails;
import com.tavenli.utils.PageNavUtil;


@Controller
@RequestMapping("/u")
public class USysController extends UBaseController {

	private static Logger logger = LoggerFactory.getLogger(USysController.class);
	
	@RequestMapping("users")
	public String userList(Model model,Integer page) {
		
		page = page== null ? 1 : page<1 ? 1 : page;
		
		WebUserDetails userInfo = this.getUserInfo();
		
		PageData<UserEntity> pageData = new PageData<UserEntity>(page, 10);
		
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("totalCount", pageData.getTotalCount());
		model.addAttribute("totalPage", pageData.getTotalPage());
		model.addAttribute("currentPage", page);
		model.addAttribute("pageNav", PageNavUtil.getPageNavHtml(page.intValue(), 10, pageData.getTotalCount(), 15));
		
		return "ucenter/sys/userList";
	}
	
	@RequestMapping("roles")
	public String roleList(Model model, Integer page) {
		
		page = page== null ? 1 : page<1 ? 1 : page;
		
		PageData<RoleEntity> pageData = new PageData<RoleEntity>(page, 10);
		
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("totalCount", pageData.getTotalCount());
		model.addAttribute("totalPage", pageData.getTotalPage());
		model.addAttribute("currentPage", page);
		model.addAttribute("pageNav", PageNavUtil.getPageNavHtml(page.intValue(), 10, pageData.getTotalCount(), 15));
		
		return "ucenter/sys/roleList";
	}
	
	@RequestMapping("menus")
	public String menuList(Model model, Integer page) {
		
		page = page== null ? 1 : page<1 ? 1 : page;
		
		PageData<MenuEntity> pageData = new PageData<MenuEntity>(page, 10);
		
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("totalCount", pageData.getTotalCount());
		model.addAttribute("totalPage", pageData.getTotalPage());
		model.addAttribute("currentPage", page);
		model.addAttribute("pageNav", PageNavUtil.getPageNavHtml(page.intValue(), 10, pageData.getTotalCount(), 15));
		
		return "ucenter/sys/menuList";
	}
	
	
}