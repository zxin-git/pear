package com.zxin.umpay.batch;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.umpay.http.ReqBodyBuilder;
import com.zxin.umpay.util.SignUtils;
import com.zxin.umpay.util.XmlUtils;

public class ReqBodyUtil {

	private static Logger logger = LoggerFactory.getLogger(ReqBodyUtil.class);

	
	public static String font(String mobile, String funcode, String license){
		return new ReqBodyBuilder().funcode(funcode).license(license).mobileid(mobile).sign().buildXml();
	}
	
//	public static String font(String mobile, String funcode, String license){
//		HashMap<String, String> reqMap = new HashMap<>();
//		reqMap.put("mobileid", mobile);
//		reqMap.put("funcode", funcode);
//		reqMap.put("license", license);
//		
//		reqMap.put("merid", "10001001");
//		reqMap.put("datetime", System.currentTimeMillis()+"");		
//		reqMap.put("transid", "zhangxin");
//		
//		reqMap.put("sign", SignUtils.getSign(reqMap));
//		String reqBody = XmlUtils.mapToXml(reqMap, "request");
//		return reqBody;
//	}
	
	public static String batch(String mobile){
		HashMap<String, String> reqMap = new HashMap<>();
		reqMap.put("childmerid", "YLZHMYTK");
		reqMap.put("funcode", "Mkt2000009");
		reqMap.put("license", "19y3sym331nanwyzupp2");
		reqMap.put("size", "200");
		reqMap.put("datetime", "20180228103838");		
		reqMap.put("merid", "10001001");
		reqMap.put("transid", "zhangxin");
		
		String file =  reqMap.get("funcode") + "_" + reqMap.get("merid") + "_" + reqMap.get("datetime") + ".txt";
		reqMap.put("file", file);
		
		reqMap.put("sign", SignUtils.batchSign(reqMap));
		String reqBody = XmlUtils.mapToXml(reqMap, "request");
		return reqBody;
	}
	
	public static String ylzh(String cid){
		HashMap<String, String> reqMap = new HashMap<>();
		reqMap.put("agreeMent","P0000360");
		reqMap.put("childmerid","134a4d125aa42f97e5727e919552f360");
		reqMap.put("cids",cid+"=2");
		reqMap.put("datetime","20180430234239");
		reqMap.put("funcode","Gcs6000002");
		reqMap.put("isFound","0");
		reqMap.put("isbatch","0");
		reqMap.put("license","mqa7vstxr28hy0hzzqh6");
		reqMap.put("merid","01010220");
		reqMap.put("queryType","5");
		String reqBody = XmlUtils.mapToXml(reqMap, "request");
		return reqBody; 
	}
	
}

