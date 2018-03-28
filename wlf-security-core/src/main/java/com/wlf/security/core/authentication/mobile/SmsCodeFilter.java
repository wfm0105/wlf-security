package com.wlf.security.core.authentication.mobile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wlf.security.core.properties.Constants;
import com.wlf.security.core.properties.SecurityProperties;
import com.wlf.security.core.validate.code.ValidateCode;
import com.wlf.security.core.validate.code.ValidateCodeException;
import com.wlf.security.core.validate.code.image.ImageCode;

/**
 * 
 * 验证码过滤器
 * 
 * @author wulinfeng
 *
 */
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean{

	private Logger logger = LoggerFactory.getLogger(SmsCodeFilter.class);
	
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	private SecurityProperties securityProperties;
	
	private Set<String> urls = new HashSet<String>();
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String[] urlList = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidateCode().getSmsCode().getSmsCodeUrl(), ",");
		for (String url : urlList) {
			urls.add("/**/"+url);
		}
		urls.add("/**/"+Constants.DEFAULT_MOBLIE_AUTHENTICATION_URL);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		boolean needValidate = false;
		for (String url : urls) {
			if(
				antPathMatcher.match(url, request.getRequestURI()) &&
				StringUtils.equalsIgnoreCase("post", request.getMethod())
			) {
				needValidate = true;
			} 
		}
		
		if(needValidate) {
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), securityProperties.getValidateCode().getSmsCode().getSmsCodeParamName());
		
		if(StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码不能为空！");
		}
		
		switch(securityProperties.getValidateCode().getSmsCode().getSmsCodeStoreMethod()) {
			case SESSION : {
				ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request, securityProperties.getValidateCode().getSmsCode().getSmsCodeSessionKey());
				if(codeInSession == null || !StringUtils.equalsIgnoreCase(codeInRequest, codeInSession.getCode())) {
					throw new ValidateCodeException("验证码错误，或已过期！");
				}
				break;
			}
			
			case REDIS : {
				if(StringUtils.isBlank(redisTemplate.opsForValue().get(securityProperties.getValidateCode().getSmsCode().getSmsCodeKeyPrefix()+codeInRequest))){
					throw new ValidateCodeException("验证码错误，或已过期！");
				}
				break;
			}
		}
		
		sessionStrategy.removeAttribute(request, securityProperties.getValidateCode().getSmsCode().getSmsCodeSessionKey());

	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
}
