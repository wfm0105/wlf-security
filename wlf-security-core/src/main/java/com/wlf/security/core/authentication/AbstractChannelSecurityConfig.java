package com.wlf.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.wlf.security.core.Constants;

public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

	@Autowired
	protected AuthenticationFailureHandler defaultAuthenticationFailureHandler;
	
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(Constants.LOGIN_CONTROLLER)
			.loginProcessingUrl(Constants.DEFAULT_FORM_AUTHENTICATION_URL)
			.successHandler(defaultAuthenticationSuccessHandler)
			.failureHandler(defaultAuthenticationFailureHandler);
	}
	
}