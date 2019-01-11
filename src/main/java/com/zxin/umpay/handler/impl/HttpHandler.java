package com.zxin.umpay.handler.impl;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.apache.http.httpclient.HttpClientUtil;
import com.zxin.apache.http.httpclient.HttpClientUtils;
import com.zxin.umpay.app.AppEnum;
import com.zxin.umpay.batch.ScanUtil;
import com.zxin.umpay.handler.IPare;
import com.zxin.umpay.handler.StatusHandler;
import com.zxin.umpay.http.ReqBodyBuilder;

//public class HttpHandler implements IHandler{
public class HttpHandler extends StatusHandler{

	private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

	private final ReqBodyBuilder reqBodyBuilder;
	
	private final AppEnum app;
	
	private String[] keys;
	
	protected final IPare pare = new IPare() {
		public String pare(String response) throws Exception {
			return response;
		};
	};
	
	public HttpHandler(ReqBodyBuilder reqBodyBuilder, AppEnum app) {
		super();
		this.reqBodyBuilder = reqBodyBuilder;
		this.app = app;
	}
	
	public HttpHandler(ReqBodyBuilder reqBodyBuilder, AppEnum app, String[] keys) {
		super();
		this.reqBodyBuilder = reqBodyBuilder;
		this.app = app;
		this.keys = keys;
	}
	
	public HttpHandler(ReqBodyBuilder reqBodyBuilder, AppEnum app, String firstLine) {
		super();
		this.reqBodyBuilder = reqBodyBuilder;
		this.app = app;
		this.firstLine = firstLine;
		this.keys = StringUtils.splitPreserveAllTokens(firstLine, ScanUtil.SEPARATOR);
	}

	@Override
	public void process(String line) throws Exception {
		post(line);
//		post(line, keys);
	}
	
	public void sleepTest() throws Exception{
		Thread.sleep(10*ThreadLocalRandom.current().nextInt(5));
	}
	
	public String postMobileid(String mobileid) throws Exception{
		String reqXml = new ReqBodyBuilder(reqBodyBuilder).mobileid(mobileid).sign().buildXml();
		String response =  HttpClientUtils.post(app.url(), reqXml);
//		String str = line+",\t"+response.replaceAll("\n", "")+"\n\n";
//		FileUtils.writeStringToFile(new File("E:/m.txt"), str,Charset.defaultCharset(),true);
		return response;
//		pare.pare(response);
	}
	
	public String post(String line) throws Exception{
		String response = HttpClientUtils.post(app.url(), line);
		return response;
	}
	
	public String post(String line, String[] keys) throws Exception{
		ReqBodyBuilder reqBodyBuilder = new ReqBodyBuilder(this.reqBodyBuilder);
		String[] values = StringUtils.splitPreserveAllTokens(line, ScanUtil.SEPARATOR);
		for (int i = 0; i < keys.length; i++) {
			reqBodyBuilder.set(keys[i], values[i]);
		}
		String response = HttpClientUtils.post(app.url(), reqBodyBuilder.sign().buildXml());
		return response;
	}
	
	
	public void get(String line) throws Exception{
		String response =  HttpClientUtil.sendGet(app.url());
		System.err.println(response);
	}

}


