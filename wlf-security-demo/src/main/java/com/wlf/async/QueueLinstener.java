package com.wlf.async;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueLinstener implements ApplicationListener<ContextRefreshedEvent>{

	Logger logger = LoggerFactory.getLogger(QueueLinstener.class);
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		new Thread(()-> {
			while(true) {
				if(StringUtils.isNoneBlank(mockQueue.getCompletedOrder())) {
					String orderNumber = mockQueue.getCompletedOrder();
					logger.info("订单处理结果："+orderNumber);
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
					mockQueue.setCompletedOrder(null);
				} else {
					try {
						TimeUnit.MICROSECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start(); 
		
	}
	
}
