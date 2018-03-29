package com.wlf.security.core.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wlf.security.core.validate.code.image.DefaultImageCodeCreator;
import com.wlf.security.core.validate.code.image.ImageCodeCreator;

/**
 * 
 * 主配置类
 * 1、启用SecurityProperties
 * 2、指定默认的加密方式
 * 
 * @author wulinfeng
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
	
	@Bean
	@ConditionalOnMissingBean(PasswordEncoder.class)
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
