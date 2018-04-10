package com.zxin.mvc.core.util.duplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * 对象流复制工具
 */
public class StreamDuplicateUtil implements IDuplicateUtil
{
	private static final Logger logger = Logger.getLogger(StreamDuplicateUtil.class);
	
	private static final StreamDuplicateUtil util = new StreamDuplicateUtil();
	
	private StreamDuplicateUtil() {}
	
	public static final IDuplicateUtil getInstance()
	{
		return util;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T duplicate(T o)
	{
		T newObj = null;
		if (!(o instanceof Serializable))
			return newObj;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			newObj = (T)ois.readObject();
		}
		catch (Exception e)
		{//IOException, ClassNotFoundException
			logger.warn("StreamDuplicateUtil.duplicate("+o.getClass().getName()+") error: ", e);
		}
		
		return newObj;
	}
}

