package com.wlf.security.core.validate.code.sms;

import com.wlf.security.core.validate.code.ValidateCode;

/**
 * 
 * 短信验证码生成器接口
 * 
 * @author wulinfeng
 *
 */
public interface SmsCodeCreator {

	ValidateCode createSmsCode(int length, int expireTime);
	
}
