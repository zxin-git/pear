package com.zxin.umpay.handler;

import java.awt.Stroke;
import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordHandler implements IHandler {

	private static final Logger logger = LoggerFactory.getLogger(RecordHandler.class);
	
	private static final BlockingQueue<String> storageQueue = new LinkedBlockingQueue<>();

	private File file;
	
	
	
	@Override
	public void handler(String data) throws Exception {
		storageQueue.put(data);
	}
	
	public void write(){
		
	}

}

