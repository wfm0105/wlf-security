package com.wlf.security.browser;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wlf.security.core.properties.SecurityProperties;

/**
 * 
 * 统一安全服务处理
 * 
 * @author wulinfeng
 *
 */
@RestController
public class BrowserSecurityController {
	
	private Logger logger = LoggerFactory.getLogger(BrowserSecurityController.class);

	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * 
	 * 需要身份认证时执行
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		Optional.ofNullable(savedRequest)
				   .ifPresent(r->{
					   String target = savedRequest.getRedirectUrl();
					   logger.info("引发授权的请求："+target);
					   if(StringUtils.endsWithIgnoreCase(target, ".html")) {
						   try {
							String loginPage = securityProperties.getBrowser().getLoginPage();
							redirectStrategy.sendRedirect(request, response, loginPage);
						} catch (IOException e) {
							logger.info("授权页跳转失败："+target);
							e.printStackTrace();
						}
					   }
				   });
			
		return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页！");
	}
	
}
