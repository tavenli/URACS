package com.tavenli.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavenli.entity.MenuEntity;
import com.tavenli.entity.RoleEntity;
import com.tavenli.services.UCenterService;

/**
 * [核心处理逻辑]
 * 
 * 资源源数据定义，即定义某一资源可以被哪些角色访问
 * 建立资源与权限的对应关系
 * 
 * 也可以直接使用Spring提供的类 DefaultFilterInvocationSecurityMetadataSource
 * 
 * @author Taven.Li
 *
 */
@Service
public class WebSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static Logger logger = LoggerFactory.getLogger(WebSecurityMetadataSource.class);
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
	
	@Autowired
	private UCenterService uCenterService;
	
	/**
	 * 初始化资源配置
	 * 
	 * spring 调用该方法的方式有2种
	 * 方式1，方法上加注解：
	 * @PostConstruct
	 * 
	 * 方式2，配置文件中 init-method 属性指定：
	 * <beans:bean id="webSecurityMetadataSource" init-method="initResource" class="com.tavenli.security.WebSecurityMetadataSource"/>
	 */
	@PostConstruct
	public void initResource(){
		
		resourceMap.clear();
		
		//取得当前系统所有可用角色
		List<RoleEntity> roles = this.uCenterService.getAvailableRoles();
		for (RoleEntity role : roles) {
			//这里的 role 参数为自己定义的，要和 UserDetailsService 中的 SimpleGrantedAuthority 参数对应
			//role 参数也可以直接使用角色名
			ConfigAttribute ca = new SecurityConfig("ROLE_"+role.getId());
			//取角色有哪些资源的权限
			Set<MenuEntity> menus = role.getMenus();
			for (MenuEntity menu : menus) {
				String menuUrl = menu.getMenuUrl();
				if(StringUtils.isBlank(menuUrl)){
					//不是菜单地址，跳过
					continue;
				}
				
				//如果是URL资源，则建立角色与资源关系
				if(resourceMap.containsKey(menuUrl)) {
					
    				resourceMap.get(menuUrl).add(ca);
    				
    			} else {
    				
    	        	Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
    	        	atts.add(ca);
    				resourceMap.put(menuUrl, atts);
    				
    			}
					
				
			}
			
		}
		
		
		//为超级管理员添加所有资源权限
		this.initSuperUserResource();
		
		
	}
	
	private void initSuperUserResource() {

		// 添加超级管理员角色
		//ROLE_SUPER 这个权限名字也是自己定义的
		ConfigAttribute superCA = new SecurityConfig("ROLE_SUPER");
		// 超级管理员有所有菜单权限
		List<MenuEntity> menus = this.uCenterService.getAllMenus();
		for (MenuEntity menu : menus) {
			String menuUrl = menu.getMenuUrl();
			if (StringUtils.isBlank(menuUrl)) {
				// 不是菜单地址，跳过
				continue;
			}

			if (resourceMap.containsKey(menuUrl)) {

				resourceMap.get(menuUrl).add(superCA);

			} else {

				Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
				atts.add(superCA);
				resourceMap.put(menuUrl, atts);

			}

		}

	}
	
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
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
