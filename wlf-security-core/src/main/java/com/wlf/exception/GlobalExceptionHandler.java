package com.wlf.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/**
 * 
 * 全局异常处理
 * 
 * @author wulinfeng
 * @date 2017-11-04
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private final static  Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = SysException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> bussinessErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception exception) throws Exception {
		logger.error("bussinessErrorHandler.exception="+exception.getMessage(),exception);
		return defaultHandler(exception);
    }
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Map<String,String> validErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception exception) throws Exception {
		logger.error("validErrorHandler.exception="+exception.getMessage(),exception);
		Map<String, String> result = new HashMap<String, String>();
		StringBuffer message = new StringBuffer(); 
		MethodArgumentNotValidException notValidexception = (MethodArgumentNotValidException) exception;
		notValidexception.getBindingResult()
						 .getFieldErrors()
						 .stream()
						 .forEach(error->{
							 message.append(error.getDefaultMessage());
						 });
		result.put("message", message.toString());
		return result;
	}
	
	@ExceptionHandler(value = {ConversionNotSupportedException.class,HttpMessageNotWritableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> conversionAndNotWritableErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception exception) throws Exception {
		logger.error("conversionAndNotWritableErrorHandler.exception="+exception.getMessage(),exception);
		return defaultHandler(exception);
	}
	
	@ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Map<String,String> mediaTypeNotSupportedErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception exception) throws Exception {
		logger.error("mediaTypeNotSupportedErrorHandler.exception="+exception.getMessage(),exception);
		return defaultHandler(exception);
	}
	
	@ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String,String> mediaTypeNotAcceptableErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception exception) throws Exception {
		logger.error("mediaTypeNotAcceptableErrorHandler.exception="+exception.getMessage(),exception);
		return defaultHandler(exception);
	}
	
	@ExceptionHandler(value = {NoSuchRequestHandlingMethodException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> noSuchRequestHandlingMethodErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception exception) throws Exception {
		logger.error("noSuchRequestHandlingMethodErrorHandler.exception="+exception.getMessage(),exception);
		return defaultHandler(exception);
	}
	
	@ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String,String> HttpRequestMethodNotSupportedMethodErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception exception) throws Exception {
		logger.error("noSuchRequestHandlingMethodErrorHandler.exception="+exception.getMessage(),exception);
		return defaultHandler(exception);
	}
	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> otherErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception exception) throws Exception {
		logger.error("otherErrorHandler.exception="+exception.getMessage(),exception);
		return defaultHandler(exception);
    }
	
	public static Map<String,String> defaultHandler(Exception exception){
		exception.printStackTrace();
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("message", exception.getMessage());
        return result;
	}
	
}
