package com.wlf.security.core.properties;

import java.awt.Color;

import com.wlf.security.core.Constants;
import com.wlf.security.core.common.StoreMethod;
import com.wlf.util.ImgValidateCodeUtil.Type;

public class ImageCodeProperties {
	
	// 图形验证码的拦截请求
	private String imageCodeUrl = Constants.DEFAULT_FORM_AUTHENTICATION_URL;
	
	// 图形验证码的存储方式
	private StoreMethod imageCodeStoreMethod = Constants.DEFAULT_IMAGE_CODE_STORE_METHOD;
	
	// 图形验证码的SESSION键值
	private String imageCodeSessionKey = Constants.DEFAULT_IMAGE_CODE_SESSION_KEY;
	
	// 图形验证码的键值前缀
	private String imageCodeKeyPrefix = Constants.DEFAULT_IMAGE_CODE_KEY_PREFIX;
	
	// 图形验证码的超时时间
	private int imageCodeExpire = Constants.DEFAULT_IMAGE_CODE_EXPIRE;

	// 图形验证码的传参名
	private String imageCodeParamName = Constants.DEFAULT_IMAGE_CODE_PARAM_NAME;
	
	// 图形验证码的宽度
	private int imageCodeWidth = Constants.DEFAULT_IMAGE_CODE_WIDTH;
	
	// 图形验证码的高度
	private int imageCodeHeight = Constants.DEFAULT_IMAGE_CODE_HEIGHT;
	
	// 图形验证码的长度
	private int imageCodeLength = Constants.DEFAULT_IMAGE_CODE_LENGTH;
	
	// 图形验证码的背景颜色
	private Color imageCodeBackgroundColor = Constants.DEFAULT_IMAGE_CODE_BACKGROUND_COLOR;
	
	// 图形验证码的类型
	private Type imageCodeType = Constants.DEFAULT_IMAGE_CODE_TYPE;
	
	// 图形验证码的超时时间参数名
	private String imageCodeExpireParamName = Constants.DEFAULT_IMAGE_CODE_EXPIRE_PARAM_NAME;
	
	// 图形验证码的宽度参数名
	private String imageCodeWidthParamName = Constants.DEFAULT_IMAGE_CODE_WIDTH_PARAM_NAME;
	
	// 图形验证码的高度参数名
	private String imageCodeHeightParamName = Constants.DEFAULT_IMAGE_CODE_HEIGHT_PARAM_NAME;
	
	// 图形验证码的长度参数名
	private String imageCodeLengthParamName = Constants.DEFAULT_IMAGE_CODE_LENGTH_PARAM_NAME;
	
	// 图形验证码的背景颜色参数名
	private String imageCodeBackgroundColorParamName = Constants.DEFAULT_IMAGE_CODE_BACKGROUND_COLOR_PARAM_NAME;
	
	// 图形验证码的类型参数名
	private String imageCodeTypeParamName = Constants.DEFAULT_IMAGE_CODE_TYPE_PARAM_NAME;
	
	public String getImageCodeUrl() {
		return imageCodeUrl;
	}

	public void setImageCodeUrl(String imageCodeUrl) {
		this.imageCodeUrl = imageCodeUrl;
	}
	
	public StoreMethod getImageCodeStoreMethod() {
		return imageCodeStoreMethod;
	}

	public void setImageCodeStoreMethod(StoreMethod imageCodeStoreMethod) {
		this.imageCodeStoreMethod = imageCodeStoreMethod;
	}

	public String getImageCodeSessionKey() {
		return imageCodeSessionKey;
	}

	public void setImageCodeSessionKey(String imageCodeSessionKey) {
		this.imageCodeSessionKey = imageCodeSessionKey;
	}

	public String getImageCodeKeyPrefix() {
		return imageCodeKeyPrefix;
	}

	public void setImageCodeKeyPrefix(String imageCodeKeyPrefix) {
		this.imageCodeKeyPrefix = imageCodeKeyPrefix;
	}

	public int getImageCodeExpire() {
		return imageCodeExpire;
	}

	public void setImageCodeExpire(int imageCodeExpire) {
		this.imageCodeExpire = imageCodeExpire;
	}

	public String getImageCodeParamName() {
		return imageCodeParamName;
	}

	public void setImageCodeParamName(String imageCodeParamName) {
		this.imageCodeParamName = imageCodeParamName;
	}

	public int getImageCodeWidth() {
		return imageCodeWidth;
	}

	public void setImageCodeWidth(int imageCodeWidth) {
		this.imageCodeWidth = imageCodeWidth;
	}

	public int getImageCodeHeight() {
		return imageCodeHeight;
	}

	public void setImageCodeHeight(int imageCodeHeight) {
		this.imageCodeHeight = imageCodeHeight;
	}

	public int getImageCodeLength() {
		return imageCodeLength;
	}

	public void setImageCodeLength(int imageCodeLength) {
		this.imageCodeLength = imageCodeLength;
	}

	public Color getImageCodeBackgroundColor() {
		return imageCodeBackgroundColor;
	}

	public void setImageCodeBackgroundColor(Color imageCodeBackgroundColor) {
		this.imageCodeBackgroundColor = imageCodeBackgroundColor;
	}

	public Type getImageCodeType() {
		return imageCodeType;
	}

	public void setImageCodeType(Type imageCodeType) {
		this.imageCodeType = imageCodeType;
	}

	public String getImageCodeExpireParamName() {
		return imageCodeExpireParamName;
	}

	public void setImageCodeExpireParamName(String imageCodeExpireParamName) {
		this.imageCodeExpireParamName = imageCodeExpireParamName;
	}

	public String getImageCodeWidthParamName() {
		return imageCodeWidthParamName;
	}

	public void setImageCodeWidthParamName(String imageCodeWidthParamName) {
		this.imageCodeWidthParamName = imageCodeWidthParamName;
	}

	public String getImageCodeHeightParamName() {
		return imageCodeHeightParamName;
	}

	public void setImageCodeHeightParamName(String imageCodeHeightParamName) {
		this.imageCodeHeightParamName = imageCodeHeightParamName;
	}

	public String getImageCodeLengthParamName() {
		return imageCodeLengthParamName;
	}

	public void setImageCodeLengthParamName(String imageCodeLengthParamName) {
		this.imageCodeLengthParamName = imageCodeLengthParamName;
	}

	public String getImageCodeBackgroundColorParamName() {
		return imageCodeBackgroundColorParamName;
	}

	public void setImageCodeBackgroundColorParamName(String imageCodeBackgroundColorParamName) {
		this.imageCodeBackgroundColorParamName = imageCodeBackgroundColorParamName;
	}

	public String getImageCodeTypeParamName() {
		return imageCodeTypeParamName;
	}

	public void setImageCodeTypeParamName(String imageCodeTypeParamName) {
		this.imageCodeTypeParamName = imageCodeTypeParamName;
	}
	
}
