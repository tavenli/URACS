package com.tavenli.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * 访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源
 * 
 * 另外还可以参考一下 AbstractAccessDecisionManager
 * 
 * @author Taven.Li
 *
 */
@Service
public class WebAccessDecisionManager implements AccessDecisionManager {
	
	private static Logger logger = LoggerFactory.getLogger(WebAccessDecisionManager.class);

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		if(configAttributes == null){
            return ;
        }
		
        Iterator<ConfigAttribute> ite=configAttributes.iterator();
        while(ite.hasNext()){
            ConfigAttribute ca=ite.next();
            String needRole=((SecurityConfig)ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
                if(needRole.equals(ga.getAuthority())){
                    return;
                }
            }
        }
        
        throw new AccessDeniedException("No Right");

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return true;
	}

}
