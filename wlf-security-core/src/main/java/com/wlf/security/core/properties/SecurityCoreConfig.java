package com.wlf.security.core.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wlf.security.core.validate.code.DefaultImageCodeCreator;
import com.wlf.security.core.validate.code.ImageCodeCreator;

/**
 * 
 * 启用SecurityProperties
 * 
 * @author wulinfeng
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@ConditionalOnMissingBean(name = "defaultImageCodeCreator")
	public ImageCodeCreator imageCodeCreator() {
		return new DefaultImageCodeCreator();
	}
	
}
