package com.wlf.async;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {

	Logger logger = LoggerFactory.getLogger(MockQueue.class);
	
	private String placeOrder;
	
	private String completedOrder;

	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder) {
		
		new Thread(()->{
			logger.info("收到订单消息："+placeOrder);
			this.placeOrder = placeOrder;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setCompletedOrder(placeOrder);
			logger.info("订单处理完毕："+placeOrder);
		}).start();
		
	}

	public String getCompletedOrder() {
		return completedOrder;
	}

	public void setCompletedOrder(String completedOrder) {
		this.completedOrder = completedOrder;
	}
	
}
