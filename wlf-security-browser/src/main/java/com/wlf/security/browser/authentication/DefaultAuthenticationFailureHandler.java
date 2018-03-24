package com.wlf.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.wlf.security.browser.SimpleResponse;
import com.wlf.security.core.properties.LoginType;
import com.wlf.security.core.properties.SecurityProperties;

@Component("defaultAuthenticationFailureHandler")
public class DefaultAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(DefaultAuthenticationFailureHandler.class);
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		logger.info("登录失败！");
		
		Gson gson = new Gson();
		
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(gson.toJson(new SimpleResponse(exception.getMessage())));
		} else {
			setDefaultFailureUrl(securityProperties.getBrowser().getFailurePage());
			super.onAuthenticationFailure(request, response, exception);
		}
		
	}

}
