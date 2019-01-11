package com.zxin.umpay.handler.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.apache.http.httpclient.HttpClientUtils;
import com.zxin.umpay.app.AppEnum;
import com.zxin.umpay.handler.IHandler;
import com.zxin.umpay.handler.IPare;
import com.zxin.umpay.http.ReqBodyBuilder;
import com.zxin.umpay.store.AbstractStore;

public class BatchHandler implements IHandler{

	private static final Logger logger = LoggerFactory.getLogger(BatchHandler.class);

	private final ReqBodyBuilder reqBodyBuilder;
	
	private final AppEnum app;
	
	private final IPare pare = new IPare() {
		public String pare(String response) throws Exception {
			return response;
		};
	};
	
	private final BlockingQueue<String> storageQueue = new LinkedBlockingQueue<>();
	
	protected final int interval = 0;
	
	private AtomicInteger errorCount = new AtomicInteger(0);
	
	private AtomicBoolean running = new AtomicBoolean(true);
	
	private AbstractStore store;
	
	public BatchHandler(ReqBodyBuilder reqBodyBuilder, AppEnum app, AbstractStore store) {
		super();
		this.reqBodyBuilder = reqBodyBuilder;
		this.app = app;
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
			store.write();
//			storageQueue.put(line + "|" + pare.pare(response) + "\n");
//			write();
		}
	}
	
	public String post(String line) throws Exception{
		String reqXml = new ReqBodyBuilder(reqBodyBuilder).mobileid(line).sign().buildXml();
		String response =  HttpClientUtils.post(app.url(), reqXml);
		String str = line+",\t"+response.replaceAll("\n", "")+"\n\n";
		return response;
	}
	
//	public void record(){
//		if (storageQueue.size() >= interval || !running.get()) {
//			synchronized(file){
//				if (storageQueue.size() >= interval || !running.get()) {
//					write();
//				}
//			}
//		}
//	}
//	
//	public void write(){
//		List<String> list = new ArrayList<>();
//		storageQueue.drainTo(list, 100);
//		try{
//			if(writer == null){
//				writer = new FileWriter(file);
//			}
//			for (int i = 0; i < list.size(); i++) {
//				writer.append(list.get(i));
//			}
//		} catch (IOException e) {
//			logger.warn("文件写入失败",e);
//		}finally {
//			try {
//				writer.flush();
////				writer.close();
//			} catch (IOException e) {
//				logger.debug("",e);
//			}
//		}
//	}
	
	
}

