package com.zxin.apache.http.rs;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class MyClient {

	public static void main(String[] args) throws Exception {
		go("http://localhost:9000/ws/jaxrs/customer/1/info");
		go("http://localhost:9000/ws/jaxrs/customer/search?name=abc");
	}

	private static void go(String url) throws Exception {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		int statusCode = client.executeMethod(method);
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed: " + method.getStatusLine());
		}
		byte[] responseBody = method.getResponseBody();
		System.out.println(new String(responseBody));
	}
}