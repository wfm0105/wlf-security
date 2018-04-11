package com.wlf.security.core.validate.code.image;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.util.FieldUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.wlf.security.core.Constants;
import com.wlf.security.core.properties.SecurityProperties;
import com.wlf.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import com.xyt.util.ImgValidateCodeUtil.Type;

/**
 * 
 * 图形验证码处理器，实现了验证码处理模板的抽象方法AbstractValidateCodeProcessor
 * 1、生成验证码
 * 2、存储验证码
 * 3、将验证码返回前端
 * 
 * @author wulinfeng
 *
 */
@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode>{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ImageCodeCreator imageCodeCreator;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Override
	public ImageCode generate(ServletWebRequest request) throws Exception {
		
		// 验证码的宽度
		int imageCodeWidth = getImageCodeWidth(request.getRequest());
		
		// 验证码的高度
		int imageCodeHeight = getImageCodeHeight(request.getRequest());
		
		// 验证码的长度
		int imageCodeLength = getImageCodeLength(request.getRequest());
		
		// 验证码的背景颜色
		Color imageCodeBackgroundColor = getImageCodeBackgroundColor(request.getRequest());
			
		// 验证码的类型
		Type imageCodeType = getImageCodeType(request.getRequest());
		
		return createImageCode(imageCodeCreator.createImageCode(imageCodeWidth, imageCodeHeight, imageCodeLength, imageCodeBackgroundColor, imageCodeType));
	}

	@Override
	public void save(ImageCode code, ServletWebRequest request) throws Exception {
		
		switch(securityProperties.getValidateCode().getImageCode().getImageCodeStoreMethod()) {
		case SESSION : {
			sessionStrategy.setAttribute(
					request, 
					securityProperties.getValidateCode().getImageCode().getImageCodeSessionKey(), 
					code);
			
			break;
		}
		case REDIS : {
			redisTemplate.opsForValue().set(
					securityProperties.getValidateCode().getImageCode().getImageCodeKeyPrefix()+code.getCode(), 
					"1", 
					securityProperties.getValidateCode().getImageCode().getImageCodeExpire(), 
					TimeUnit.SECONDS);
			}
		}
		
	}

	@Override
	public String send(ImageCode code, ServletWebRequest request) throws Exception {
		request.getResponse().setContentType("image/jpeg");
		request.getResponse().setDateHeader("expries", -1);
		request.getResponse().setHeader("Cache-Control", "no-cache");
		request.getResponse().setHeader("Pragma", "no-cache");
		
		return code.getImage();
	}
	
	private int getImageCodeWidth(HttpServletRequest request)  throws ServletRequestBindingException {
		int imageCodeWidth = getImageCodeWidthFromRequest(request);
		
		if(imageCodeWidth == 0)
			imageCodeWidth = getImageCodeWidthFromConfig();
		
		return imageCodeWidth;
	}
	
	private int getImageCodeHeight(HttpServletRequest request)  throws ServletRequestBindingException {
		int imageCodeHeight = getImageCodeHeightFromRequest(request);
		
		if(imageCodeHeight == 0)
			imageCodeHeight = getImageCodeHeightFromConfig();
		
		return imageCodeHeight;
	}
	
	private int getImageCodeLength(HttpServletRequest request)  throws ServletRequestBindingException {
		int imageCodeLength = getImageCodeLengthFromRequest(request);
		
		if(imageCodeLength == 0)
			imageCodeLength = getImageCodeLengthFromConfig();
		
		return imageCodeLength;
	}
	
	private Color getImageCodeBackgroundColor(HttpServletRequest request)  throws ServletRequestBindingException, IllegalArgumentException, IllegalAccessException {
		String imageCodeBackgroundColor = getImageCodeBackgroundColorFromRequest(request);
		
		Color imageCodeBackgroundColorValue;
		
		if(!StringUtils.isBlank(imageCodeBackgroundColor)) {
			Field colorField = FieldUtils.getField(Color.class, imageCodeBackgroundColor);
			colorField.setAccessible(true);
			imageCodeBackgroundColorValue = (Color) colorField.get(null);
			if(imageCodeBackgroundColorValue == null) {
				imageCodeBackgroundColorValue = getImageCodeBackgroundColorFromConfig();
			}
		} else {
			imageCodeBackgroundColorValue = getImageCodeBackgroundColorFromConfig();
		}
		
		return imageCodeBackgroundColorValue;
	}
	
	private Type getImageCodeType(HttpServletRequest request)  throws ServletRequestBindingException, IllegalArgumentException, IllegalAccessException {
		String imageCodeType = getImageCodeTypeFromRequest(request);
		
		Type imageCodeTypeValue;
		
		if(!StringUtils.isBlank(imageCodeType)) {
			imageCodeTypeValue = Type.valueOf(imageCodeType);
			if(imageCodeTypeValue == null) {
				imageCodeTypeValue = getImageCodeTypeFromConfig();
			}
		} else {
			imageCodeTypeValue = getImageCodeTypeFromConfig();
		}
		
		return imageCodeTypeValue;
	}
	
	private int getImageCodeWidthFromRequest(HttpServletRequest request) throws ServletRequestBindingException {
		return Optional.ofNullable(
				ServletRequestUtils.getIntParameter(
					request, 
					Optional.ofNullable(securityProperties)
					.map(properties->{
						return properties.getValidateCode().getImageCode().getImageCodeWidthParamName();
					})
					.orElseGet(()->{
						return Constants.DEFAULT_IMAGE_CODE_WIDTH_PARAM_NAME;
					})
				)
		).orElse(0);
	}
	
	private int getImageCodeHeightFromRequest(HttpServletRequest request) throws ServletRequestBindingException {
		return Optional.ofNullable(
				ServletRequestUtils.getIntParameter(
					request, 
					Optional.ofNullable(securityProperties)
					.map(properties->{
						return properties.getValidateCode().getImageCode().getImageCodeHeightParamName();
					})
					.orElseGet(()->{
						return Constants.DEFAULT_IMAGE_CODE_HEIGHT_PARAM_NAME;
					})
				)
		).orElse(0);
	}
	
	private int getImageCodeLengthFromRequest(HttpServletRequest request) throws ServletRequestBindingException {
		return Optional.ofNullable(
				ServletRequestUtils.getIntParameter(
					request, 
					Optional.ofNullable(securityProperties)
					.map(properties->{
						return properties.getValidateCode().getImageCode().getImageCodeLengthParamName();
					})
					.orElseGet(()->{
						return Constants.DEFAULT_IMAGE_CODE_LENGTH_PARAM_NAME;
					})
				)
		).orElse(0);
	}
	
	private String getImageCodeBackgroundColorFromRequest(HttpServletRequest request) throws ServletRequestBindingException {
		return Optional.ofNullable(
				ServletRequestUtils.getStringParameter(
					request, 
					Optional.ofNullable(securityProperties)
					.map(properties->{
						return properties.getValidateCode().getImageCode().getImageCodeBackgroundColorParamName();
					})
					.orElseGet(()->{
						return Constants.DEFAULT_IMAGE_CODE_BACKGROUND_COLOR_PARAM_NAME;
					})
				)
		).orElse("");
	}
	
	private String getImageCodeTypeFromRequest(HttpServletRequest request) throws ServletRequestBindingException {
		return Optional.ofNullable(
				ServletRequestUtils.getStringParameter(
					request, 
					Optional.ofNullable(securityProperties)
					.map(properties->{
						return properties.getValidateCode().getImageCode().getImageCodeTypeParamName();
					})
					.orElseGet(()->{
						return Constants.DEFAULT_IMAGE_CODE_TYPE_PARAM_NAME;
					})
				)
		).orElse("");
	}
	
	private int getImageCodeWidthFromConfig() {
		return Optional.ofNullable(securityProperties)
				.map(properties->{
					return properties.getValidateCode().getImageCode().getImageCodeWidth();
				})
				.orElseGet(()->{
					return Constants.DEFAULT_IMAGE_CODE_WIDTH;
				});
	}
	
	private int getImageCodeHeightFromConfig() {
		return Optional.ofNullable(securityProperties)
				.map(properties->{
					return properties.getValidateCode().getImageCode().getImageCodeHeight();
				})
				.orElseGet(()->{
					return Constants.DEFAULT_IMAGE_CODE_HEIGHT;
				});
	}
	
	private int getImageCodeLengthFromConfig() {
		return Optional.ofNullable(securityProperties)
				.map(properties->{
					return properties.getValidateCode().getImageCode().getImageCodeLength();
				})
				.orElseGet(()->{
					return Constants.DEFAULT_IMAGE_CODE_LENGTH;
				});
	}
	
	private Color getImageCodeBackgroundColorFromConfig() {
		return Optional.ofNullable(securityProperties)
				.map(properties->{
					return properties.getValidateCode().getImageCode().getImageCodeBackgroundColor();
				})
				.orElseGet(()->{
					return Constants.DEFAULT_IMAGE_CODE_BACKGROUND_COLOR;
				});
	}
	
	private Type getImageCodeTypeFromConfig() {
		return Optional.ofNullable(securityProperties)
				.map(properties->{
					return properties.getValidateCode().getImageCode().getImageCodeType();
				})
				.orElseGet(()->{
					return Constants.DEFAULT_IMAGE_CODE_TYPE;
				});
	}
	
	private ImageCode createImageCode(ImageCodeStringInfo imageCodeStringInfo) {
		return new ImageCode(imageCodeStringInfo.getBase64Image(), imageCodeStringInfo.getCode(), securityProperties.getValidateCode().getImageCode().getImageCodeExpire());
	}

}
