package com.zxin.jdk.node.random;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomTest {

	private static Logger logger = LoggerFactory.getLogger(RandomTest.class);
	
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for (int i = 0; i < 1000; i++) {
			threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					logger.info(ThreadLocalRandom.current().nextInt(100)+"");
				}
			});
		}
		while(threadPoolExecutor.getActiveCount()==0){
			stopWatch.stop();
			
//		Thread.sleep(1000);
		}
		System.out.println(threadPoolExecutor.getActiveCount());
//		System.out.println(stopWatch.getTime());
	}
}

