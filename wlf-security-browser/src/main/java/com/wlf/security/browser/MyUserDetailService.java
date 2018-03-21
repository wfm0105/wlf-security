package com.wlf.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("验证用户信息，登录名："+username);
		// 读数据库获取用户和权限
		return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
