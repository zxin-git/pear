package com.zxin.umpay.batch;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheUtil {

	public static final BlockingQueue<String> BLOCKING_QUEUE = new LinkedBlockingQueue<>();

}

