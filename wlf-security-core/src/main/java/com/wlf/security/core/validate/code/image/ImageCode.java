package com.wlf.security.core.validate.code.image;

import java.time.LocalDateTime;

import com.wlf.security.core.validate.code.ValidateCode;

public class ImageCode extends ValidateCode {

	private String image;

	public ImageCode(String image, String code, int expireIn) {
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(String image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}
	
	public boolean isExpired() {
		return this.expireTime.compareTo(LocalDateTime.now()) < 0 ? true : false; 
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
	
}
