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
//			String reqXml = new ReqBodyBuilder().funcode("Mkt2000009").license("19y3sym331nanwyzupp2").childmerid("YLZHMYTK")
//					.datetime("20180228103838").size("200").file().sign( (reqMap) -> {return SignUtils.batchSign(reqMap);} ).buildXml();
//			String reqXml = new ReqBodyBuilder().funcode("Gck00017").mobileid("e156f80b8b686a41c75d4cac88cd38cc").license("axffcfmnbhhtn5e0b0qb").datetime("20181101135949").sign().buildXml(); 
//			String reqXml = ylzh("e1164a5e022670be75142fa4bb571972");
//			String response = HttpClientUtil.sendByPost(AppEnum.TEST.url(), reqXml);
//			System.out.println(response);
			test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void e9015() throws Exception{
		String reqXml = new ReqBodyBuilder().funcode("Mkt2000009").license("19y3sym331nanwyzupp2").childmerid("YLZHMYTK")
				.datetime("20180228103838").size("200").file().sign( (reqMap) -> {return SignUtils.batchSign(reqMap);} ).buildXml();
		String response = HttpClientUtil.sendByPost(AppEnum.BATCH_PRO.url(), reqXml);
		System.out.println(response);
	}
	
	public static String test() throws Exception{
		String reqXml = new ReqBodyBuilder().funcode("Gcs6000023").name("杨盛䶮").identityNo("220623197812271317").childmerid("cangu").mobileid("13596711609").buildXml();
		String response = HttpClientUtil.sendByPost(AppEnum.FONT_UAT_53.url(), reqXml);
		System.out.println(response);
		return "";
	}
}

