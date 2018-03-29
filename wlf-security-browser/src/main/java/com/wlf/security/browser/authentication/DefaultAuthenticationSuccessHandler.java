package com.wlf.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.wlf.security.core.properties.LoginType;
import com.wlf.security.core.properties.SecurityProperties;

/**
 * 
 * 验证成功的默认处理
 * 
 * @author wulinfeng
 *
 */
@Component("defaultAuthenticationSuccessHandler")
public class DefaultAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(DefaultAuthenticationSuccessHandler.class);
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		logger.info("登录成功！");
		
		Gson gson = new Gson();
		
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(gson.toJson(authentication));
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
