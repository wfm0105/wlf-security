package com.wlf.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * 配置项管理
 * 
 * @author wulinfeng
 *
 */
@ConfigurationProperties(prefix = "wlf.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();
	
	private ValidateCodeProperties validateCode = new ValidateCodeProperties();
	
	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public ValidateCodeProperties getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(ValidateCodeProperties validateCode) {
		this.validateCode = validateCode;
	}
	
}
