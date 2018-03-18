package com.wlf.dto;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 4093364932444483755L;
	
	private String userid;
	private String username;
	private String password;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
