package com.zxin.jdk.node.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashMapErrorTest {

	private static final Logger logger = LoggerFactory.getLogger(HashMapErrorTest.class);

	private static final Map<String, String> map = new HashMap<>();
	
	public static void main(String[] args) {
		AtomicInteger ai = new AtomicInteger(0);
		ExecutorService exService = Executors.newCachedThreadPool();
		for (int i = 0; i < 1000; i++) {
			exService.execute(()->{
				String key = new Random().nextInt(99)+"";
				String value = new Random().nextInt(99)+"";
				map.put(key, value);
				map.put(value, key);
				System.out.println(Json.toJson(map,JsonFormat.tidy())+ai.incrementAndGet());
			});
		}
	}
}

