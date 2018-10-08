package com.zxin.umpay.handler;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheHandler implements IHandler{

	private static final Logger logger = LoggerFactory.getLogger(CacheHandler.class);
	
//	private ICache cache;
	
	private final BlockingQueue<String> cache;
	
	public CacheHandler(BlockingQueue<String> cache) {
		this.cache = cache;
	}

	@Override
	public void handler(String line) throws Exception {
		cache.put(line);
	}
	
	
	
}

