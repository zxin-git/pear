package com.zxin.jdk.node.concurrent.instance.block;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SearchTask implements Runnable{

	private BlockingQueue<File> queue;
	private String keyword;
	private AtomicInteger i = new AtomicInteger(0);
	
	public SearchTask(BlockingQueue<File> queue,String keyword) {
		this.keyword=keyword;
		this.queue=queue;
	}
	
	@Override
	public void run() {
		try {
			boolean done = false;
			while(!done){
				File file = queue.take();
				if(file == FileEnumTask.DUMMY){
					queue.put(file);
					done = true;
				}else{
					search(file);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void search(File file) throws FileNotFoundException {
		
		try(Scanner in = new Scanner(file)){
			int lineNumber = 0;
			while(in.hasNextLine()){
				lineNumber++;
				String line = in.nextLine();
				if (line.contains(keyword)) {
					System.out.print(i.incrementAndGet()+"\t");
					System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
				}
			}
		}
	}
}
