package com.tavenli.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {

		return "index";

	}
	
	@RequestMapping("/login")
	public String login() {

		return "login";

	}
	
	@RequestMapping("/help")
	public String help() {

		return "index";

	}
	
}
