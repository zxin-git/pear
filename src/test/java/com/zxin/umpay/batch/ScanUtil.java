package com.zxin.umpay.batch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.apache.http.httpclient.HttpClientUtil;
import com.zxin.umpay.handler.IHandler;
import com.zxin.umpay.util.XmlUtils;

public class ScanUtil {

	private static final Logger logger = LoggerFactory.getLogger(ScanUtil.class);

	/**
	 * 第二行开始跑批
	 * @param file
	 * @param handler
	 */
	public static void scan(File file, IHandler handler){
		try (Scanner scanner = new Scanner(file);) {
			String firstLine = scanner.nextLine();
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				handler.handler(line);
			}
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
}

