package com.wlf.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 
 * 验证码处理器接口，通过实现该接口定义相应的模板
 * 
 * @author wulinfeng
 *
 */
public interface ValidateCodeProcessor {

	String create(ServletWebRequest request) throws Exception;
	
}
