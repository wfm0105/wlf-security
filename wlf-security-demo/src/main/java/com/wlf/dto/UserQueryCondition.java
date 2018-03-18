package com.wlf.dto;

import java.io.Serializable;

public class UserQueryCondition implements Serializable{

	private static final long serialVersionUID = -7211959177226242605L;
	
	private String username;
	private int status;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
