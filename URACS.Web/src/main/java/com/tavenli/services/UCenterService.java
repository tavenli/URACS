package com.tavenli.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavenli.entity.RoleEntity;
import com.tavenli.entity.UserEntity;
import com.tavenli.repository.RoleDao;
import com.tavenli.repository.UserDao;

@Service
public class UCenterService {

	private static Logger logger = LoggerFactory.getLogger(UCenterService.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
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
	
}
