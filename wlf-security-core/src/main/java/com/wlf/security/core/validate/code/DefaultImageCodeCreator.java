package com.wlf.security.core.validate.code;

import java.awt.Color;

import org.springframework.stereotype.Component;

import com.wlf.util.ImgValidateCodeUtil;
import com.wlf.util.ImgValidateCodeUtil.Type;

@Component("defaultImageCodeCreator")
public class DefaultImageCodeCreator implements ImageCodeCreator {

	@Override
	public ImageCodeStringInfo createImageCode(int width, int height, int length, Color backgroundColor, Type type) throws Exception {
		ImageCodeStringInfo imageCodeStringInfo = new ImageCodeStringInfo();
		ImgValidateCodeUtil imgValidateCodeUtil = new ImgValidateCodeUtil();
		imgValidateCodeUtil.setWidth(width);
		imgValidateCodeUtil.setHeight(height);
		imgValidateCodeUtil.setLength(length);
		imgValidateCodeUtil.setBackgroudColor(backgroundColor);
		imgValidateCodeUtil.setType(type);
		
		String[] validateInfoArray=imgValidateCodeUtil.createValiteCode();
		String code=validateInfoArray[0];			//验证码的随机数字
	    String base64Image=validateInfoArray[1];	//验证码图的base64字符串	
		imageCodeStringInfo.setCode(code);
		imageCodeStringInfo.setBase64Image(base64Image);
		return imageCodeStringInfo;
	}
	
}
