package com.zxin.mvc.core.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zhanghang
 *
 */
public class CmdExcutor {
	public static enum OS {windows, linux};
	
	public static OS platform = System.getProperty("os.name").toLowerCase().indexOf("windows") > -1 ? OS.windows : OS.linux;
	
	public static void main(String[] args) {
		System.out.println(excutor("dir"));
//		System.out.println(excutor("mstsc D://164xp.rdp"));
//		System.out.println(excutor("dir /b findstr \"C:\\Program Files\\Microsoft SQL Server\\MSSQL*\" >tempfile\n set /p T=<tempfile\necho %T%\nset regpath=\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Microsoft SQL Server\\%T%\\MSSQLServer\\SuperSocketNetLib\" /v ForceEncryption\nreg query %regpath%"));
//		System.out.println(excutor("dir /b findstr \"D:\\program\\Microso*\" >tempfile\n type tempfile"));
//		System.out.println(excutor("dir /b \"D:\" >tempfile&type tempfile |findstr \"mariadb5*\" >tempfile1&type tempfile1"));
//		System.out.println(excutor(args[0]));
	}
	
	public static String excutor(String cmd){
		String charset = "gbk";
		return excutor(cmd, charset);
	}
	
	public static String excutor(String cmd, String charset){
		Process process = null;
		StreamGobbler errorGobbler = null;
		StreamGobbler outputGobbler = null;
		try{
			if (platform == OS.windows) {
				process = Runtime.getRuntime().exec("cmd /c " + cmd);
//				process = Runtime.getRuntime().exec(cmd);
			} else {
				String[] cmds = { "/bin/sh", "-c", cmd };
				process = Runtime.getRuntime().exec(cmds);
			}
			
			process.getOutputStream().close();
			
			errorGobbler = new StreamGobbler(process.getErrorStream(), "" + System.currentTimeMillis(), charset);
			outputGobbler = new StreamGobbler(process.getInputStream(), "" + System.currentTimeMillis(), charset);
			errorGobbler.start();
			outputGobbler.start();
			
			process.waitFor();
			
			System.out.println(errorGobbler.getResult());
			
			return outputGobbler.getResult();
		}catch (Exception e) {
			System.out.println("excute cmd error:" + e.getMessage());
			return errorGobbler.getResult();
		} finally{
			if(process!=null){
				try{
					process.destroy();
				}catch(Exception e){}
			}
		}
	}
}

class StreamGobbler extends Thread {
	
	InputStream is;
	String id;
	String charset;
	
	StringBuilder sb;

	public String getResult() {
		if(sb != null){
			return sb.toString();
		}
		return null;
	}

	StreamGobbler(InputStream is, String id, String charset) {
		this.is = is;
		this.id = id;
		this.charset = charset;
		if(this.charset == null){
			this.charset = "utf-8";
		}
		this.setDaemon(true);
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is, charset);
//			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
//				if("".equals(line)){
//					continue ;
//				}
				if(sb == null){
					 sb = new StringBuilder();
				}
				sb.append(line).append("\n");
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}