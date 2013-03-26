package com.tavenli.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tavenli.entity.UserEntity;
import com.tavenli.entity.UserRoleEntity;
import com.tavenli.services.UCenterService;

/**
 * 实现 UserDetailsService 接口，主要是在 loadUserByUsername 方法中验证一个用户
 * 
 * 这里需要从数据库中读取验证表单提交过来的用户
 * 
 * @author Taven
 *
 */
public class WebUserDetailsService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(WebUserDetailsService.class);
	
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	@Autowired
	private UCenterService uCenterService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//该方法负责实现验证并授权
		
		UserEntity userEntity = uCenterService.getUserByUserName(username);
		
		if (null == userEntity) {
			throw new UsernameNotFoundException(
							messages.getMessage("User.notFound", new Object[] { username }, "Username {0} not found"));
		}
		
		long userId = userEntity.getId();
		String password = userEntity.getPassWord();
		boolean userEnabled = userEntity.getStatus() == 1;
		
		//读取当前用户有哪些权限
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<UserRoleEntity> userRoles = uCenterService.getUserRole(userId);
		for (UserRoleEntity userRole : userRoles) {
			
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(String.valueOf(userRole.getRoleId()));

			authorities.add(authority);
		}
		
		//创建 UserDetails 对象
		WebUserDetails webUserDetails = new WebUserDetails(username, password, userEnabled, authorities);
		
		return webUserDetails;
		
	}

}
