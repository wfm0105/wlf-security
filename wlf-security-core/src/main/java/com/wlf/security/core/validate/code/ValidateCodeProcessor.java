package com.wlf.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {

	String create(ServletWebRequest request) throws Exception;
	
}
