package com.tavenli.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tavenli.security.WebUserDetails;

public abstract class UBaseController {

	protected WebUserDetails getUserInfo(){
		WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return webUserDetails;
	}
	
}
