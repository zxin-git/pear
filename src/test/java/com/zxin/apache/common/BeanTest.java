package com.zxin.apache.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.jdk.node.bean.Job;
import com.zxin.jdk.node.bean.Parent;

public class BeanTest {

	private static Logger logger = LoggerFactory.getLogger(BeanTest.class);
	
	public static void main(String[] args) {
		Parent parent = new Parent();
		parent.setName("name");
		parent.setId(1);
		Job job = new Job();
		job.setId(123);
		job.setAddress("bsd");
		parent.setJob(job);
		
		try {
			Parent a = parent.clone();

			System.out.println(a);
		} catch (CloneNotSupportedException e) {
			logger.debug("",e);
		}
		
//		User a = new User();
//		User b = new User();
//		User a1 = a;
//		User ba = null;
//		try {
//			ba = (User) BeanUtils.cloneBean(a);
//			logger.debug((b==a)+"");
//		} catch (IllegalAccessException e) {
//			logger.debug("",e);
//		} catch (InstantiationException e) {
//			logger.debug("",e);
//		} catch (InvocationTargetException e) {
//			logger.debug("",e);
//		} catch (NoSuchMethodException e) {
//			logger.debug("",e);
//		}
//		System.out.println(a1+""+ba+"");
	}
}

