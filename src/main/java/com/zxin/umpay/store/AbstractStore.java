package com.zxin.umpay.store;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.umpay.handler.IHandler;
import com.zxin.umpay.handler.StatusHandler;

public abstract class AbstractStore extends StatusHandler implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(AbstractStore.class);
	
	private static final ExecutorService storeService = Executors.newFixedThreadPool(20);
	
	protected final BlockingQueue<String> storageQueue = new LinkedBlockingQueue<>();
	
	protected int interval = 100;
	
	protected AtomicBoolean running = new AtomicBoolean(true);

	public AbstractStore() {
		storeService.execute(this);
	}
	
//	@Override
//	public void handler(String line) throws Exception {
//		
//	}
	
	public abstract void write();
	
	public BlockingQueue<String> getStorageQueue(){
		return storageQueue;
	}
	
	public void stop(){
		running.set(false);
	}
	
	
}

