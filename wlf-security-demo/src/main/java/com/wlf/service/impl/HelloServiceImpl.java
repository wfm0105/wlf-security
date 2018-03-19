package com.wlf.service.impl;

import org.springframework.stereotype.Service;

import com.wlf.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService{

	@Override
	public String greeting(String name) {
		
		return "hello "+name;
		
	}

}
