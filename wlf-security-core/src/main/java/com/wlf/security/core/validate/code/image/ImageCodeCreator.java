package com.wlf.security.core.validate.code.image;

import java.awt.Color;

/**
 * 
 * 验证码的创建接口
 * 
 */
import com.wlf.util.ImgValidateCodeUtil.Type;

public interface ImageCodeCreator {
	
	/**
	 * 
	 * @param width 验证码的宽度
	 * @param height 验证码的高度
	 * @param length 验证码的长度
	 * @param backgroundColor 验证码的背景色
	 * @param type 验证码的类型
	 * @return
	 * @throws Exception
	 */
	ImageCodeStringInfo createImageCode(int width, int height, int length, Color backgroundColor, Type type) throws Exception;
	
}
