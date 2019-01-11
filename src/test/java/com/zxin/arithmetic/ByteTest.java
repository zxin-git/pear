package com.zxin.arithmetic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteTest {

	private static final Logger logger = LoggerFactory.getLogger(ByteTest.class);

	public static void main(String[] args) {
		byte b = 0x01;
		String hex = Integer.toHexString(b &0xFF);
		if (hex.length() == 1)
			hex = "0" + hex;
		System.out.println(hex);
	}
	
	
	private static final String bytesToHex(byte[] bytes) {
		
		if (bytes == null)
			return null;
		
		StringBuilder sb = new StringBuilder();
		String hex;
		for (byte b : bytes) {
			hex = Integer.toHexString(b &0xFF);
			if (hex.length() == 1)
				sb.append("0");
			sb.append(hex.toLowerCase());
		}
		
		return sb.toString();
	}
}

