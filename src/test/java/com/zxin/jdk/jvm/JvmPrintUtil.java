/**
 * 
 */
package com.zxin.jdk.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zxin
 *
 */
public class JvmPrintUtil {

	private static final Logger logger = LoggerFactory.getLogger(JvmPrintUtil.class);
	
	public static void menoryStatus(){
		System.out.println("================内存状态===========================");
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		//获取堆内存使用情况，包括初始大小，最大大小，已使用大小等，单位字节
		System.out.println(memoryMXBean.getHeapMemoryUsage().toString());
		//获取堆外内存使用情况。
		System.out.println(memoryMXBean.getNonHeapMemoryUsage().toString());
	}

	
	public static void dumpStatus(){
		System.out.println("================堆内存状态======================");
		//这里会返回老年代，新生代等内存区的使用情况，按需自取就好
		List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
		memoryPoolMXBeans.forEach((pool) -> {
		    System.out.println(pool.getName());
		    System.out.println(pool.getUsage());
		});
	}
	
	public static void nodump(){
		List<MemoryManagerMXBean> list = ManagementFactory.getMemoryManagerMXBeans();
		list.forEach((bean) ->{
			logger.info(bean.getName());
			logger.info(Arrays.toString(bean.getMemoryPoolNames()));
		});
	}
	
	public static void main(String[] args) {
//		menoryStatus();
//		dumpStatus();
		nodump();
	}

}
