package com.zxin.umpay.batch;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.umpay.handler.IHandler;
import com.zxin.umpay.handler.Status;
import com.zxin.umpay.handler.StatusHandler;

public class ScanUtil {

	private static final Logger logger = LoggerFactory.getLogger(ScanUtil.class);
	
	private static final Map<IHandler, String> firstLineMap = Collections.synchronizedMap(new WeakHashMap<>());
	
	public static final String SEPARATOR = ","; 

//	private static final ConcurrentHashMap<IHandler, String[]> CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();
	
	/**
	 * 第二行开始跑批
	 * @param file
	 * @param handler
	 */
	public static void scan(File file, IHandler handler){
		try (Scanner scanner = new Scanner(file);) {
			String firstLine = scanner.nextLine();
			firstLineMap.put(handler, firstLine);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				handler.handler(line);
			}
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
	
	public static void scan(File file, StatusHandler statusHandler){
		try (Scanner scanner = new Scanner(file);) {
			String firstLine = scanner.nextLine();
			statusHandler.setFirstLine(firstLine);
			firstLineMap.put(statusHandler, firstLine);
			statusHandler.setStatus(Status.SCANNING);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				statusHandler.handler(line);
				statusHandler.getCount().incrementAndGet();
			}
			statusHandler.setStatus(Status.SCANNED);
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
	
	public static String getFirstLine(IHandler handler){
		return firstLineMap.get(handler);
	}
	
}

