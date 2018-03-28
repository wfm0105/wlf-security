package com.wlf.security.core.properties;

import java.awt.Color;

import com.wlf.security.core.common.StoreMethod;
import com.wlf.util.ImgValidateCodeUtil.Type;

/**
 * 
 * 常量
 * 
 * @author Administrator
 *
 */
public class Constants {

	public static final String DEFAULT_LOGIN_PAGE = "/defaultLogin.html";

	public static final String DEFAULT_FAILURE_PAGE = "/defaultFailure.html";
	
	public static final StoreMethod DEFAULT_IMAGE_CODE_STORE_METHOD = StoreMethod.SESSION;
	
	public static final String DEFAULT_IMAGE_CODE_SESSION_KEY = "/default_image_code_session_key";
	
	public static final String DEFAULT_IMAGE_CODE_KEY_PREFIX = "/default_image_code_key_";

	public static final int DEFAULT_IMAGE_CODE_EXPIRE = 60;

	public static final String DEFAULT_FORM_AUTHENTICATION_URL = "/authentication/form";
	
	public static final String DEFAULT_MOBLIE_AUTHENTICATION_URL = "/authentication/mobile";
	
	public static final String DEFAULT_IMAGE_CODE_PARAM_NAME = "imageCode";

	public static final int DEFAULT_IMAGE_CODE_WIDTH = 100;

	public static final int DEFAULT_IMAGE_CODE_HEIGHT = 30;

	public static final int DEFAULT_IMAGE_CODE_LENGTH = 4;

	public static final Color DEFAULT_IMAGE_CODE_BACKGROUND_COLOR = Color.WHITE;

	public static final Type DEFAULT_IMAGE_CODE_TYPE = Type.ENGLISH_CHARACTER_NUMBER;

	public static final String DEFAULT_IMAGE_CODE_EXPIRE_PARAM_NAME = "imageCodeExpire";

	public static final String DEFAULT_IMAGE_CODE_WIDTH_PARAM_NAME = "imageCodeWidth";

	public static final String DEFAULT_IMAGE_CODE_HEIGHT_PARAM_NAME = "imageCodeHeight";

	public static final String DEFAULT_IMAGE_CODE_LENGTH_PARAM_NAME = "imageCodeLength";

	public static final String DEFAULT_IMAGE_CODE_BACKGROUND_COLOR_PARAM_NAME = "imageCodeBackgroundColor";

	public static final String DEFAULT_IMAGE_CODE_TYPE_PARAM_NAME = "imageCodeType";

	public static final String DEFAULT_REMOBER_ME_NAME = "remember-me";
	
	public static final StoreMethod DEFAULT_SMS_CODE_STORE_METHOD = StoreMethod.SESSION;
	
	public static final String DEFAULT_SMS_CODE_SESSION_KEY = "/default_sms_code_session_key";
	
	public static final String DEFAULT_SMS_CODE_KEY_PREFIX = "/default_sms_code_key_";

	public static final int DEFAULT_SMS_CODE_LENGTH = 6;
	
	public static final String DEFAULT_SMS_CODE_LENGTH_PARAM_NAME = "smsCodeLength";
	
	public static final int DEFAULT_SMS_CODE_EXPIRE = 60;
	
	public static final String DEFAULT_SMS_CODE_PHONE_PARAM_NAME = "mobile";
	
	public static final String DEFAULT_SMS_CODE_PARAM_NAME = "smsCode";
	
	public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "image";
	
	public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "sms";
	
}
