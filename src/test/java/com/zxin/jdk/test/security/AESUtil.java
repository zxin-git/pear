package com.zxin.jdk.test.security;

import java.security.SecureRandom;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.alibaba.druid.util.StringUtils;

/**
 * @Title AESUtil调用工具类
 * @author zxin
 * @decription 统一 utf-8字符
 */
public class AESUtil {
    private static final String ALGORITHM = "AES";
    private static final String DEFAULT_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String DEFAULT_IV_STRING = "a1b2c3d4e5f6g7h8";	//16 bytes
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_SEED = "ldys";
    private static final String DEFAULT_KEY = "86B9154349064F6F";	//ASCII
    private static enum EncodeEnum { HEX,BASE64,ASCII };
    
    
    /**
     * 16 bytes 128 bits
     * Hex的随机数
     * @return
     */
    public static String randomKey(){
    	return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
    
    /**
     * 获取秘钥
     * @return 
     * @throws Exception
     */
    public static SecretKey createKey(String seed) throws Exception{
    	SecretKey genSecretKey = null;
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom random = new SecureRandom();
		random.setSeed(seed.getBytes());
		keyGenerator.init(random);	//不使用种子,每次生成的都不同
//		keyGenerator.init(128);
		genSecretKey = keyGenerator.generateKey();
		return genSecretKey;
    }
    
    /**
     * 创建编码后的秘钥字符串
     * @param encodeType 编码类型
     * @return
     * @throws Exception
     */
    public static String createKeyStr(EncodeEnum encodeType, String seed) throws Exception{
    	String key = "";
    	encodeType = encodeType == null ? EncodeEnum.ASCII : encodeType;
    	switch (encodeType) {
		case HEX:
			key = Hex.encodeHexString(createKey(seed).getEncoded());
			break;
		case BASE64:
			key = Base64.encodeBase64String(createKey(seed).getEncoded());
			break;
		default:
			key = new String(createKey(seed).getEncoded(), EncodeEnum.ASCII.toString());
		}
    	return key;
    }
    
    /**
     * 
     * @param encodeType
     * @return
     * @throws Exception
     */
    public static String createKeyStr(EncodeEnum encodeType) throws Exception{
    	return createKeyStr(encodeType, DEFAULT_SEED);
    }
    
    /**
     * 
     * @param content 原文
     * @param keyString
     * @param ivString
     * @return 密文
     * @throws Exception
     */
    @Deprecated
    public static String encrypt(String content, String keyString, String ivString) throws Exception{
    	byte[] raw = content.getBytes(DEFAULT_CHARSET);
    	byte[] data = encryptBytes(raw, DEFAULT_TRANSFORMATION, keyString, ivString);
    	return new String(data, DEFAULT_CHARSET);
    }
    
    /**
     * 加密成HEX形式
     * @param content 原文
     * @param keyString
     * @param ivString
     * @return HEX密文
     * @throws Exception
     */
    public static String encrypt2Hex(String content, String keyString, String ivString) throws Exception{
    	byte[] raw = content.getBytes(DEFAULT_CHARSET);
    	byte[] data = encryptBytes(raw, DEFAULT_TRANSFORMATION, keyString, ivString);
    	return Hex.encodeHexString(data);
    }
    
    /**
     * 加密成Base64形式
     * @param content 原文
     * @param keyString
     * @param ivString
     * @return Base64密文
     * @throws Exception
     */
    public static String encrypt2Base64(String content, String keyString, String ivString) throws Exception{
    	byte[] raw = content.getBytes(DEFAULT_CHARSET);
    	byte[] data = encryptBytes(raw, DEFAULT_TRANSFORMATION, keyString, ivString);
    	return Base64.encodeBase64String(data);
    }
    
    /**
     * 
     * @param content 原文字节数组
     * @param transformation 加密填充模式
     * @param keyString AES秘钥
     * @param ivString 初始化向量字符串
     * @return 密文字节数组
     * @throws Exception
     */
    public static byte[] encryptBytes(byte[] content, String transformation, String keyString, String ivString) throws Exception{
    	SecretKeySpec seKeySpec = generateKey(keyString);
        IvParameterSpec iv = new IvParameterSpec(ivString.getBytes(DEFAULT_CHARSET));
		return encryptBytes(content, transformation, seKeySpec, iv);
    }
    
    
    /**
     *  
     * @param content 密文字节数组
     * @param transformation 加密填充模式
     * @param key 秘钥
     * @param iv 初始化向量
     * @return 原文字节数组
     * @throws Exception
     */
    public static byte[] encryptBytes(byte[] content, String transformation, SecretKeySpec key, IvParameterSpec iv) throws Exception {
    	return doHandler(content, transformation, Cipher.ENCRYPT_MODE, key, iv);
    }
    
    /**
     * 解密Hex类型密文
     * @param content hex密文
     * @param keyString AES秘钥
     * @param ivString 初始化向量字符串
     * @return 原文
     * @throws Exception
     */
    public static String decryptHex(String content, String keyString , String ivString) throws Exception{
    	byte[] raw = Hex.decodeHex(content.toCharArray());
    	byte[] data = decryptBytes(raw, DEFAULT_TRANSFORMATION, keyString, ivString);
    	return new String(data, DEFAULT_CHARSET);
    }
    
    
    /**
     * 解密Base64类型密文
     * @param content Base64密文
     * @param keyString AES秘钥
     * @param ivString 初始化向量字符串
     * @return 原文
     * @throws Exception
     */
    public static String decryptBase64(String content, String keyString, String ivString) throws Exception{
    	byte[] raw = Base64.decodeBase64(content);
    	byte[] data = decryptBytes(raw, DEFAULT_TRANSFORMATION, keyString, ivString);
    	return new String(data, DEFAULT_CHARSET);
    }
    

    /**
     * 
     * @param content 密文字节数组
     * @param transformation 加密填充模式
     * @param keyString AES秘钥
     * @param ivString 初始化向量字符串
     * @return 原文字节数组
     * @throws Exception
     */
    public static byte[] decryptBytes(byte[] content, String transformation, String keyString, String ivString) throws Exception{
    	SecretKeySpec seKeySpec = generateKey(keyString);
        IvParameterSpec iv = new IvParameterSpec(ivString.getBytes(DEFAULT_CHARSET));
		return decryptBytes(content, transformation, seKeySpec, iv);
    }
    
    /**
     *  
     * @param content 密文字节数组
     * @param transformation 加密填充模式
     * @param key 秘钥
     * @param iv 初始化向量
     * @return 原文字节数组
     * @throws Exception
     */
    public static byte[] decryptBytes(byte[] content, String transformation, SecretKeySpec key, IvParameterSpec iv) throws Exception {
    	return doHandler(content, transformation, Cipher.DECRYPT_MODE, key, iv);
    }
    
    
    /**
     * 加解密处理
     * @param transformation
     * @param mode 范围C
     * @param key
     * @param iv
     * @param data
     * @return
     * @throws Exception
     */
    private static byte[] doHandler(byte[] content, String transformation, int mode, SecretKeySpec key, IvParameterSpec iv) throws Exception{
    	Cipher cipher = Cipher.getInstance(transformation);
    	if(StringUtils.isEmpty(transformation)){
    		transformation = DEFAULT_TRANSFORMATION;
    	}
    	if(iv==null || "AES".equals(transformation) || transformation.contains("ECB") ){
    		cipher.init(mode, key);
    	}else{
    		cipher.init(mode, key, iv);
    	}
    	return cipher.doFinal(content);
    }
 
    /**
     * 获取秘钥
     * @param key 秘钥字符串
     * @param encodeType  秘钥编码类型
     * @return
     * @throws DecoderException 
     */
    public static SecretKeySpec generateKey(String key, EncodeEnum encodeType) throws Exception {
        byte[] raw = null;
        encodeType = encodeType == null ? EncodeEnum.ASCII : encodeType;
        switch (encodeType) {
			case HEX: 
				raw = Hex.decodeHex(key); break;
			case BASE64: 
				raw = Base64.decodeBase64(key); break;
			default: 
				raw = key.getBytes(EncodeEnum.ASCII.toString()); break;
		}
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
        return secretKeySpec;
    }
    
    public static SecretKeySpec generateKey(String key) throws Exception{
//    	return generateKey(key,EncodeEnum.HEX);
    	return generateKey(key,EncodeEnum.ASCII);
    }
    
    public static void main(String[] args) {
        try {
//        	String key = createKeyStr(EncodeEnum.HEX).toUpperCase();
        	String key = randomKey().toUpperCase();
//        	String key = "3A34F4324D25452D";
        	System.out.println(key);
        	String hex = encrypt2Hex("qwertyuiopsdsssddssasdfasdfdad", key, DEFAULT_IV_STRING).toUpperCase();
        	System.out.println(hex);
        	String content = decryptHex(hex, key, DEFAULT_IV_STRING);
        	System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
