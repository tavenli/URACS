package com.tavenli.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavenli.entity.MenuEntity;
import com.tavenli.entity.RoleEntity;
import com.tavenli.entity.UserEntity;
import com.tavenli.model.PageData;
import com.tavenli.repository.MenuDao;
import com.tavenli.repository.RoleDao;
import com.tavenli.repository.UserDao;
import com.tavenli.utils.DEncryptionUtils;
import com.tavenli.utils.DateUtil;

@Service
public class UCenterService {

	private static Logger logger = LoggerFactory.getLogger(UCenterService.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	
	
	public UserEntity getUserByUserName(String userName){
		
		try {
			return this.userDao.getSingleUser(userName);
		} catch (Exception e) {
			logger.error("查用户 "+userName+" 数据出错");
		}
		return null;
		
	}
	
	public UserEntity getUser(int id){
		return this.userDao.getUserById(id);
	}
	
	@Transactional
	public int updateUserStatus(int id,int status){
		return this.userDao.updateUserStatus(id, status);
	}
	
	@Transactional(readOnly = false)
	public int delUser(int userId){
		return this.userDao.delUser(userId);
	}
	
	public int delUsers(Integer[] userIds){
		return this.userDao.delUsers(userIds);
	}
	
	public boolean saveUser(UserEntity userEntity) {
		int userId = userEntity.getId();
		String userName = userEntity.getUserName();
		String passWord = userEntity.getPassWord();
		
		if (userId > 0) {
			UserEntity updateEntity = this.userDao.getUserById(userId);
			
			updateEntity.setStatus(userEntity.getStatus());
			updateEntity.setUserName(userName);
			updateEntity.setLastUpdate(DateUtil.getCurrentTime());
			
			if(StringUtils.isNotBlank(passWord)){
				updateEntity.setPassWord(DEncryptionUtils.standPwdEncoder(passWord));
			}
			
			try {
				this.userDao.update(updateEntity);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return false;
			}
		}else{
			//新增
			if(this.userDao.existUser(userName)){
				return false;
			}
			
			if(StringUtils.isBlank(passWord)){
				//新增用户，如果用户没有输入密码，则设置默认的密码
				passWord = "123456";
			}
			
			userEntity.setCreateTime(DateUtil.getCurrentTime());
			userEntity.setLastUpdate(DateUtil.getCurrentTime());
			userEntity.setPassWord(DEncryptionUtils.standPwdEncoder(passWord));
			
			try {
				this.userDao.save(userEntity);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return false;
			}
			
		}
		
		return true;
	}
	
	public RoleEntity getRole(int id){
		return this.roleDao.getRoleById(id);
	}
	
	@Transactional
	public int updateRoleStatus(int id,int status){
		return this.roleDao.updateRoleStatus(id, status);
	}
	
	@Transactional
	public int delRole(int roleId){
		return this.roleDao.delRole(roleId);
	}
	
	public boolean saveRole(RoleEntity roleEntity) {
		int roleId = roleEntity.getId();
		String roleName = roleEntity.getRoleName();
		
		if (roleId > 0) {
			RoleEntity updateEntity = this.roleDao.getRoleById(roleId);
			
			updateEntity.setStatus(roleEntity.getStatus());
			updateEntity.setRoleName(roleName);
			updateEntity.setLastUpdate(DateUtil.getCurrentTime());

			
			try {
				this.roleDao.update(updateEntity);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return false;
			}
		}else{
			//新增			
			roleEntity.setCreateTime(DateUtil.getCurrentTime());
			roleEntity.setLastUpdate(DateUtil.getCurrentTime());
			
			try {
				this.roleDao.save(roleEntity);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return false;
			}
			
		}
		
		return true;
	}
	
	public MenuEntity getMenu(int id){
		return this.menuDao.getMenuById(id);
	}

	@Transactional
	public int delMenu(int menuId){
		return this.menuDao.delMenu(menuId);
	}
	
	public List<RoleEntity> getAvailableRoles(){
		return this.roleDao.getRoles(1);
	}
	
	public List<MenuEntity> getAllMenus(){
		return this.menuDao.getMenus();
		
	}
	
	public List<MenuEntity> getChildMenus(int parentId) {
		return this.menuDao.getChildMenus(parentId);
		
	}
	
	public boolean saveMenu(MenuEntity menuEntity) {
		int menuId = menuEntity.getId();
		String menuName = menuEntity.getMenuName();
		
		if (menuId > 0) {
			MenuEntity updateEntity = this.menuDao.getMenuById(menuId);
			
			updateEntity.setMenuName(menuName);
			updateEntity.setMenuCode(menuEntity.getMenuCode());
			updateEntity.setMenuUrl(menuEntity.getMenuUrl());
			updateEntity.setLastUpdate(DateUtil.getCurrentTime());

			
			try {
				this.menuDao.update(updateEntity);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return false;
			}
		}else{
			//新增			
			menuEntity.setCreateTime(DateUtil.getCurrentTime());
			menuEntity.setLastUpdate(DateUtil.getCurrentTime());
			
			try {
				this.menuDao.save(menuEntity);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return false;
			}
			
		}
		
		
		return true;
	}
	
	public PageData<UserEntity> getUsers(int pageIndex, int pageSize,String userName){
		
		PageData<UserEntity> pageData = new PageData<UserEntity>(pageIndex, pageSize);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		
		int totalCount = userDao.queryDataCount(paramMap);
		List<UserEntity> list = userDao.queryPageData(pageData.getStartRow(), pageSize,paramMap);
		
		pageData.setTotalCount(totalCount);
		pageData.setPageData(list);
		
		return pageData;
		
	}
	
	public PageData<RoleEntity> getRoles(int pageIndex, int pageSize,String roleName){
		
		PageData<RoleEntity> pageData = new PageData<RoleEntity>(pageIndex, pageSize);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleName", roleName);
		
		int totalCount = roleDao.queryDataCount(paramMap);
		List<RoleEntity> list = roleDao.queryPageData(pageData.getStartRow(), pageSize,paramMap);
		
		pageData.setTotalCount(totalCount);
		pageData.setPageData(list);
		
		return pageData;
		
	}
	
	public PageData<MenuEntity> getMenus(int pageIndex, int pageSize,String menuName){
		
		PageData<MenuEntity> pageData = new PageData<MenuEntity>(pageIndex, pageSize);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuName", menuName);
		
		int totalCount = menuDao.queryDataCount(paramMap);
		List<MenuEntity> list = menuDao.queryPageData(pageData.getStartRow(), pageSize,paramMap);
		
		pageData.setTotalCount(totalCount);
		pageData.setPageData(list);
		
		return pageData;
		
	}
	
}
