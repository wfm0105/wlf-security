package com.wlf.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wlf.security.browser.authentication.DefaultAuthenticationFailureHandler;
import com.wlf.security.core.properties.Constants;
import com.wlf.security.core.properties.SecurityProperties;
import com.wlf.security.core.validate.code.ValidateCodeFilter;
import com.wlf.security.core.validate.code.ImageCodeCreator;
import com.wlf.security.core.validate.code.DefaultImageCodeCreator;

/**
 * 
 * web端安全配置
 * 
 * @author wulinfeng
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String LOGIN_CONTROLLER = "/authentication/require"; 
	
	private static final String IMAGE_CODE_CONTROLLER = "/code/image"; 
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

	@Autowired
	private DefaultAuthenticationFailureHandler defaultAuthenticationFailureHandler;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		
		validateCodeFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.setRedisTemplate(redisTemplate);
		validateCodeFilter.afterPropertiesSet();
		
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			 .formLogin()
			 .loginPage(LOGIN_CONTROLLER)
			 .loginProcessingUrl(Constants.DEFAULT_FORM_AUTHENTICATION_URL)
			 .successHandler(defaultAuthenticationSuccessHandler)
			 .failureHandler(defaultAuthenticationFailureHandler)
			 .and()
			 .authorizeRequests()
			 .antMatchers(
					 LOGIN_CONTROLLER,
					 securityProperties.getBrowser().getLoginPage(),
					 IMAGE_CODE_CONTROLLER).permitAll()
			 .anyRequest()
			 .authenticated()
			 .and()
			 .csrf().disable();
		
	}
	
}
