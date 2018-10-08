package com.zxin.jdk.test.security;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.nutz.json.Json;
import org.nutz.lang.Files;

/**
 * @author zxin
 * RSA非对称加解密
 */
public class RSAUtil {

    public static final String CHARSET = "UTF-8";
    
    public static final String RSA_ALGORITHM = "RSA";
    public static final String DEFAULT_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    
    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";
    
    
    /**
     * 创建一对RSA公钥和私钥
     * @param keySize
     * @return
     */
    public static Map<String, String> createKeys(int keySize){
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try{
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        }catch(NoSuchAlgorithmException e){
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put(PUBLIC_KEY, publicKeyStr);
        keyPairMap.put(PRIVATE_KEY, privateKeyStr);

        return keyPairMap;
    }
    
    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }
    
    /**
     * 私钥加密
     * @param data 原文
     * @param privateKey
     * @return 密文
     */
    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
    	return privateHandler(DEFAULT_TRANSFORMATION, data, privateKey, Cipher.ENCRYPT_MODE);
    }
    
    
    public static String privateDecrypt(String encrypt,String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException{
    	return privateHandler(DEFAULT_TRANSFORMATION, encrypt, getPrivateKey(privateKey), Cipher.DECRYPT_MODE);
    }
    
    /**
     * 私钥解密
     * @param encrypt 密文
     * @param privateKey
     * @return 原文
     */
    public static String privateDecrypt(String encrypt,RSAPrivateKey privateKey){
    	return privateHandler(DEFAULT_TRANSFORMATION, encrypt, privateKey, Cipher.DECRYPT_MODE);
    }
    
    /**
     * 私钥处理
     * @param transformation 加密模式
     * @param data	原文
     * @param privateKey 私钥
     * @param mode	输入 Cipher.DECRYPT_MODE 或 Cipher.ENCRYPT_MODE
     * @return
     */
    public static String privateHandler(String transformation, String data, RSAPrivateKey privateKey,int mode){
    	return doHandler(transformation, data, privateKey, mode);
    }
    
    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return 密文
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
    	return publicHandler(DEFAULT_TRANSFORMATION, data, publicKey, Cipher.ENCRYPT_MODE);
    }
    
    /**
     * 公钥解密
     * @param encrypt 密文
     * @param publicKey 公钥
     * @return 原文
     */
    public static String publicDecrypt(String encrypt, RSAPublicKey publicKey){
    	return publicHandler(DEFAULT_TRANSFORMATION, encrypt, publicKey, Cipher.DECRYPT_MODE);
    }
    
    
    /**
     * 公钥处理
     * @param transformation 加密模式
     * @param data	原文
     * @param publicKey 公钥
     * @param mode	输入 Cipher.DECRYPT_MODE 或 Cipher.ENCRYPT_MODE
     * @return
     */
    public static String publicHandler(String transformation, String data, RSAPublicKey publicKey,int mode){
    	return doHandler(transformation, data, publicKey, mode);
    }
    
    /**
     * 处理加解密
     * @param transformation 加密模式
     * @param data
     * @param rsaKey  必须同时为java.security.Key的实现类
     * @param mode	输入 Cipher.DECRYPT_MODE 或 Cipher.ENCRYPT_MODE
     * @return
     */
    private static String doHandler(String transformation, String data, RSAKey rsaKey, int mode){
        try{
        	Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(mode, (Key)rsaKey );
            if(mode == Cipher.DECRYPT_MODE){
            	return new String(rsaSplitCodec(cipher, mode, Base64.decodeBase64(data), rsaKey.getModulus().bitLength()), CHARSET);
            }else{
                return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), rsaKey.getModulus().bitLength()));
            }
        }catch(Exception e){
            throw new RuntimeException("加解密字符串[" + data + "]时遇到异常", e);
        }
    }
    
    
	private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else if(opmode == Cipher.ENCRYPT_MODE){
            maxBlock = keySize / 8 - 11;
        }else{
        	throw new RuntimeException("模式只能为加密或者解密["+opmode+"]发生异常"); 
        }
        
        int offSet = 0;
        byte[] buff;
        int i = 0;
        byte[] resultDatas;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }finally{
        	resultDatas = out.toByteArray();
        	IOUtils.closeQuietly(out);
        }
        return resultDatas;
    }
    
    
    public static void makeKeys(File file){
    	Files.write(file, Json.toJson(createKeys(2048)));
    }
	
    public static void main(String[] args) {
    	try {
    		test1();
    		
//			String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALcYRPbQEgTpZ2Houu9Kma8dCwGuheYJJPCVVsc3Tmr0q4zkw_V9rcYzN_PKBOGlqr6zUGSRZxRoeos7txVzvpsCAwEAAQ";
//			String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAtxhE9tASBOlnYei670qZrx0LAa6F5gkk8JVWxzdOavSrjOTD9X2txjM388oE4aWqvrNQZJFnFGh6izu3FXO-mwIDAQABAkApMD17ZG1ZS4RlQyyiXh7ahOcP9snKaO41bPL7l9Z9OrHZByAzVflr2jf-N01Di82J4gcH5uCcO6Z88OKLpP25AiEA3dl7ZUilwsGdJPbeKZ6ng1Qtz7cUo8TchA4HxkJRH6cCIQDTR5AsvsKdzW0vKZgePOoHBR9Mco4Q7TnsBowJwCEn7QIhAJDWo2RN2MLdKx5t-j-L8GuicsJREi2Voi6pBlRfCZ2zAiEAzzRi6A8ZbIt4JgXD4vvYhJP4cw_x1hXhdWGiWzmrCq0CIH5Zd5rxAIOBUoq0NjORzaPZX9FEhIRkRNOzmtoImaq4";
//			String original = "{\"customId\": \"mockCustomMark\",\"cardId\": \"mockCard\",\"type\": \"T001,T002,T003,T004\"}";
//			
//			final RSAPrivateKey rsaPrivateKey = RSAUtil.getPrivateKey(privateKey);
//			RSAPublicKey rsaPublicKey = RSAUtil.getPublicKey(publicKey);
//			
//			final String encrypt = RSAUtil.publicEncrypt(original, rsaPublicKey);
////			String str = RSAUtil.privateDecrypt(encrypt, rsaPrivateKey);
////			System.out.println(str);
//			
//			ExecutorService exService = Executors.newFixedThreadPool(10);
//			for(int i=0;i<100;i++){
//				final int a = i;
//				exService.execute(new Runnable() {
//					@Override
//					public void run() {
//						String str = RSAUtil.privateDecrypt(encrypt, rsaPrivateKey);
//						System.out.println(a+"\t"+str);
//					}
//				});
//			}
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
    
    
    public static void test1(){
    	try {
//    	  String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ4YJ54OBrVMdH2LaElqRc0eig2bC2jTXV3y6jsz_hP_OKj8QTViml1WuNF1RLe3x9r-irL0CJmyn4pHJNmkekIh3T-XSXXfhSkLZy7sq_fGvVxGMr_IkObUdPxOvD2zvTrz2jE4G24RQ-Goj41gC3VZCb-64VI9G4Ac9w1n9BS9AgMBAAECgYBsuq4cnQbuD6waYBOUUlu5S-SkoNqC-MNBBv0jRIndFO3SAtOSNMN3qFop7F4bgHEaY2_ezclQc7IX9wAYE2_lD3xvL55GQxhNOEHHSnEjvK6eH3fCAWYnAwSXrP_RWrIemEPFAunVasaCaAbVQ8lImUhkFKKX_8oiCuEVSJIbPQJBAN_G6plss-hDucCCBJvqzeY1nfJm1zH8IfxrXiyZT799q6G7GAUWhId-B7oH-MNLQAnmptjqpiylbFebfORPt8MCQQC02_sa1O0UAjN88z36K89Qz0qb8L_6RovNTK9fnEYVK7DRPHjemmHkiNPs4B6LnigJxucT08ixgIMB3kOnNLl_AkEAjD6NLuR6iBlR4_1YSCNLMXju6Ulcmt4Kqrjbh5X57egj1bN85UtsecIZs5RlanfRxaLBdHby-E4gbXNsPWZcowJACMA4FyVET1qH6TzPu-IQxuUww_jTISX2r7lNJwAlt_KEPKuDYanwoKEqN-TkNXcykr6IAcKWjFgefqGlflqjuwJBALyzu_3VlXcZ59ccswRTD1xFY823xpGO_USNJQa_-rvlS1NDoW-gszb1gmGpMvYkzdcYs6BTPWCYn7DSC6IM0t0";
//    	  String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCeGCeeDga1THR9i2hJakXNHooNmwto011d8uo7M_4T_zio_EE1YppdVrjRdUS3t8fa_oqy9AiZsp-KRyTZpHpCId0_l0l134UpC2cu7Kv3xr1cRjK_yJDm1HT8Trw9s70689oxOBtuEUPhqI-NYAt1WQm_uuFSPRuAHPcNZ_QUvQIDAQAB";
    	String pro_ld_PublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv7+trX1H1Ey0k6kJgNEvcJgbyUuhgwPOU6LYdCsA8Ba8uN2IsRYNVfXkXDWAzdRePkVIc6pJglAZf2jH3r8bTb6cDSMWfBWDLmxAK4EaEX4lW9skTFhhtcieRG6oyqR2tCQThFesPz1BP2pyNF7FpWOM44ofdhQf1jPYljj/crblJGHM3Dlpx2EGGjlyMwnCsJijMqbSKHPW07dKh4HNvQVD+R3ULw31YS0BmtMl71p6ogaYSG1PwZSmvbQeIAlI//QucjxVMznaehc7nQH6r0FsXa6JOYe3BJtKEm6M0Y/j/uzFhnUOXHCZkJGm/+f/3bnhfnkvv1DikRxxW8M0fQIDAQAB";
    	String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC/v62tfUfUTLSTqQmA0S9wmBvJS6GDA85Toth0KwDwFry43YixFg1V9eRcNYDN1F4+RUhzqkmCUBl/aMfevxtNvpwNIxZ8FYMubEArgRoRfiVb2yRMWGG1yJ5EbqjKpHa0JBOEV6w/PUE/anI0XsWlY4zjih92FB/WM9iWOP9ytuUkYczcOWnHYQYaOXIzCcKwmKMyptIoc9bTt0qHgc29BUP5HdQvDfVhLQGa0yXvWnqiBphIbU/BlKa9tB4gCUj/9C5yPFUzOdp6FzudAfqvQWxdrok5h7cEm0oSbozRj+P+7MWGdQ5ccJmQkab/5//dueF+eS+/UOKRHHFbwzR9AgMBAAECggEBAI7mQX/ess5Dz/uPaTaoLsuhgGu2wopKJtTrspZ4if1jZIf9CWa4AX5Df0k80l4S251astEIYjoMicQLCZE2KRn6aH/d217kBVxubjA9yXip4xueqGGBKGh6N4DWW1KR2KkNjcH+OK70bpDdOnlO+8ry8seMYz/zZXqULuBdX/pTipFrsLJsrF6hkTzSvLRlTS1DuHUcCee9Iq64K4MS3qW+XAxhkWPncdtuFJZTbJtsDVnD5QPHUMueqzpGuyBS3KVDRWT/5gcJlqJCLrYZE7wW57tAogBfUqjDzH1owR8NFUcHSBg70w6SHDRKZPZRTIKO+pfvXPH5w9katbJkB+ECgYEA6pg7NP93AcfG3CWRNUWXTzQjrUfLLwesmoCt5LIhSeflN+oDVTThLkiPpiHkMrQleTTKxbQjsT3aD4VwqDtHSMkVcbBiweSGTtz9+adt2c6G+iNKKioilZlzDrr2SKUi67oOnKplTVnEfArbdZTVT5R9cBc22O45Rra7/UEZ+HkCgYEA0T6iBPapM3ISVMbUtZdjCxlIdN4M5ZCwdDG6Tr+o1ADVgOy/W6hVxHne+by/cmAGbknPc1UcLXoM9i4nI3sxsSwBP8hjxyFlVrTwHgcAojChr7G+i3NKgkE6Xw0ItBdNJZ4svU/WDytp4or57B82IJX+yqv4XHsmHkHHMstn4yUCgYAUEhgmraCZ52StGw20WBLc9OIEhjrs+CQdR9sY0OCmOQ/UW9ITLSYC1rRri0TBzcXS++BJT+Mp29fXMhx0WDX+FS4ffu7EFfAjuSaFgHyWgOPtbK2AtAAnemxsmzRM/MvjDRUI9E8ccgysbCVghu5FE7gzqdjIcIE3+pRU04gMSQKBgQC2bBR0n1cEMv8oSfBIIKOImZJB0RUNTweHRZNX9GAL9G4u6DgsgKXFlTqAH74IyQ7SOCdUNW9Ldoy2U6ZrmIvKGsM8CEh04vimjziH9TLoM93CfwPQL8NVq+hn1cUwvN/VXAFFSsUklITLrL4WzV8zKZHov+TXO8YPuukfr5XywQKBgHQdbSwwgIzbn3YizFl/IsttCXh8h+LPNjm5SlBYotK0v+RgSsXTKJ71LhTk/dh9i88FLc3K82Z77GvRqUwSCx20r57Cv7i8nRfiOexk2uJEAmtBR6P1gwV4YN6mFHIR7uZnZq+I22Io20Y2VRF1aWgtZoWicXuRXTUkf/W1A0mY";
    	  String encrypt = "{encrypt=UVV_qih7lPvoxdtqQJ0oO298_FW9mf1CPWj00bTlUaLEmoBnYqwX_O9e33Eh_MDzVLHpvolx8FbARXMKoAY1esZh2jbWKoXaeXHYr6XOCDiOCJ2NG3Eu5PZf2pW-X877Egn2R88RfvfAXNzuB9LyNy5EFr3qy6pNK0Uyn3muYG3CNkZegHYTUytXQ_ErW-iIn--DHlNjY6ERlUZLuA70m9SpbZ7sa-kdOFkXLrCmdWHaKYOyUERpc_iG04BA_mbG5jSfJf3zqlrmpis0y-4B0kc74VuGejZkKy6jrV_2mDY3mqjrZ88N_ggIaQzwcwqQ3-CbRk6sIIuNJ0wNEbyLIA, sign=d25e98a2fc38e86ad3429051a0a3928b, account=lian2018}";
//    	  String raw = "{\"cardId\":\"bf2e9a813e05fe58d1e\"}";
//    	  String encrypt = "R1odCHb4rD_BJGbkeXICwE3uh69D5KM_ln12oJfm1wBJrAF8vQpqSb4yLjUu7GyImBjjYL9FYR82Suikz2oZ0LW3y_5YucgPLOOb8AMLgk9flKlbZBU8qhai8nlq86-yKLyCkwCA6Eps4iSBL3HthwpJYpE6fU7sSoCxv8643KM";
//    	  String encrypt = privateEncrypt(raw, getPrivateKey(privateKey));
    	  String data;
			data = RSAUtil.publicDecrypt(encrypt, getPublicKey(pro_ld_PublicKey));
    	  System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static RSAPrivateKey defaultPrivateKey(){
		String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAtxhE9tASBOlnYei670qZrx0LAa6F5gkk8JVWxzdOavSrjOTD9X2txjM388oE4aWqvrNQZJFnFGh6izu3FXO-mwIDAQABAkApMD17ZG1ZS4RlQyyiXh7ahOcP9snKaO41bPL7l9Z9OrHZByAzVflr2jf-N01Di82J4gcH5uCcO6Z88OKLpP25AiEA3dl7ZUilwsGdJPbeKZ6ng1Qtz7cUo8TchA4HxkJRH6cCIQDTR5AsvsKdzW0vKZgePOoHBR9Mco4Q7TnsBowJwCEn7QIhAJDWo2RN2MLdKx5t-j-L8GuicsJREi2Voi6pBlRfCZ2zAiEAzzRi6A8ZbIt4JgXD4vvYhJP4cw_x1hXhdWGiWzmrCq0CIH5Zd5rxAIOBUoq0NjORzaPZX9FEhIRkRNOzmtoImaq4";
		RSAPrivateKey rsaPrivateKey;
    	try {
    		rsaPrivateKey = RSAUtil.getPrivateKey(privateKey);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
    	return rsaPrivateKey;
    }
    
    public static RSAPublicKey defaultPublicKey(){
		String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALcYRPbQEgTpZ2Houu9Kma8dCwGuheYJJPCVVsc3Tmr0q4zkw_V9rcYzN_PKBOGlqr6zUGSRZxRoeos7txVzvpsCAwEAAQ";
		RSAPublicKey rsaPublicKey;
    	try {
    		rsaPublicKey = RSAUtil.getPublicKey(publicKey);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
    	return rsaPublicKey;
    }
	
}

