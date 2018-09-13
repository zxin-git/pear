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

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

@Deprecated
public class RSAUtils {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";
    public static final String DEFAULT_TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    
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
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

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
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     * @param data
     * @param publicKey
     * @return
     */

    public static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
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
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()){
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
            resultDatas = out.toByteArray();
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
//        IOUtils.closeQuietly(out);	
        return resultDatas;
    }

    
	
	public static String privateDecrypt(String encrypt, String privateKey){
		return privateDecrypt(DEFAULT_TRANSFORMATION, encrypt, privateKey);
	}
	
	public static String privateDecrypt2(String encrypt, RSAPrivateKey privateKey){
		return privateDecrypt(DEFAULT_TRANSFORMATION, encrypt, Base64.encodeBase64URLSafeString(privateKey.getEncoded()));
	}
	
    /**
     * 私有解密
     * @param transformation
     * @param encrypt
     * @param privateKey
     * @return 解密字符串
     */
    public static String privateDecrypt(String transformation, String encrypt, String privateKey){
    	return doHandler(transformation, encrypt, privateKey, Cipher.DECRYPT_MODE, Cipher.PRIVATE_KEY);
    }
    
    /**
     * 私有加密
     * @param transformation
     * @param data
     * @param privateKey
     * @return 加密字符串
     */
    public static String privateEncrypt(String transformation, String data, String privateKey){
    	return doHandler(transformation, data, privateKey, Cipher.ENCRYPT_MODE, Cipher.PRIVATE_KEY);
    }
    
    /**
     * 加解密处理 Base64编码
     * @param transformation
     * @param data
     * @param key
     * @param mode
     * @param keyType
     * @return 加解密后的字符串
     */
    private static String doHandler(String transformation, String data, String key, int mode, int keyType){
        try{
        	Cipher cipher = Cipher.getInstance(transformation);
            RSAKey rsaKey;
            if(Cipher.PUBLIC_KEY == keyType){
            	rsaKey = getPublicKey(key);
            }else{
            	rsaKey = getPrivateKey(key);
            }
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
    
    
    public static void main(String[] args) {
    	try {
			String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALcYRPbQEgTpZ2Houu9Kma8dCwGuheYJJPCVVsc3Tmr0q4zkw_V9rcYzN_PKBOGlqr6zUGSRZxRoeos7txVzvpsCAwEAAQ";
			String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAtxhE9tASBOlnYei670qZrx0LAa6F5gkk8JVWxzdOavSrjOTD9X2txjM388oE4aWqvrNQZJFnFGh6izu3FXO-mwIDAQABAkApMD17ZG1ZS4RlQyyiXh7ahOcP9snKaO41bPL7l9Z9OrHZByAzVflr2jf-N01Di82J4gcH5uCcO6Z88OKLpP25AiEA3dl7ZUilwsGdJPbeKZ6ng1Qtz7cUo8TchA4HxkJRH6cCIQDTR5AsvsKdzW0vKZgePOoHBR9Mco4Q7TnsBowJwCEn7QIhAJDWo2RN2MLdKx5t-j-L8GuicsJREi2Voi6pBlRfCZ2zAiEAzzRi6A8ZbIt4JgXD4vvYhJP4cw_x1hXhdWGiWzmrCq0CIH5Zd5rxAIOBUoq0NjORzaPZX9FEhIRkRNOzmtoImaq4";
			String original = "{\"customId\": \"mockCustomMark\",\"cardId\": \"mockCard\",\"type\": \"T001,T002,T003,T004\"}";
			
			RSAPrivateKey rsaPrivateKey = RSAUtils.getPrivateKey(privateKey);
			RSAPublicKey rsaPublicKey = RSAUtils.getPublicKey(publicKey);
			
			String encrypt = RSAUtils.publicEncrypt(original, rsaPublicKey);
			String str = RSAUtils.privateDecrypt(encrypt, privateKey);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
    
}
