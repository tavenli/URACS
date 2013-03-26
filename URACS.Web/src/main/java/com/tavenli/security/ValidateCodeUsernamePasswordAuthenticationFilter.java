package com.tavenli.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 实现带验证码的登录验证，在这里还可以实现登录验证的其他参数接收和处理
 * 
 * @author Taven
 *
 */
public class ValidateCodeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static Logger logger = LoggerFactory.getLogger(ValidateCodeUsernamePasswordAuthenticationFilter.class);
	
	/**
	 * 是否只接收POST方式提交的验证数据
	 */
	private boolean postOnly = true;
	
	/**
	 * 验证码对应的表单参数名称
	 */
	private String validateCodeParameter = "validateCode";
	
	/**
	 * 验证码保存在session中的名称
	 */
	private String validateCodeSessionName = "validateCode";
	
	/**
	 * 验证成功，跳转的页面
	 * 注意：地址必须是 / 或 http 开头的URL地址
	 */
	private String successUrl = "/";
	/**
	 * 
	 */
	private String failureUrl = "/";

	public ValidateCodeUsernamePasswordAuthenticationFilter(){
		//配置接收参数的表单名称，默认是 j_username 和 j_password
		//可以在这里手工指定，也可以在Spring配置中注入属性
		//this.setUsernameParameter("userName");
		//this.setPasswordParameter("passWord");
		
		//验证成功，跳转的页面
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl(successUrl);		
		this.setAuthenticationSuccessHandler(successHandler);
		
		//验证失败，跳转的页面
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl(failureUrl);
		this.setAuthenticationFailureHandler(failureHandler);
		
	}
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
        	//这里可以直接抛出异常，也可以直接跳转
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
            //或
            //request.getRequestDispatcher("/errorPage").forward(request, response);
        }
        
        //取用户名密码前，设置编码格式
        //request.setCharacterEncoding("UTF-8");
        
        //校验验证码是否输入正确
        checkValidateCode(request);

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        
        return this.getAuthenticationManager().authenticate(authRequest);
    }
	
	protected void checkValidateCode(HttpServletRequest request) {
		String sessionValidateCode = obtainSessionValidateCode(request);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		//验证码输入不能为空，不区分大小写
		if (StringUtils.isBlank(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			throw new AuthenticationServiceException(messages.getMessage("validateCode.notEquals"));
		}
	}


	public boolean isPostOnly() {
		return postOnly;
	}

	@Override
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}
	
	private String obtainValidateCodeParameter(HttpServletRequest request) {
		return request.getParameter(validateCodeParameter);
	}

	protected String obtainSessionValidateCode(HttpServletRequest request) {
		Object sessionCode = request.getSession().getAttribute(validateCodeSessionName);
		return null == sessionCode ? "" : sessionCode.toString();
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailureUrl() {
		return failureUrl;
	}

	public void setFailureUrl(String failureUrl) {
		this.failureUrl = failureUrl;
	}
	
	
}
