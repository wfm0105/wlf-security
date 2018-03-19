package com.wlf.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wlf.service.HelloService;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint,String> {

	private Logger logger = LoggerFactory.getLogger(MyConstraintValidator.class);
	
	@Autowired
	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint myConstraint) {
		logger.info("MyConstraintValidator.initialize");
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		logger.info("MyConstraintValidator.initialize.value="+value);
		helloService.greeting("world");
		return true;
	}

}
