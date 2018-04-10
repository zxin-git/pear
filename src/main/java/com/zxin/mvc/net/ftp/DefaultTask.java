package com.zxin.mvc.net.ftp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.nutz.json.Json;
import org.nutz.lang.Files;

import com.zxin.mvc.core.util.FtpUtil;

public class DefaultTask implements Runnable{
	
	private String name;
	
	private String filePath;
	
	private boolean running = true;

	
	@Override
	public void run() {
		while(running){
			FtpUtil ftpUtil = new FtpUtil("127.0.0.1", 21, "admin", "123456","GBK",true);
			ftpUtil.login();
			if(ftpUtil.isConnected()){
//				System.out.println(Json.toJson(ftpUtil.getFileList("/")));
				InputStream in = ftpUtil.getInputStream(filePath);
				
				File file = new File("E:\\FTP\\tmp\\z.pdf");
				try {
					if(!file.exists())
						Files.createNewFile(file);
					
					OutputStream out = new FileOutputStream(file);
					IOUtils.copy(in, out);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
			running = false;
		}		
	}

	
	public DefaultTask(){
		
	}

	public DefaultTask(String filePath){
		this.filePath = filePath;
		this.name = "Thread-"+filePath;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public boolean isRunning() {
		return running;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}


	@Override
	public String toString() {
		return "DefaultTask [name=" + name + ", filePath=" + filePath + "]";
	}

	
}
