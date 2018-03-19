package com.wlf.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

public class User implements Serializable {

	private static final long serialVersionUID = 4093364932444483755L;
	
	public interface UserSimpleView {};
	public interface UserDetailView extends UserSimpleView {};
	
	private String userid;
	
	private String username;
	
	@NotBlank(message="密码不能为空！")
	private String password;
	
	private Date createDate;
	
	private int createBy;
	
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

	@JsonView(UserSimpleView.class)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonView(UserSimpleView.class)
	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	
}
