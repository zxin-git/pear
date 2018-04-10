package com.zxin.apache.common;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Test {
	public static void main(String[] args) throws Exception {
	     
	    byte[] binaryData = "这是一个小小的测试 this is a test only".getBytes();
	     
	    long t1 = System.currentTimeMillis();     
	     
	    long t2 = System.currentTimeMillis();
	     
	    Encoder encoder = java.util.Base64.getEncoder();
	    Decoder decoder = java.util.Base64.getDecoder();
	    for (int i = 0; i < 10000 * 1000; i++)
	        encoder.encodeToString(binaryData);
//	    decoder.decode(encoder.encodeToString(binaryData));
	     
	    long t3 = System.currentTimeMillis();
	     
	    for (int i = 0; i < 10000 * 1000; i++)
	       	org.apache.commons.codec.binary.Base64.encodeBase64String(binaryData);
//	        org.apache.commons.codec.binary.Base64.decodeBase64(org.apache.commons.codec.binary.Base64.encodeBase64String(binaryData));
	     
	    long t4 = System.currentTimeMillis();
	     
	    System.out.println("t2-t1:"+(t2-t1));
	    System.out.println("t3-t2:"+(t3-t2));
	    System.out.println("t4-t3:"+(t4-t3));
	}
}
