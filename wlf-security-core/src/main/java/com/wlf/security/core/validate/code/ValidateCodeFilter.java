package com.wlf.security.core.validate.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wlf.security.core.Constants;
import com.wlf.security.core.common.StoreMethod;
import com.wlf.security.core.properties.SecurityProperties;
import com.wlf.security.core.validate.code.image.ImageCode;

/**
 * 
 * 验证码过滤器
 * 1、拦截指定的post请求
 * 2、验证验证码
 * 
 * @author wulinfeng
 *
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

	private Logger logger = LoggerFactory.getLogger(ValidateCodeFilter.class);
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	
	//private Set<String> urls = new HashSet<String>();
	
	private Map<String, ValidateCodeType> urlMap = new HashMap<String, ValidateCodeType>();
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		
		urlMap.put("/**/"+Constants.DEFAULT_FORM_AUTHENTICATION_URL, ValidateCodeType.IMAGE);
		addUrlToMap(securityProperties.getValidateCode().getImageCode().getImageCodeUrl(), ValidateCodeType.IMAGE);

		urlMap.put("/**/"+Constants.DEFAULT_MOBLIE_AUTHENTICATION_URL, ValidateCodeType.SMS);
		addUrlToMap(securityProperties.getValidateCode().getSmsCode().getSmsCodeUrl(), ValidateCodeType.SMS);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		ValidateCodeType type = getValidateCodeType(request);
		if(type != null) {
			try {
				validate(type, new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	protected void addUrlToMap(String urlString, ValidateCodeType type) {
		if (StringUtils.isNotBlank(urlString)) {
			String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
			for (String url : urls) {
				urlMap.put(url, type);
			}
		}
	}

	private void validate(ValidateCodeType type, ServletWebRequest request) throws ServletRequestBindingException {
		
		String codeParams = "";
		StoreMethod storeMethod = null;
		String sessionKey = "";
		String redisKeyPrefix = "";
		
		switch(type) {
			case IMAGE : {
				codeParams = securityProperties.getValidateCode().getImageCode().getImageCodeParamName();
				storeMethod = securityProperties.getValidateCode().getImageCode().getImageCodeStoreMethod();
				sessionKey = securityProperties.getValidateCode().getImageCode().getImageCodeSessionKey();
				redisKeyPrefix = securityProperties.getValidateCode().getImageCode().getImageCodeKeyPrefix();
				break;
			}
			case SMS : {
				codeParams = securityProperties.getValidateCode().getSmsCode().getSmsCodeParamName();
				storeMethod = securityProperties.getValidateCode().getSmsCode().getSmsCodeStoreMethod();
				sessionKey = securityProperties.getValidateCode().getSmsCode().getSmsCodeSessionKey();
				redisKeyPrefix = securityProperties.getValidateCode().getSmsCode().getSmsCodeKeyPrefix();
				break;
			}
		}
		
		doValidate(codeParams, storeMethod, sessionKey, redisKeyPrefix, request);
		
	}
	
	public void doValidate(String codeParams, StoreMethod storeMethod, String sessionKey, String redisKeyPrefix, ServletWebRequest request) throws ServletRequestBindingException {
		
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), codeParams);
		
		if(StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码不能为空！");
		}
		
		switch(storeMethod) {
			case SESSION : {
				ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, sessionKey);
				if(codeInSession == null || !StringUtils.equalsIgnoreCase(codeInRequest, codeInSession.getCode())) {
					throw new ValidateCodeException("验证码错误，或已过期！");
				}
				break;
			}
			
			case REDIS : {
				if(StringUtils.isBlank(redisTemplate.opsForValue().get(redisKeyPrefix+codeInRequest))){
					throw new ValidateCodeException("验证码错误，或已过期！");
				}
				break;
			}
		}
		
		sessionStrategy.removeAttribute(request, sessionKey);
		
	}
	
	/**
	 * 获取校验码的类型，如果当前请求不需要校验，则返回null
	 * 
	 * @param request
	 * @return
	 */
	private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
		ValidateCodeType result = null;
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
			Set<String> urls = urlMap.keySet();
			for (String url : urls) {
				if (antPathMatcher.match(url, request.getRequestURI())) {
					result = urlMap.get(url);
				}
			}
		}
		return result;
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
