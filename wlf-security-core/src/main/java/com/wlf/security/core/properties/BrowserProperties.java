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

	// 错误页面，返回类型为REDIRECT时才起作用
	private String failurePage = Contants.DEFAULT_FAILURE_PAGE;
	
	// 返回类型
	private LoginType loginType = LoginType.JSON;
	
	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	
	public String getFailurePage() {
		return failurePage;
	}

	public void setFailurePage(String failurePage) {
		this.failurePage = failurePage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}
	
}
