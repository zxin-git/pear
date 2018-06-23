package com.zxin.jdk.node.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FutureTest {

	private static Logger logger = LoggerFactory.getLogger(FutureTest.class);
	
	public static void main(String[] args) {
		ExecutorService eService =  Executors.newFixedThreadPool(10);
		Future<String> future = eService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return null;
			}
		});
	}
}

