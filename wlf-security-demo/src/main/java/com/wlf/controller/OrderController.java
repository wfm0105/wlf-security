package com.wlf.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.wlf.async.DeferredResultHolder;
import com.wlf.async.MockQueue;

@RestController
@RequestMapping("/order")
public class OrderController {

	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@RequestMapping
	public DeferredResult<String> order() throws Exception {
		
		logger.info("主线程开始");
		
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		DeferredResult<String> result = new DeferredResult<String>(5000L);
		result.onTimeout(new Thread(()-> {
			logger.info("处理超时！");
			result.setResult("place order success");
		}));
		deferredResultHolder.getMap().put(orderNumber, result);
		
		logger.info("主线程结束");
		
		return result;
		
	}
	
}
