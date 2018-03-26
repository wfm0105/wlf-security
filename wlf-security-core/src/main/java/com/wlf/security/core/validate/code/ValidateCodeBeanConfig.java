package com.wlf.security.core.validate.code;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wlf.security.core.validate.code.image.DefaultImageCodeCreator;
import com.wlf.security.core.validate.code.image.ImageCodeCreator;
import com.wlf.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.wlf.security.core.validate.code.sms.SmsCodeSender;

@Configuration
public class ValidateCodeBeanConfig {

	@Bean
	@ConditionalOnMissingBean(ImageCodeCreator.class)
	public ImageCodeCreator imageCodeCreator() {
		return new DefaultImageCodeCreator();
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
	
}
