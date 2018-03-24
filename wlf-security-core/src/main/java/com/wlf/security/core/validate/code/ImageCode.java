package com.wlf.security.core.validate.code;

import java.time.LocalDateTime;

public class ImageCode {

	private String image;
	
	private String code;
	
	private LocalDateTime expireTime;

	public ImageCode(String image, String code, int expireIn) {
		this.image = image;
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public ImageCode(String image, String code, LocalDateTime expireTime) {
		this.image = image;
		this.code = code;
		this.expireTime = expireTime;
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
