package com.wlf.security.core.validate.code;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.util.FieldUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.wlf.security.core.Constants;
import com.wlf.security.core.properties.SecurityProperties;
import com.wlf.security.core.validate.code.image.ImageCode;
import com.wlf.security.core.validate.code.image.ImageCodeCreator;
import com.wlf.security.core.validate.code.image.ImageCodeStringInfo;
import com.wlf.security.core.validate.code.sms.SmsCodeCreator;
import com.wlf.security.core.validate.code.sms.SmsCodeSender;
import com.xyt.util.ImgValidateCodeUtil.Type;

/**
 * 
 * 验证码生成controller
 * 
 * @author wulinfeng
 *
 */
@RestController
@RequestMapping("/code")
public class ValidateCodeController {

	private Logger logger = LoggerFactory.getLogger(ValidateCodeController.class);
	
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	
	@GetMapping("/{type}")
	public String createCode(
			@PathVariable("type") String type,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
		
	}
	
}
