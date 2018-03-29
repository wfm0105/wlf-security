package com.wlf.security.core.validate.code.sms;

/**
 * 
 * 短信验证码发发送接口
 * 
 * @author wulinfeng
 *
 */
public interface SmsCodeSender {

	void send(String mobile, String code);
	
}
