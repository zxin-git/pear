package com.zxin.umpay.handler.impl;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.umpay.app.AppEnum;
import com.zxin.umpay.http.ReqBodyBuilder;
import com.zxin.umpay.store.AbstractStore;

public class StoreHandler extends HttpHandler{

	private static final Logger logger = LoggerFactory.getLogger(StoreHandler.class);

	private AtomicInteger errorCount = new AtomicInteger(0);
	
	private AtomicBoolean running = new AtomicBoolean(true);
	
	private AbstractStore store;
	
	public StoreHandler(ReqBodyBuilder reqBodyBuilder, AppEnum app, AbstractStore store) {
		super(reqBodyBuilder, app);
		this.store = store;
	}
	
	@Override
	public void handler(String line) throws Exception {
		String response = "";
		try{
			response = post(line);
		}catch (Exception e) {
			errorCount.incrementAndGet();
		}finally {
			store.getStorageQueue().put(line + "|" + pare.pare(response) + "\n");
//			store.write();
		}
	}

	
}

