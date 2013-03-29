package com.tavenli.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/u")
public class UCenterController {

	private static Logger logger = LoggerFactory.getLogger(UCenterController.class);
	
	
	@RequestMapping(value = { "/", "/index", "/main" })
	public String index() {

		return "ucenter/index";

	}
	
	@RequestMapping("/logout")
	public String logout(){
		
		
		//return "redirect:/login";
		return "logout";
	}
}