package com.wlf.security.core.validate.code.impl;

import org.springframework.web.context.request.ServletWebRequest;

import com.wlf.security.core.validate.code.ValidateCodeProcessor;

/**
 * 
 * 验证码的模板
 * 1、生成验证码
 * 2、存储验证码
 * 3、返回验证码到前端
 * 
 * @author wulinfeng
 *
 * @param <T>
 */
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
