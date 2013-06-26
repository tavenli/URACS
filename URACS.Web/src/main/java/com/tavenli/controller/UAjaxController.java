package com.tavenli.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tavenli.entity.MenuEntity;
import com.tavenli.entity.RoleEntity;
import com.tavenli.entity.UserEntity;
import com.tavenli.model.PageData;
import com.tavenli.model.TreeData;
import com.tavenli.model.UserInfo;
import com.tavenli.security.WebSecurityMetadataSource;
import com.tavenli.security.WebUserDetails;
import com.tavenli.services.UCenterService;
import com.tavenli.services.UResourceService;
import com.tavenli.utils.PageNavUtil;
import com.tavenli.utils.ValidatorUtil;

@Controller
@RequestMapping("/u")
public class UAjaxController extends UBaseController {

	private static Logger logger = LoggerFactory.getLogger(UAjaxController.class);
	
	@Autowired
	private UCenterService uCenterService;
	@Autowired
	private UResourceService uResourceService;
	@Autowired
	private WebSecurityMetadataSource webSecurityMetadataSource;
	
	@RequestMapping("/queryUsers")
	@ResponseBody
	public Map<String, Object> queryUsers(Model model,Integer page,String userName){
		boolean resultStatus = true;
		page = page== null ? 1 : page<1 ? 1 : page;
				
		int pageSize = 10;
		
		PageData<UserEntity> pageData = this.uCenterService.getUsers(page, pageSize, userName);
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("datas", pageData.getPageData());
		resMap.put("totalCount", pageData.getTotalCount());
		resMap.put("totalPage", pageData.getTotalPage());
		resMap.put("currentPage", page);
		resMap.put("pageNav", PageNavUtil.getAjaxPageNavHtml(page.intValue(), pageSize, pageData.getTotalCount(), 15));
		
		resMap.put("resultStatus", resultStatus);
		return resMap;
		
	}
	
	@RequestMapping(value = "/changeUserStatus", method = RequestMethod.POST)
	@ResponseBody
	public boolean changeUserStatus(int id,int status){
		int resultCode = uCenterService.updateUserStatus(id, status);
		return resultCode > 0;
	}
	
	@RequestMapping(value = "/delUser", method = RequestMethod.POST)
	@ResponseBody
	public int delUser(int id) {
		//删除单个
		int resultCode = uCenterService.delUser(id);
		return resultCode;
	}
	
	@RequestMapping(value = "/delUsers", method = RequestMethod.POST)
	@ResponseBody
	public int delUsers(Integer[] userIds) {
		//批量删除
		int resultCode = uCenterService.delUsers(userIds);
		return resultCode;
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveUser(Model model,UserEntity userEntity) {
		boolean resultStatus = true;
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		String userName = userEntity.getUserName();
		if(userEntity==null || StringUtils.isBlank(userName)){
			resultStatus = false;
			resMap.put("errorMsg", "用户名不能为空");
			resMap.put("resultStatus", resultStatus);
			return resMap;
		}
		
		//过滤非法字符
		userName = ValidatorUtil.filterUnSafeChar(userName).trim();
		userEntity.setUserName(userName);
		
		resultStatus = this.uCenterService.saveUser(userEntity);
		
		resMap.put("resultStatus", resultStatus);
		return resMap;
		
	}
	
	@RequestMapping(value = "/delRole", method = RequestMethod.POST)
	@ResponseBody
	public int delRole(int id) {
		//删除单个
		int resultCode = uCenterService.delRole(id);
		return resultCode;
	}
	
	@RequestMapping(value = "/changeRoleStatus", method = RequestMethod.POST)
	@ResponseBody
	public boolean changeRoleStatus(int id,int status){
		int resultCode = uCenterService.updateRoleStatus(id, status);
		return resultCode > 0;
	}
	
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveRole(Model model,RoleEntity roleEntity) {
		boolean resultStatus = true;
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		String roleName = roleEntity.getRoleName();
		if(roleEntity==null || StringUtils.isBlank(roleName)){
			resultStatus = false;
			resMap.put("errorMsg", "角色名不能为空");
			resMap.put("resultStatus", resultStatus);
			return resMap;
		}
		
		//过滤非法字符
		roleName = ValidatorUtil.filterUnSafeChar(roleName).trim();
		roleEntity.setRoleName(roleName);
		
		resultStatus = this.uCenterService.saveRole(roleEntity);
		
		resMap.put("resultStatus", resultStatus);
		return resMap;
		
	}
	
	@RequestMapping(value = "/delMenu", method = RequestMethod.POST)
	@ResponseBody
	public int delMenu(int id) {
		//删除单个
		int resultCode = uCenterService.delMenu(id);
		return resultCode;
	}
	
	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveMenu(Model model,MenuEntity menuEntity) {
		boolean resultStatus = true;
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		String menuName = menuEntity.getMenuName();
		if(menuEntity==null || StringUtils.isBlank(menuName)){
			resultStatus = false;
			resMap.put("errorMsg", "角色名不能为空");
			resMap.put("resultStatus", resultStatus);
			return resMap;
		}
		
		//过滤非法字符
		menuName = ValidatorUtil.filterUnSafeChar(menuName).trim();
		menuEntity.setMenuName(menuName);
		
		resultStatus = this.uCenterService.saveMenu(menuEntity);
		
		resMap.put("resultStatus", resultStatus);
		return resMap;
		
	}
	
	
	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveUserRole(Model model,UserInfo userInfo) {
		boolean resultStatus = true;
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		int userId = userInfo.getId();
		if(userId<=0){
			resultStatus = false;
			resMap.put("errorMsg", "没有指定用户");
			resMap.put("resultStatus", resultStatus);
			return resMap;
		}
		
		
		resultStatus = this.uCenterService.saveUserRole(userInfo);
		
		resMap.put("resultStatus", resultStatus);
		return resMap;
		
	}
	
	@RequestMapping(value = "/getMenuTree", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeData> getMenuTree(Model model) {
		
		List<TreeData> treeData = this.uResourceService.getTreeData();
		
		return treeData;
	}
	
	
	@RequestMapping(value = "/saveRoleResource", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveRoleResource(Model model,int roleId,String menuIds) {
		boolean resultStatus = true;
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		//需要注意的是jsTree如果一个节点下所有子节点都被选中，则只会返回这个父节点的ID，下面的子节点ID不会返回
		String[] mIds = menuIds.split(",");
		
		resultStatus = this.uCenterService.saveRoleResource(roleId,mIds);
		if(resultStatus){
			//角色权限改变，重新更新资源和角色关系
			webSecurityMetadataSource.reloadResource();
		}
		
		resMap.put("resultStatus", resultStatus);
		return resMap;
		
	}
	
	@RequestMapping(value = "/saveUserPwd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveUserPwd(Model model,String passWord,String rePassWord) {
		boolean resultStatus = true;
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		if(StringUtils.isBlank(passWord) || StringUtils.isBlank(rePassWord)){
			resultStatus = false;
			resMap.put("errorMsg", "密码不能为空");
			resMap.put("resultStatus", resultStatus);
			return resMap;
		}
		
		if(!passWord.equals(rePassWord)){
			resultStatus = false;
			resMap.put("errorMsg", "两次输入的密码不一致");
			resMap.put("resultStatus", resultStatus);
			return resMap;
		}
		
		//取当前登录用户
		WebUserDetails userInfo = this.getUserInfo();
		int userId = userInfo.getUserId();
		
		UserEntity userEntity = this.uCenterService.getUser(userId);
		
		userEntity.setPassWord(passWord);
		
		resultStatus = this.uCenterService.saveUser(userEntity);
		
		resMap.put("resultStatus", resultStatus);
		return resMap;
		
	}
	
}
