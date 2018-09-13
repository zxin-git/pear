package com.zxin.jdk.node.enums;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleEnumTest {

	private static Logger logger = LoggerFactory.getLogger(SingleEnumTest.class);
		
	public static void main(String[] args) {
		try {
			Constructor[] constructors = SingleEnum.class.getDeclaredConstructors();
			constructors[0].setAccessible(true);
			System.out.println(constructors[0].newInstance("s",1));
			System.out.println(constructors);
//			Constructor<SingleEnum> constructor = SingleEnum.class.getDeclaredConstructor(int.class);
//			constructor.setAccessible(true);
//			System.out.println(constructor.newInstance());
//			Method[] methods = SingleEnum.Mine.class.getDeclaredMethods();
//			for (Method method : methods) {
//				System.out.println(method.getName());
//			}
//			
//			Method m =  SingleEnum.Mine.class.getDeclaredMethod("");
//			System.out.println(m);
			
//			for (Constructor c : constructors) {
//				System.out.println(c.getModifiers());
//			}
//			SingleEnum instance = constructor.newInstance();
//			instance.toString();

		} catch (Exception e) {
			logger.debug("",e);
		}
	}
}

