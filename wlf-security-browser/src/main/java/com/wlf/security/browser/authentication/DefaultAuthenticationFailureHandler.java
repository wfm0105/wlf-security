package com.wlf.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component("defaultAuthenticationFailureHandler")
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(DefaultAuthenticationFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		logger.info("登录失败！");
		
		Gson gson = new Gson();
		
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json:charset=utf-8");
		response.getWriter().write(gson.toJson(exception));
		
	}

}
