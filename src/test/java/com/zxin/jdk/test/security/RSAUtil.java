package com.zxin.jdk.test.security;

import java.io.ByteArrayOutputStream;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

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
    
    
    public static void main(String[] args) {
    	try {
			String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALcYRPbQEgTpZ2Houu9Kma8dCwGuheYJJPCVVsc3Tmr0q4zkw_V9rcYzN_PKBOGlqr6zUGSRZxRoeos7txVzvpsCAwEAAQ";
			String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAtxhE9tASBOlnYei670qZrx0LAa6F5gkk8JVWxzdOavSrjOTD9X2txjM388oE4aWqvrNQZJFnFGh6izu3FXO-mwIDAQABAkApMD17ZG1ZS4RlQyyiXh7ahOcP9snKaO41bPL7l9Z9OrHZByAzVflr2jf-N01Di82J4gcH5uCcO6Z88OKLpP25AiEA3dl7ZUilwsGdJPbeKZ6ng1Qtz7cUo8TchA4HxkJRH6cCIQDTR5AsvsKdzW0vKZgePOoHBR9Mco4Q7TnsBowJwCEn7QIhAJDWo2RN2MLdKx5t-j-L8GuicsJREi2Voi6pBlRfCZ2zAiEAzzRi6A8ZbIt4JgXD4vvYhJP4cw_x1hXhdWGiWzmrCq0CIH5Zd5rxAIOBUoq0NjORzaPZX9FEhIRkRNOzmtoImaq4";
			String original = "{\"customId\": \"mockCustomMark\",\"cardId\": \"mockCard\",\"type\": \"T001,T002,T003,T004\"}";
			
			final RSAPrivateKey rsaPrivateKey = RSAUtil.getPrivateKey(privateKey);
			RSAPublicKey rsaPublicKey = RSAUtil.getPublicKey(publicKey);
			
			final String encrypt = RSAUtil.publicEncrypt(original, rsaPublicKey);
//			String str = RSAUtil.privateDecrypt(encrypt, rsaPrivateKey);
//			System.out.println(str);
			
			ExecutorService exService = Executors.newFixedThreadPool(10);
			for(int i=0;i<100;i++){
				final int a = i;
				exService.execute(new Runnable() {
					@Override
					public void run() {
						String str = RSAUtil.privateDecrypt(encrypt, rsaPrivateKey);
						System.out.println(a+"\t"+str);
					}
				});
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
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

