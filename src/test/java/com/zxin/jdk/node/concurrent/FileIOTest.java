package com.zxin.jdk.node.concurrent;

import java.io.File;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.util.PropertiesUtil;

public class FileIOTest {

	private static Logger logger = LoggerFactory.getLogger(FileIOTest.class);
	
	public static void main(String[] args) {
		File file = new File("d:/a.txt");
		for (int i = 0; i < 50; i++) {
			ExecutorUtils.executor.submit(new Runnable() {
				@Override
				public void run() {
					try {
						Properties prop = PropertiesUtil.getProperties("beetl.properties");
						for (Entry<Object, Object> obj : prop.entrySet()) {
							logger.info(prop.get(obj.getKey())+"");
						}
						prop.put(1, 2);
					} catch (Exception e) {
						logger.info("ssss",e);
					}
				}
			});
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				logger.debug("",e);
			}
		}
	}
	
}

