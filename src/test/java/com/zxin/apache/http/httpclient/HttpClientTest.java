package com.zxin.apache.http.httpclient;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.umpay.app.AppEnum;

public class HttpClientTest {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientTest.class);

	public static void main(String[] args) {
		
	}
	
	@Test
	public void first() throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(AppEnum.GATEWAY_TEST.url());
		CloseableHttpResponse resp = httpclient.execute(httpGet);
	}
	
	@Test
	public void sec() throws Exception{
		
	}
	
	@Test
	public void url() throws Exception{
		URI uri = new URIBuilder()
				.setScheme("http")
				.setHost("www.baidu.com")
				.setPath("/search")
				.setParameter("q", "httpclient")
				.build();
	}
	
	@Test
	public void entity() throws Exception{
		StringEntity myEntity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));
		System.out.println(myEntity.getContentType());
		System.out.println(myEntity.getContentLength());
		System.out.println(EntityUtils.toString(myEntity));
		System.out.println(EntityUtils.toByteArray(myEntity).length);
	}
	
	@Test
	public void StringEntity() throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(AppEnum.GATEWAY_TEST.url());
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
		    HttpEntity entity = response.getEntity();
		    if (entity != null) {
		        long len = entity.getContentLength();
		        if (len != -1 && len < 2048) {		//防止大文本
		            System.out.println(EntityUtils.toString(entity));
		        } else {
		            // Stream content out
		        }
		    }
		} finally {
		    response.close();
		}
	}
	
	
}

