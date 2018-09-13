package com.zxin.jdk.node.serial;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.jdk.test.security.RSAUtil;
import com.zxin.util.JsonUtil;

public class JsonTest {

	private static Logger logger = LoggerFactory.getLogger(JsonTest.class);
	
	public static void main(String[] args) throws Exception {
		String json = "";
			json = FileUtils.readFileToString(new File("E:\\zxin\\developer\\tmp\\java\\encrypt.json"),"UTF-8");
//			json = FileUtils.readFileToString(new File("E:\\zxin\\developer\\tmp\\java\\encrypt.json"),"UTF-8");
			System.out.println(json);
//			String encrypt = RSAUtil.publicEncrypt(json, RSAUtil.defaultPublicKey());
//			FileUtils.writeStringToFile(new File("E:\\zxin\\developer\\tmp\\java\\encrypt.json"), encrypt,"UTF-8");
			Map map = JsonUtil.jsonString2Obj(json, Map.class);
//			Map map = Json.fromJsonAsMap(Object.class, json);
			System.out.println(Json.toJson(map));
	}
}



