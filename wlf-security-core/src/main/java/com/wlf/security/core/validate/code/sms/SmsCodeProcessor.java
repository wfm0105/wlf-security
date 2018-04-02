package com.wlf.security.core.validate.code.sms;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.wlf.security.core.Constants;
import com.wlf.security.core.properties.SecurityProperties;
import com.wlf.security.core.validate.code.ValidateCode;
import com.wlf.security.core.validate.code.impl.AbstractValidateCodeProcessor;

@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode>{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private SmsCodeCreator smsCodeCreator;
	
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Override
	public ValidateCode generate(ServletWebRequest request) throws Exception {
		int length = getSmsCodeLength(request.getRequest());
		ValidateCode validateCode = smsCodeCreator.createSmsCode(length, securityProperties.getValidateCode().getSmsCode().getSmsCodeExpire());
		return validateCode;
	}

	@Override
	public void save(ValidateCode code, ServletWebRequest request) throws Exception {
		switch(securityProperties.getValidateCode().getSmsCode().getSmsCodeStoreMethod()) {
		case SESSION : {
			sessionStrategy.setAttribute(
					request, 
					securityProperties.getValidateCode().getSmsCode().getSmsCodeSessionKey(), 
					code);
			
			break;
		}
		case REDIS : {
				redisTemplate.opsForValue().set(
						securityProperties.getValidateCode().getSmsCode().getSmsCodeKeyPrefix()+code.getCode(), 
						"1", 
						securityProperties.getValidateCode().getSmsCode().getSmsCodeExpire(), 
						TimeUnit.SECONDS);
			}
		}
	}

	@Override
	public String send(ValidateCode code, ServletWebRequest request) throws Exception {
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), securityProperties.getValidateCode().getSmsCode().getSmsCodePhoneParamName());
		smsCodeSender.send(mobile, code.getCode());
		return code.getCode();
	}
	
	private int getSmsCodeLength(HttpServletRequest request)  throws ServletRequestBindingException {
		int smsCodeLength = getSmsCodeLengthFromRequest(request);
		
		if(smsCodeLength == 0)
			smsCodeLength = getSmsCodeLengthFromConfig();
		
		return smsCodeLength;
	}
	
	private int getSmsCodeLengthFromRequest(HttpServletRequest request) throws ServletRequestBindingException {
		return Optional.ofNullable(
				ServletRequestUtils.getIntParameter(
					request, 
					Optional.ofNullable(securityProperties)
					.map(properties->{
						return properties.getValidateCode().getSmsCode().getSmsCodeLengthParamName();
					})
					.orElseGet(()->{
						return Constants.DEFAULT_SMS_CODE_LENGTH_PARAM_NAME;
					})
				)
		).orElse(0);
	}
	
	private int getSmsCodeLengthFromConfig() {
		return Optional.ofNullable(securityProperties)
				.map(properties->{
					return properties.getValidateCode().getSmsCode().getSmsCodeLength();
				})
				.orElseGet(()->{
					return Constants.DEFAULT_SMS_CODE_LENGTH;
				});
	}

}
