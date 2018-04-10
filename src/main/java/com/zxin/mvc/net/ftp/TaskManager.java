package com.zxin.mvc.net.ftp;

import java.util.List;

import org.nutz.json.Json;

import com.zxin.mvc.core.util.FtpUtil;

public class TaskManager {
	public static String ip = "127.0.0.1";
	public static int port = 21;
	public FtpUtil ftpUtil; 
	
	public static void main(String[] args){
		FtpUtil ftpUtil = new FtpUtil("127.0.0.1", 21, "admin", "123456","GBK",true);
		ftpUtil.login();
		if(ftpUtil.isConnected()){
			List<String> list =ftpUtil.getFileList("/",true);
			
			System.out.println(Json.toJson(list));
		}
//		for(int i=0;i<10;i++){
//			new Thread(new DefaultTask("elasticsearch权威指南-中文.pdf")).start();
//		}
	}
	
	
}
