package com.zxin.apache.http.httpclient.arronlong;

import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.SSLs.SSLProtocolVersion;
import com.arronlong.httpclientutil.exception.HttpProcessException;

public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	private static HttpClient httpclient = null;
	
	public static HttpClient getHttpclient(){
		if(httpclient==null){
			synchronized(HttpUtil.class){
				if (httpclient==null) {
					try {
						httpclient = HCB.custom().pool(500, 20).sslpv(SSLProtocolVersion.TLSv1_2).ssl().build();
					} catch (HttpProcessException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		return httpclient;
	}
	

	public static String post(String url, String reqXml){
		String resp = "";
		try {
			resp = HttpClientUtil.post(HttpConfig.custom().url(url).json(reqXml).client(getHttpclient()));
		} catch (HttpProcessException e) {
			logger.warn("",e);
		}
		return resp;
	}
	
	public static void main(String[] args) {
		try {
			HttpClient httpclient = HCB.custom().pool(500, 20).sslpv(SSLProtocolVersion.TLSv1_2).ssl().build();
			String url = "https://opendevl.fbank.com/pm2/opencallback/callback/passthrough/bg4ice?apiCode=bigdata&apiMethod=iceBingLianFen";
//			String url = "https://10.102.1.75/v2/dps-registry/tags/list";
			String html = HttpClientUtil.post(HttpConfig.custom().url(url).json("sdfsda").client(httpclient));
			System.out.println(html);
		} catch (HttpProcessException e) {
			logger.warn("",e);
		}
		
	}
}

