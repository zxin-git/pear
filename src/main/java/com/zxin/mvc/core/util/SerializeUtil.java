package com.zxin.mvc.core.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * 序列化工具
 * <p>
 * 依赖于xstream类库
 * </p>
 * 
 */
public final class SerializeUtil {
	
	private static Logger logger = Logger.getLogger(SerializeUtil.class);

	private static XStream xstream = new XStream();
	private static XStream jsonXstream = new XStream(new JettisonMappedXmlDriver());
	
	private final static SerializeUtil instance = new SerializeUtil();
	
	private SerializeUtil() {
	}

	/**
	 * 获取序列化工具实例
	 * @return
	 */
	public static SerializeUtil getInstance(){
		return instance;
	}
	
	/**
	 * 获取序列化工具实例，同时注册类
	 * @param clazz
	 * @return
	 */
	public static SerializeUtil getInstance(Class<?>... clazz) {
		if (clazz != null)
			for (Class<?> cls : clazz)
				registerClass(cls);
		return instance;
	}

	/**
	 * 获取序列化工具实例，同时使用指定的名称注册类
	 * @param name
	 * @param clazz
	 * @return
	 */
	public static SerializeUtil getInstance(String name, Class<?>... clazz) {
		if (clazz != null)
			for (Class<?> cls : clazz)
				registerClass(name, cls);
		return instance;
	}

	/**
	 * 注册类
	 * @param clazz
	 */
	public static void registerClass(Class<?> clazz) {
		xstream.alias(clazz.getSimpleName(), clazz);
		jsonXstream.alias(clazz.getSimpleName(), clazz);
	}

	/**
	 * 使用指定的名称注册类
	 * @param name
	 * @param clazz
	 */
	public static void registerClass(String name, Class<?> clazz) {
		xstream.alias(name, clazz);
		jsonXstream.alias(name, clazz);
	}

	/**
	 * 将对象序列化为XML字符串，并写入输出流
	 * @param object 要序列化的对象实例
	 * @param out 输出流
	 */
	public void toXml(Object object, OutputStream out) {
		xstream.toXML(object, out);
	}

	/**
	 * 将对象序列化为XML字符串，并写入字符流writer
	 * @param object 要序列化的对象实例
	 * @param writer 字符流writer
	 */
	public void toXml(Object object, Writer writer) {
		xstream.toXML(object, writer);
	}

	/**
	 * 将对象序列化为XML字符串
	 * @param object 要序列化的对象实例
	 * @return 序列化后的XML字符串
	 */
	public String toXml(Object object) {
		return xstream.toXML(object);
	}
	
	/**
	 * 将对象序列化为JSON字符串
	 * @param object 要序列化的对象实例
	 * @return 序列化后的JSON字符串
	 */
	public String toJson(Object object) {
		return jsonXstream.toXML(object);
	}

	/**
	 * 将XML字符串反序列化为对象实例
	 * @param xml XML字符串
	 * @return
	 */
	public Object fromXml(String xml) {
		if (xml.contains("\\n")) {//added by peizl 2012-04-17 在oracle数据库中获取的数据带有\n和\"的处理
			xml = xml.replace("\\n", "");
		}
		if (xml.contains("\\\"")) {
			xml = xml.replace("\\\"", "\"");
		}
		return xstream.fromXML(xml);
	}

	/**
	 * 从输入流中读取XML字符串，并反序列化为对象实例
	 * @param in 输入流
	 * @return
	 */
	public Object fromXml(InputStream in) {
		Object obj = null;
		try {
			obj = xstream.fromXML(in);
		} catch (Throwable t) {
			logger.error(t);
		}
		return obj;
	}

	/**
	 * 从字符流reader中读取XML字符串，并反序列化为对象实例
	 * @param r 字符流reader
	 * @return
	 */
	public Object fromXml(Reader r) {
		Object obj = null;
		try {
			obj = xstream.fromXML(r);
		} catch (Throwable t) {
			logger.error(t);
		}
		return obj;
	}

	/**
	 * 将JSON字符串反序列化为对象实例
	 * @param json JSON字符串
	 * @return
	 */
	public Object fromJson(String json) {
		Object obj = null;
		try {
			obj = jsonXstream.fromXML(json);
		} catch (Throwable t) {
			logger.error(t);
		}
		return obj;
	}
}
