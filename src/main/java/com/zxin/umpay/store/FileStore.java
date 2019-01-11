package com.zxin.umpay.store;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStore extends AbstractStore {

	private static final Logger logger = LoggerFactory.getLogger(FileStore.class);

	private File file;
	
	private FileWriter writer;
	
	public FileStore(String str){
		this(new File(str));
	}
	
	public FileStore(File file) {
//		super();
		this.file = file;
		try {
			if (file.isDirectory()) {
				throw new RuntimeException("存储文件，不能为目录！！！");
			}else if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
		} catch (Exception e) {
			logger.warn("",e);
		}
	}
	
	@Override
	public void write() {
		try {
			String data = storageQueue.take();
			getWriter().write(data);
			getWriter().flush();
		} catch (InterruptedException | IOException e) {
			logger.debug("",e);
		}
		
	}
	
	@Override
	public void run() {
		while (running.get()) {
			write();
		}
	}
	
	
	public File getFile(){
		return file;
	}
	
	public FileWriter getWriter(){
		if(writer==null){
			synchronized(this){
				if(writer==null){
					try {
						writer = new FileWriter(file);
					} catch (IOException e) {
						logger.error("",e);
						throw new RuntimeException(e);
					}
				}
			}
		}
		
		return writer;
	}

	@Override
	public void handler(String data) throws Exception {
	}

	@Override
	public void process(String data) throws Exception {
	}

	
	
}

