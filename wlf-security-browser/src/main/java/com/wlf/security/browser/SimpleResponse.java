package com.wlf.security.browser;

/**
 * 
 * 统一返回的封装
 * 
 * @author wulinfeng
 *
 */
public class SimpleResponse {

	private Object content;
	
	public SimpleResponse(Object content) {
		this.content = content;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
