package com.wlf.security.core.validate.code;

import java.awt.Color;

import org.springframework.web.context.request.ServletWebRequest;

import com.wlf.util.ImgValidateCodeUtil.Type;

public interface ImageCodeCreator {

	ImageCodeStringInfo createImageCode(int width, int height, int length, Color backgroundColor, Type type) throws Exception;
	
}
