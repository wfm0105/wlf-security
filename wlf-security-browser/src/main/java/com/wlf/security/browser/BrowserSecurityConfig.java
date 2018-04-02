package com.wlf.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.wlf.security.browser.authentication.DefaultAuthenticationFailureHandler;
import com.wlf.security.core.Constants;
import com.wlf.security.core.authentication.AbstractChannelSecurityConfig;
import com.wlf.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.wlf.security.core.authentication.mobile.SmsCodeFilter;
import com.wlf.security.core.properties.SecurityProperties;
import com.wlf.security.core.validate.code.ValidateCodeFilter;
import com.wlf.security.core.validate.code.ValidateCodeSecurityConfig;
import com.wlf.security.core.validate.code.image.DefaultImageCodeCreator;
import com.wlf.security.core.validate.code.image.ImageCodeCreator;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 
 * web端安全配置
 * 
 * @author wulinfeng
 *
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService defaultUserDetailService;
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl(); 
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		jdbcTokenRepositoryImpl.setCreateTableOnStartup(securityProperties.getBrowser().isCreateTable());
		return jdbcTokenRepositoryImpl;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
//		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//		
//		validateCodeFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);
//		validateCodeFilter.setSecurityProperties(securityProperties);
//		validateCodeFilter.setRedisTemplate(redisTemplate);
//		validateCodeFilter.afterPropertiesSet();
		
//		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
//		
//		smsCodeFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);
//		smsCodeFilter.setSecurityProperties(securityProperties);
//		smsCodeFilter.setRedisTemplate(redisTemplate);
//		smsCodeFilter.afterPropertiesSet();
		
		applyPasswordAuthenticationConfig(http);
		
		http.apply(smsCodeAuthenticationSecurityConfig)
			.and()
			//.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.apply(validateCodeSecurityConfig)
			.and()
			 //.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			 //.formLogin()
				 //.loginPage(LOGIN_CONTROLLER)
				 //.loginProcessingUrl(Constants.DEFAULT_FORM_AUTHENTICATION_URL)
				 //.successHandler(defaultAuthenticationSuccessHandler)
				 //.failureHandler(defaultAuthenticationFailureHandler)
				 //.and()
			 .rememberMe()
				 .tokenRepository(persistentTokenRepository())
				 .tokenValiditySeconds(securityProperties.getBrowser().getRemberMeSeconds())
				 .userDetailsService(defaultUserDetailService)
				 .rememberMeParameter(securityProperties.getBrowser().getRemberMeName())
				 .rememberMeCookieName(securityProperties.getBrowser().getRemberMeName())
				 .and()
			 .authorizeRequests()
				 .antMatchers(
						 Constants.LOGIN_CONTROLLER,
						 securityProperties.getBrowser().getLoginPage(),
						 Constants.IMAGE_CODE_CONTROLLER,
						 Constants.SMS_CODE_CONTROLLER).permitAll()
				 .anyRequest()
				 .authenticated()
				 .and()
			 .csrf().disable();
//			 .apply(smsCodeAuthenticationSecurityConfig);
		
	}
	
}
