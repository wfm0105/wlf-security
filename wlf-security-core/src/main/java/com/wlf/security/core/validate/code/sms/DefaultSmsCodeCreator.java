package com.wlf.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wlf.security.core.validate.code.ValidateCode;

@Component("smsCodeCreator")
public class DefaultSmsCodeCreator implements SmsCodeCreator {

	Logger logger = LoggerFactory.getLogger(DefaultSmsCodeCreator.class);
	
	@Override
	public ValidateCode createSmsCode(int length, int expireTime) {
		String code = RandomStringUtils.randomNumeric(length);
		logger.info("验证码"+code);
		ValidateCode validateCode = new ValidateCode(code, expireTime);
		return validateCode;
	}

}
