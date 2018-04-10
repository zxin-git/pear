package com.zxin.apache.common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.nutz.castor.Castors;

public class CodecTest {
	public static String str = "abc";
	static byte[] bs = str.getBytes(Charset.forName("gbk"));
	
	public static void baseTest64(){
		Base64 base = new Base64();
		String enchar = base.encodeToString(bs);
		byte[] enbs = base.encode(bs);
		
		byte[] debs = base.decode(enbs);
		System.out.println(enchar);
		System.out.println(new String(enbs));
		System.out.println(new String(debs));
		
	}
	
	public static byte[] base64Decode(byte[] bs){
		return Base64.encodeBase64(bs);
//		return Base64.decodeBase64(bs);
//		return DigestUtils.md5(bs);
//		DigestUtils.md5Hex(bs);
	}
	
	public static byte[] md5(byte[] bs){
		byte[] enbs = null;
		 try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");  
			 enbs = messageDigest.digest(bs);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		 return enbs;
	}
	
	public static void printAscii(){
		for(int i=0;i<=Byte.MAX_VALUE;i++){
			System.out.print( i+"-"+(char)i +" ");
			if(i%30==0){
				System.out.println("");
			}
		}
	}
	
	public static void main(String[] args) {
//		printAscii();
//		baseTest64();
		DigestUtils.md5Hex(bs);
		System.out.println(Hex.encodeHexString(md5("abc".getBytes())));
		md5("abc".getBytes());
	}
	

	
}
