package com.wlf.security.core.properties;

import com.wlf.security.core.common.StoreMethod;

public class SmsCodeProperties {

	// 短信验证码的拦截请求
	private String smsCodeUrl = Constants.DEFAULT_MOBLIE_AUTHENTICATION_URL;
	
	// 短信验证码的存储方式
	private StoreMethod smsCodeStoreMethod = Constants.DEFAULT_SMS_CODE_STORE_METHOD;

	// 短信验证码的SESSION键值
	private String smsCodeSessionKey = Constants.DEFAULT_SMS_CODE_SESSION_KEY;
	
	// 短信验证码的SESSION键值
	private String smsCodeKeyPrefix = Constants.DEFAULT_SMS_CODE_KEY_PREFIX;
	
	// 短信验证码的长度
	private int smsCodeLength = Constants.DEFAULT_SMS_CODE_LENGTH;
	
	// 短信验证码的长度
	private String smsCodeLengthParamName = Constants.DEFAULT_SMS_CODE_LENGTH_PARAM_NAME;
	
	// 短信验证码的超时时间
	private int smsCodeExpire = Constants.DEFAULT_SMS_CODE_EXPIRE;
	
	// 短信验证码的传参名
	private String smsCodeParamName = Constants.DEFAULT_SMS_CODE_PARAM_NAME;
	
	public String getSmsCodeUrl() {
		return smsCodeUrl;
	}

	public void setSmsCodeUrl(String smsCodeUrl) {
		this.smsCodeUrl = smsCodeUrl;
	}

	public StoreMethod getSmsCodeStoreMethod() {
		return smsCodeStoreMethod;
	}

	public void setSmsCodeStoreMethod(StoreMethod smsCodeStoreMethod) {
		this.smsCodeStoreMethod = smsCodeStoreMethod;
	}

	public String getSmsCodeSessionKey() {
		return smsCodeSessionKey;
	}

	public void setSmsCodeSessionKey(String smsCodeSessionKey) {
		this.smsCodeSessionKey = smsCodeSessionKey;
	}

	public String getSmsCodeKeyPrefix() {
		return smsCodeKeyPrefix;
	}

	public void setSmsCodeKeyPrefix(String smsCodeKeyPrefix) {
		this.smsCodeKeyPrefix = smsCodeKeyPrefix;
	}

	public int getSmsCodeLength() {
		return smsCodeLength;
	}

	public void setSmsCodeLength(int smsCodeLength) {
		this.smsCodeLength = smsCodeLength;
	}

	public String getSmsCodeLengthParamName() {
		return smsCodeLengthParamName;
	}

	public void setSmsCodeLengthParamName(String smsCodeLengthParamName) {
		this.smsCodeLengthParamName = smsCodeLengthParamName;
	}

	public int getSmsCodeExpire() {
		return smsCodeExpire;
	}

	public void setSmsCodeExpire(int smsCodeExpire) {
		this.smsCodeExpire = smsCodeExpire;
	}

	public String getSmsCodeParamName() {
		return smsCodeParamName;
	}

	public void setSmsCodeParamName(String smsCodeParamName) {
		this.smsCodeParamName = smsCodeParamName;
	}
	
}
