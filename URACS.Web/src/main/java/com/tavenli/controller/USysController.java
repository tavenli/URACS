package com.tavenli.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tavenli.entity.MenuEntity;
import com.tavenli.entity.RoleEntity;
import com.tavenli.entity.UserEntity;
import com.tavenli.model.PageData;
import com.tavenli.security.WebUserDetails;
import com.tavenli.services.UCenterService;
import com.tavenli.utils.PageNavUtil;


@Controller
@RequestMapping("/u")
public class USysController extends UBaseController {

	private static Logger logger = LoggerFactory.getLogger(USysController.class);
	
	@Autowired
	private UCenterService uCenterService;
	
	@RequestMapping("/users")
	public String userList(Model model,Integer page,String userName) {
		
		page = page== null ? 1 : page<1 ? 1 : page;
		
		int pageSize = 10;
		
		PageData<UserEntity> pageData = this.uCenterService.getUsers(page, pageSize, userName);
		
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("totalCount", pageData.getTotalCount());
		model.addAttribute("totalPage", pageData.getTotalPage());
		model.addAttribute("currentPage", page);
		model.addAttribute("pageNav", PageNavUtil.getPageNavHtml(page.intValue(), pageSize, pageData.getTotalCount(), 15));
		
		return "ucenter/sys/userList";
	}
	
	@RequestMapping("/userAdd")
	public String userAdd(Model model,UserEntity userEntity) {
		userEntity.setStatus(1);
		model.addAttribute("userEntity", userEntity);
		return "ucenter/sys/userEdit";
	}
	
	@RequestMapping("/userEdit")
	public String userEdit(Model model,int id) {
		
		UserEntity userEntity = this.uCenterService.getUser(id);
		model.addAttribute("userEntity", userEntity);
		return "ucenter/sys/userEdit";
	}
	
	@RequestMapping("/changePwd")
	public String changePwd(Model model) {
		WebUserDetails userInfo = this.getUserInfo();
		int userId = userInfo.getUserId();
		
		UserEntity userEntity = this.uCenterService.getUser(userId);
		model.addAttribute("userEntity", userEntity);
		return "ucenter/sys/userEdit";
	}
	
	@RequestMapping("/roles")
	public String roleList(Model model, Integer page,String roleName) {
		
		page = page== null ? 1 : page<1 ? 1 : page;
		int pageSize = 10;
		PageData<RoleEntity> pageData = this.uCenterService.getRoles(page, pageSize, roleName);
		
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("totalCount", pageData.getTotalCount());
		model.addAttribute("totalPage", pageData.getTotalPage());
		model.addAttribute("currentPage", page);
		model.addAttribute("pageNav", PageNavUtil.getPageNavHtml(page.intValue(), 10, pageData.getTotalCount(), 15));
		
		return "ucenter/sys/roleList";
	}
	
	@RequestMapping("/roleAdd")
	public String roleAdd(Model model,RoleEntity roleEntity) {
		roleEntity.setStatus(1);
		model.addAttribute("roleEntity", roleEntity);
		return "ucenter/sys/roleEdit";
	}
	
	@RequestMapping("/roleEdit")
	public String roleEdit(Model model,int id) {
		
		RoleEntity roleEntity = this.uCenterService.getRole(id);
		model.addAttribute("roleEntity", roleEntity);
		return "ucenter/sys/roleEdit";
	}
	
	@RequestMapping("/menus")
	public String menuList(Model model, Integer page,String menuName) {
		
		page = page== null ? 1 : page<1 ? 1 : page;
		int pageSize = 10;
		PageData<MenuEntity> pageData = this.uCenterService.getMenus(page, pageSize, menuName);
		
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("totalCount", pageData.getTotalCount());
		model.addAttribute("totalPage", pageData.getTotalPage());
		model.addAttribute("currentPage", page);
		model.addAttribute("pageNav", PageNavUtil.getPageNavHtml(page.intValue(), 10, pageData.getTotalCount(), 15));
		
		return "ucenter/sys/menuList";
	}
	
	@RequestMapping("/menuAdd")
	public String menuAdd(Model model,MenuEntity menuEntity) {

		model.addAttribute("menuEntity", menuEntity);
		return "ucenter/sys/menuEdit";
	}
	
	@RequestMapping("/menuEdit")
	public String menuEdit(Model model,int id) {
		
		MenuEntity menuEntity = this.uCenterService.getMenu(id);
		model.addAttribute("menuEntity", menuEntity);
		return "ucenter/sys/menuEdit";
	}
	
}