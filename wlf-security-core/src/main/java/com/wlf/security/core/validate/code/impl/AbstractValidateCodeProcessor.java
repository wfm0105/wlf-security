package com.wlf.security.core.validate.code.impl;

import org.springframework.web.context.request.ServletWebRequest;

import com.wlf.security.core.validate.code.ValidateCodeProcessor;

public abstract class AbstractValidateCodeProcessor<T> implements ValidateCodeProcessor {
	
	@Override
	public String create(ServletWebRequest request) throws Exception {
		T validateCode = generate(request);
		if(validateCode == null)
			throw new RuntimeException("验证码生成失败imageCode=null");
		save(validateCode, request);
		String result = send(validateCode, request);
		return result;
	}

	public abstract T generate(ServletWebRequest request) throws Exception;
	
	public abstract void save(T code, ServletWebRequest request) throws Exception;
	
	public abstract String send(T code, ServletWebRequest request) throws Exception;
	
}
