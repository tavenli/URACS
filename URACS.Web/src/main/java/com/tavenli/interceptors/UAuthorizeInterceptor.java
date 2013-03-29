package com.tavenli.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户中心的拦截器
 * 
 * @author Taven
 *
 */
public class UAuthorizeInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(UAuthorizeInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//你可以在这里做一些拦截处理，当然你也可以什么都不做，如果您只关注 Spring Security，可以不关注 HandlerInterceptor 接口
		
		//String path = request.getContextPath();
		//response.sendRedirect(path + "/index");
				
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		
	}


	
}
