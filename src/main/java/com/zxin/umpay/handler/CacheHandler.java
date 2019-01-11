package com.zxin.umpay.handler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheHandler extends StatusHandler{

	private static final Logger logger = LoggerFactory.getLogger(CacheHandler.class);
	
//	private ICache cache;
	
	private final BlockingQueue<String> cache;
	
	public CacheHandler() {
		this.cache = new LinkedBlockingQueue<>();
	}
	
	public CacheHandler(BlockingQueue<String> cache) {
		this.cache = cache;
	}

	@Override
	public void process(String line) throws InterruptedException {
		cache.put(line);
	}

	public BlockingQueue<String> getCache() {
		return cache;
	}
	
	public int size(){
		return cache.size();
	}
	
}

