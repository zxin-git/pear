package com.zxin.umpay.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.apache.http.httpclient.HttpClientUtil;
import com.zxin.umpay.util.SignUtils;
import com.zxin.umpay.util.XmlUtils;

public class HttpTest {

	private static Logger logger = LoggerFactory.getLogger(HttpTest.class);

	public static String font(){
		HashMap<String, String> reqMap = new HashMap<>();
//		reqMap.put("childmerid", "unknow");
		reqMap.put("mobileid", "");

		reqMap.put("funcode", "Gck1010842");
		reqMap.put("merid", "10001001");
		reqMap.put("license", "q2kwa1858axh7hmxy21k");
		
		reqMap.put("datetime", "20180911");		
		reqMap.put("transid", "zhangxin");
		
		reqMap.put("sign", SignUtils.getSign(reqMap));
		String reqBody = XmlUtils.mapToXml(reqMap, "request");
		return reqBody;
	}
	
	public static String yl(String cid){
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
	
	public static void main(String[] args) {
//		String url = "http://10.102.5.51:9006/umpaydc/dataQuery/query/";
//		String url = "http://10.102.5.53:9005/umpaydc/dataQuery/query/";
		try {
			String ylUrl = "http://localhost:9018/";
			
			Scanner scanner = new Scanner(new File("E:\\zxin\\developer\\tmp\\tmp\\z.txt"));
			int i = 0; 
			while (scanner.hasNext()) {
				String cid = scanner.next();
				System.err.println(HttpClientUtil.sendByPost(ylUrl, yl(cid)));
			}
		} catch (FileNotFoundException e) {
			logger.debug("",e);
		} catch (Exception e) {
			logger.debug("",e);
		}
		
//		String content = font();
//		System.out.println(content);
//		String respXml = null;
//		try {
//			respXml = HttpClientUtil.sendByPost(url, content);
//		} catch (Exception e) {
//			logger.debug("",e);
//		}
//		System.out.println(respXml);
	}
	
}

