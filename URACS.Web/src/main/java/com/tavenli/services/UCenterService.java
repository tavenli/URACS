package com.tavenli.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tavenli.entity.UserEntity;
import com.tavenli.entity.UserRoleEntity;

@Service
public class UCenterService {

	private static Logger logger = LoggerFactory.getLogger(UCenterService.class);
	
	public UserEntity getUserByUserName(String userName){
		
		return null;
		
	}
	
	public List<UserRoleEntity> getUserRole(long userId){
		return null;
	}
	
	
}
