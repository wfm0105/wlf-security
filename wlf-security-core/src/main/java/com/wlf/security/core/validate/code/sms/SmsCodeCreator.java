package com.wlf.security.core.validate.code.sms;

import com.wlf.security.core.validate.code.ValidateCode;

public interface SmsCodeCreator {

	ValidateCode createSmsCode(int length, int expireTime);
	
}
