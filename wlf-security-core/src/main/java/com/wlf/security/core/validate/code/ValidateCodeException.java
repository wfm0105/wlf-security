package com.wlf.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 
 * 验证码异常
 * 
 * @author wulinfeng
 *
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = 8224065476319357805L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
