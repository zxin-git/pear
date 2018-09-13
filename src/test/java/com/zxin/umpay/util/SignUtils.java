package com.zxin.umpay.util;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

public class SignUtils {

//	public String getSign(Map<String, String> map) {
//		String sign = MD5Utils.getMD5Str("funcode" + map.get("funcode") + "datetime" + map.get("datetime") + "merid"
//				+ map.get("merid") + "transid" + map.get("transid"));
//		return sign;
//	}


	public String getSign(String funcode, String datetime, String retcode, String transid) {
		String sign = DigestUtils
				.md5Hex("funcode" + funcode + "transid" + transid + "datetime" + datetime + "retcode" + retcode);
		return sign;

	}
	
	public static String getSign(Map<String, String> reqMap){
		StringBuffer buffer = new StringBuffer();
		buffer.append("funcode").append(reqMap.get("funcode"));
		buffer.append("datetime").append(reqMap.get("datetime"));
		buffer.append("merid").append(reqMap.get("merid"));
		buffer.append("transid").append(reqMap.get("transid"));
		
		return DigestUtils.md5Hex(buffer.toString());
	}
}
