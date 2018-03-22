package com.wlf.security.core.properties;

/**
 * 
 * web端配置项
 * 
 * @author wulinfeng
 *
 */
public class BrowserProperties {

	// 登录页面
	private String loginPage = Contants.DEFAULT_LOGIN_PAGE;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	
}
