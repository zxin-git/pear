package com.zxin.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JsonUtil {
	private static ObjectMapper jsonmapper = new ObjectMapper();
	
	private static XmlMapper xmlMapper = new XmlMapper();

	/**
	 * 将json串转换为指定的java对象 并创建该对象的实例进行返回
	 * @param jsonString  要转换的json字符串
	 * @param classOft    要转换成的对象的类
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws Exception
	 */
	public static <T> T jsonString2Obj(String jsonString, Class<T> classOft) throws JsonParseException, JsonMappingException, IOException {
		T t = jsonmapper.readValue(jsonString, classOft);
		return t;
	}
	
	/**
	 * 将json串转换为指定的java对象 并创建该对象的集合进行返回
	 * @param src
	 * @param collectionClass
	 * @param valueType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jacksonToCollection(String src,Class<?> collectionClass, Class<?>... valueType)  
            throws Exception {  
        JavaType javaType= jsonmapper.getTypeFactory().constructParametricType(collectionClass, valueType);   
        return (T)jsonmapper.readValue(src, javaType);  
    }  
	
	/**
	 * 将java对象转换为json字符串
	 * @param t  java对象
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> String obj2json(T t) throws JsonGenerationException, JsonMappingException, IOException{
		return jsonmapper.writeValueAsString(t);
	}

	
	public static <T> String obj2Xml(T t) throws JsonProcessingException{
		return xmlMapper.writeValueAsString(t);
		
	}
}