package com.wlf.security.core.validate.code.image;

/**
 * 
 * 图形验证码中的文字信息
 * 
 * @author wulinfeng
 *
 */
public class ImageCodeStringInfo {

	private String code;
	private String base64Image;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
}
