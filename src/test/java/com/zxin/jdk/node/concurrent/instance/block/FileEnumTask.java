package com.zxin.jdk.node.concurrent.instance.block;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class FileEnumTask implements Runnable{

	public static File DUMMY = new File("");
	private BlockingQueue<File> queue;
	private File startingDir;
	
	public FileEnumTask(BlockingQueue<File> queue,File dir) {
		this.queue = queue;
		this.startingDir = dir;
	}
	
	@Override
	public void run() {
		try {
			enumerate(startingDir);
			queue.put(DUMMY);
		} catch (Exception e) {
			
		}
	}
	
	public void enumerate(File dir) throws InterruptedException {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				enumerate(files[i]);
			}else{
				queue.put(files[i]);
			}
		}
	}
}
