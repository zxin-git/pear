package com.zxin.jdk.node.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.nutz.json.Json;

import com.zxin.jdk.design.single.Singleton;
import com.zxin.jdk.design.single.SingletonEnum;


public class SerialTest {

	
	public static void main(String[] args) {
//		write();
//		read();
//		Singleton.INSTANCE.setId(1);
//		Singleton.INSTANCE.setUsername("ss");
//		Singleton singleton = Singleton.INSTANCE;
//		write(singleton);
		try {
			FileInputStream fi	= new FileInputStream("e:/a");
			ObjectInputStream oi = new ObjectInputStream(fi);
			Object clone = oi.readObject();
//			System.out.println(clone.equals(singleton));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void write(Object obj) {
		try {
//			User obj = new User();
//			obj.setId(1);
//			obj.setPassword(123);
//			obj.setName("name");
//			obj.getMap().put("sss", 2);
			FileOutputStream fo = new FileOutputStream("e:/a");
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(obj);
			oo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void read(){
		try {
			FileInputStream fi	= new FileInputStream("e:/a");
			ObjectInputStream oi = new ObjectInputStream(fi);
			Object clone = oi.readObject();
			System.out.println(Json.toJson(clone));
			oi.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void byteClone(){
		try {
			User obj = new User();
			obj.setId(1);
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			oo.close();
			
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			User clone = (User) oi.readObject();
			System.out.println(Json.toJson(clone));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
