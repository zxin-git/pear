package com.zxin.umpay.handler;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.apache.http.httpclient.HttpClientUtil;
import com.zxin.umpay.app.AppEnum;
import com.zxin.umpay.http.ReqBodyBuilder;

public class HttpHandler implements IHandler{

	private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

	private final ReqBodyBuilder reqBodyBuilder;
	
	private final AppEnum app;
	
	private IPare pare;
	
	public HttpHandler(ReqBodyBuilder reqBodyBuilder, AppEnum app) {
		super();
		this.reqBodyBuilder = reqBodyBuilder;
		this.app = app;
	}

	@Override
	public void handler(String line) throws Exception {
		post(line);
//		sleepTest();
	}
	
	public void sleepTest() throws Exception{
		Thread.sleep(10*ThreadLocalRandom.current().nextInt(5));
	}
	
	public void post(String line) throws Exception{
		String reqXml = new ReqBodyBuilder(reqBodyBuilder).mobileid(line).sign().buildXml();
		String response =  HttpClientUtil.sendByPost(app.url(), reqXml);
//		System.out.println(response);
//		pare.pare(response);
	}
	
}


