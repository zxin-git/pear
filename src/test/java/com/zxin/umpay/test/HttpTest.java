package com.zxin.umpay.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.apache.http.httpclient.HttpClientUtil;
import com.zxin.umpay.app.AppEnum;
import com.zxin.umpay.http.ReqBodyBuilder;
import com.zxin.umpay.util.SignUtils;

public class HttpTest {

	private static Logger logger = LoggerFactory.getLogger(HttpTest.class);

	public static String ylzh(String cid){
		String reqXml = new ReqBodyBuilder().funcode("Mkt2000009").license("19y3sym331nanwyzupp2").childmerid("YLZHMYTK")
				.set("cids",cid+"=2").set("isFound","0").set("isbatch","0").sign().buildXml();
		return reqXml; 
	}
	
	
	public static void main(String[] args){
		try {
			String reqXml = new ReqBodyBuilder().funcode("Mkt2000009").license("19y3sym331nanwyzupp2").childmerid("YLZHMYTK")
					.datetime("20180228103838").size("200").file().sign( (reqMap) -> {return SignUtils.batchSign(reqMap);} ).buildXml();
			String response = HttpClientUtil.sendByPost(AppEnum.BATCH_PRO.url(), reqXml);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

