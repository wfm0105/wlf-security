package com.wlf.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSmsCodeSender implements SmsCodeSender {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSmsCodeSender.class);
	
	@Override
	public void send(String mobile, String code) {
		logger.info("发送短信验证码");
	}

}
