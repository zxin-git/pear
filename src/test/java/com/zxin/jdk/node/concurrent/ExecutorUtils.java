package com.zxin.jdk.node.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutorUtils {

	private static Logger logger = LoggerFactory.getLogger(ExecutorUtils.class);
	
	public static ExecutorService executor = Executors.newFixedThreadPool(20);
}

