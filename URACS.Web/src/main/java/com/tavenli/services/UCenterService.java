package com.tavenli.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavenli.entity.MenuEntity;
import com.tavenli.entity.RoleEntity;
import com.tavenli.entity.UserEntity;
import com.tavenli.model.PageData;
import com.tavenli.repository.MenuDao;
import com.tavenli.repository.RoleDao;
import com.tavenli.repository.UserDao;

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

	public List<RoleEntity> getAvailableRoles(){
		return this.roleDao.getRoles(1);
	}
	
	public List<MenuEntity> getAllMenus(){
		return this.menuDao.getMenus();
		
	}
	
	public List<MenuEntity> getChildMenus(int parentId) {
		return this.menuDao.getChildMenus(parentId);
		
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
