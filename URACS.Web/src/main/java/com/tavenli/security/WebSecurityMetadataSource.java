package com.tavenli.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Service;

/**
 * [核心处理逻辑]
 * 
 * 资源源数据定义，即定义某一资源可以被哪些角色访问
 * 资源与权限的对应关系
 * 
 * @author Taven
 *
 */
@Service
public class WebSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static Logger logger = LoggerFactory.getLogger(WebSecurityMetadataSource.class);

	//加载资源配置？
	private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
	
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		
		HttpServletRequest request = ((FilterInvocation)object).getRequest();
        Iterator<String> ite = resourceMap.keySet().iterator();
        
        while (ite.hasNext()) {
            String resourceURL = ite.next();
            //AntPathRequestMatcher : 来自于Ant项目，是一种简单易懂的路径匹配策略。
            //RegexRequestMatcher : 如果 AntPathRequestMatcher 无法满足需求，
            //还可以选择使用更强大的RegexRequestMatcher，它支持使用正则表达式对URL地址进行匹配
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resourceURL);
            if (requestMatcher.matches(request)) {
                return resourceMap.get(resourceURL);
            }
        }
        return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return false;
	}

}
