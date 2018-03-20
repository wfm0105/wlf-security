package com.wlf.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;

public class User implements Serializable {

	private static final long serialVersionUID = 4093364932444483755L;
	
	public interface UserSimpleView {};
	public interface UserDetailView extends UserSimpleView {};
	
	@ApiModelProperty(value="用户id")
	private String userid;
	
	@ApiModelProperty(value="用户名")
	private String username;
	
	@NotBlank(message="密码不能为空！")
	@ApiModelProperty(value="密码")
	private String password;
	
	@ApiModelProperty(value="创建时间")
	private Date createDate;
	
	@ApiModelProperty(value="创建人")
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
