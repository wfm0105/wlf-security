package com.wlf.exception;

/**
 * spring框架全局异常类
 * 
 * @author huangtf
 * @date 2016/7/21
 *
 */
public class SysException extends Exception{
 
	private static final long serialVersionUID = -6353402410109800530L;
	
	private String errorCode; 	 // 错误码
	private String errorMsg;  	 // 错误消息

	public SysException(String code, String message){
		super(message);
		errorCode=code;
		errorMsg=message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
 
}
