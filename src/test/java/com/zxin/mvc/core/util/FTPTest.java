package com.zxin.mvc.core.util;

import java.util.List;

public class FTPTest {
	public static void main(String[] args) {
		FtpUtil ftp = new FtpUtil("192.168.12.227",21, "admin","admin");
		boolean isLogin = ftp.login();
		if(isLogin){
			List<String> files = ftp.getFileList("/");
			for (String string : files) {
				System.out.println(string);
			}
		}
	}
}
