package com.wlf.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 默认短信验证码发送方式，这里并没有实现真正的短信发送，实际需要接入具体的短信平台
 * 
 * @author wulinfeng
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSmsCodeSender.class);
	
	@Override
	public void send(String mobile, String code) {
		logger.info("发送短信验证码");
	}

}
