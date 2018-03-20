package com.wlf.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class UserQueryCondition implements Serializable{

	private static final long serialVersionUID = -7211959177226242605L;
	
	@NotNull
	@ApiModelProperty(value="用户名")
	private String username;
	@ApiModelProperty(value="状态")
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
