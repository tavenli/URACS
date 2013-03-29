package com.tavenli.services;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavenli.entity.MenuEntity;
import com.tavenli.entity.RoleEntity;
import com.tavenli.entity.UserEntity;
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
			logger.error("查用户 "+userName+" 数据出错",e);
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
}
