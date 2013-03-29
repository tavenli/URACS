package com.tavenli.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 全站拦截器
 * 
 * 可以重写父类 preHandle 、postHandle 、afterCompletion 方法，实现对全局页面请求的一些逻辑处理
 * 
 * @author Taven
 *
 */
public class WebDefaultInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(WebDefaultInterceptor.class);
	
}
