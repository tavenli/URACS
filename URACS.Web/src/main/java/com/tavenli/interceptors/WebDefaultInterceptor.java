package com.tavenli.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 全站拦截器
 * 
 * @author Taven
 *
 */
public class WebDefaultInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(WebDefaultInterceptor.class);
	
}
