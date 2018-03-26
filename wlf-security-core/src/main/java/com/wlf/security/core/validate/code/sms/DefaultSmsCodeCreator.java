package com.wlf.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

import com.wlf.security.core.validate.code.ValidateCode;

@Component("smsCodeCreator")
public class DefaultSmsCodeCreator implements SmsCodeCreator {

	@Override
	public ValidateCode createSmsCode(int length, int expireTime) {
		String code = RandomStringUtils.randomNumeric(length);
		ValidateCode validateCode = new ValidateCode(code, expireTime);
		return validateCode;
	}

}
