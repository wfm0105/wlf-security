package com.wlf.security.core.authentication.mobile;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails userDetails = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());
		Optional.ofNullable(userDetails)
				.orElseThrow(()->{
					return new InternalAuthenticationServiceException("无法获取用户信息！");
				});
		
		SmsCodeAuthenticationToken smsCodeAuthenticationToken = new SmsCodeAuthenticationToken(userDetails.getUsername(), userDetails.getAuthorities());
		smsCodeAuthenticationToken.setDetails(authentication.getDetails());
		return smsCodeAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (SmsCodeAuthenticationToken.class.isAssignableFrom(authentication));
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
}
