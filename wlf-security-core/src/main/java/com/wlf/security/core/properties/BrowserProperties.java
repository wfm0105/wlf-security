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
	private String loginPage = Constants.DEFAULT_LOGIN_PAGE;

	// 错误页面，返回类型为REDIRECT时才起作用
	private String failurePage = Constants.DEFAULT_FAILURE_PAGE;
	
	// 返回类型
	private LoginType loginType = LoginType.JSON;
	
	// 是否创建相应的表
	private boolean createTable = true;
	
	// 记住我的时间
	private int remberMeSeconds = 60 * 60;
	
	// 记住我的请求参数名，cookie键名
	private String remberMeName = Constants.DEFAULT_REMOBER_ME_NAME;
	
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

	public boolean isCreateTable() {
		return createTable;
	}

	public void setCreateTable(boolean createTable) {
		this.createTable = createTable;
	}

	public int getRemberMeSeconds() {
		return remberMeSeconds;
	}

	public void setRemberMeSeconds(int remberMeSeconds) {
		this.remberMeSeconds = remberMeSeconds;
	}

	public String getRemberMeName() {
		return remberMeName;
	}

	public void setRemberMeName(String remberMeName) {
		this.remberMeName = remberMeName;
	}
	
}
