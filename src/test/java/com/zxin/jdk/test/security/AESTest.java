package com.zxin.jdk.test.security;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESTest {

	private static Logger logger = LoggerFactory.getLogger(AESTest.class);

	public static void main(String[] args) {
		File file = new File("D:\\mobile_fileProcess_20180927205911_51968_encry.txt");
		File outer = new File("D:\\outer.txt");
		
		try(Scanner scanner = new Scanner(file);) {
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				String data = AESUtil.decryptBase64(line, "f9aaaa61-9dfb-42", "8b-abbb-be121323");
				FileUtils.write(outer, data+"\n", Charset.defaultCharset(), true);
				Thread.sleep(10);
			}
		} catch (Exception e) {
			logger.debug("",e);
		}
	}
}

