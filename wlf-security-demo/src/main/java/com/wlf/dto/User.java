package com.wlf.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

public class User implements Serializable {

	private static final long serialVersionUID = 4093364932444483755L;
	
	public interface UserSimpleView {};
	public interface UserDetailView extends UserSimpleView {};
	
	private String userid;
	private String username;
	private String password;
	
	@JsonView(UserSimpleView.class)
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
