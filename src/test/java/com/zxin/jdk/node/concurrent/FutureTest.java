package com.zxin.jdk.node.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FutureTest {

	private static Logger logger = LoggerFactory.getLogger(FutureTest.class);
	
	public static void main(String[] args) {
		ExecutorService eService =  Executors.newFixedThreadPool(10);
		ThreadLocal<HashMap<String,String>> data = new ThreadLocal<HashMap<String,String>>(){
			protected HashMap<String,String> initialValue() {
		        return new HashMap<String,String>();
		    }
		};
		List<Future<HashMap<String,String>>> list = new ArrayList<Future<HashMap<String,String>>>();
		for (int i = 0; i < 20; i++) {
			final int a = i ;
			Future<HashMap<String,String>> future = eService.submit(new Callable<HashMap<String,String>>() {
				@Override
				public HashMap<String,String> call() throws Exception {
					data.get().clear();
//					logger.info("第{}个  线程名字:{}", a,Thread.currentThread().getName());
					data.get().put(""+a, Thread.currentThread().getName());
//					return data.get();
					return new HashMap<String,String>(data.get());
				}
			});
			list.add(future);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			logger.debug("",e1);
		}
		for (Future<HashMap<String,String>> future : list) {
			
			try {
				logger.info(Json.toJson(future.get()));
			} catch (InterruptedException e) {
				logger.debug("",e);
			} catch (ExecutionException e) {
				logger.debug("",e);
			}
		}
	}
}

