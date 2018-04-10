/**
 * 
 */
package com.zxin.mvc.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化流 与 文件之间转换
 * @author zhanghang
 */
public class ObjectFileOper {

	public static void write(String filePath, Object obj) {
		File file = new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(oos != null){
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Object read(String filePath) {
		return read(new File(filePath));
	}

	public static Object read(File file) {
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			obj = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ois != null){
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

}
