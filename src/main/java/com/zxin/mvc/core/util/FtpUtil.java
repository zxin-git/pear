/**
 * author:	zxin
 * Description：ftp工具类，利用apache.commons这个包完成访问ftp及下载文件等功能。
 */
package com.zxin.mvc.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

public class FtpUtil {
	private FTPClient ftpClient;
    private String ipAddress;
    private int ipPort;
    private String userName;
    private String passWord;
    private InputStream inStream = null;
    private static final Logger logger = Logger.getLogger(FtpUtil.class);
    /**
     * 构造函数
     * @param ip 机器IP
     * @param port 机器FTP端口号，若端口未配置则使用默认端口21
     * @param userName FTP用户名
     * @param passWord FTP密码
     */
	public FtpUtil(String ip, int port, String userName, String passWord) {
		try {
			this.ipAddress = new String(ip);
			if (port == 0) {
				this.ipPort = 21;
			} else {
				this.ipPort = port;
			}
			ftpClient = new FTPClient();
			this.userName = new String(userName);
			this.passWord = new String(passWord);
			/*设置主动模式*/
			ftpClient.enterLocalActiveMode();
			
			//设置超时时间
			ftpClient.setDefaultTimeout(10 * 1000);
			ftpClient.setConnectTimeout(10 * 1000);
			ftpClient.setDataTimeout(120 * 1000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public FtpUtil(String ip, int port, String userName, String passWord,String charset) {
		new FtpUtil(ip,port,userName,passWord,charset,true);
	}
	/**
     * 构造函数
     * @param ip 机器IP
     * @param port 机器FTP端口号，若端口未配置则使用默认端口21
     * @param userName FTP用户名
     * @param passWord FTP密码
     * @param charset FTP字符集(默认为utf-8)
     * @param isActiveMode true为主动模式,false为被动模式
     */
	public FtpUtil(String ip, int port, String userName, String passWord,String charset,boolean isActiveMode) {
		try {
			this.ipAddress = new String(ip);
			if (port == 0) {
				this.ipPort = 21;
			} else {
				this.ipPort = port;
			}
			ftpClient = new FTPClient();
			if (StringUtils.isBlank(charset))
				charset="utf-8";
			ftpClient.setControlEncoding(charset);
			//设置传输模式
			if (isActiveMode)
				ftpClient.enterLocalActiveMode();
			else 
				ftpClient.enterLocalPassiveMode();
			this.userName = new String(userName);
			this.passWord = new String(passWord);
			
			//设置超时时间
			ftpClient.setDefaultTimeout(10 * 1000);
			ftpClient.setConnectTimeout(10 * 1000);
			ftpClient.setDataTimeout(120 * 1000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.err.println(e);
		}
	}
    
    /**
     * 登录FTP服务器
     */
	public boolean login() {
		boolean isSuccess = false;
		try {
			ftpClient.connect(ipAddress, ipPort);
			isSuccess = ftpClient.login(userName, passWord);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return isSuccess;
	}
    
    /**
     * 退出FTP服务器
     */
	public void logout() {
		try {
			if (ftpClient != null) {
				ftpClient.disconnect();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 判断ftp是否已连接
	 * @return 连接状态
	 */
	public boolean isConnected(){
		return ftpClient.isConnected();
	}
    
    /**
     * 取得某个目录下的所有文件或文件夹列表
	 * @param path 目录名
	 * @param isDirectory true-函数返回文件夹列表；false-函数返回文件列表
	 * @return 文件或文件夹列表
     */
	public List<String> getFileList(String path, boolean isDirectory) {
		List<String> list = new ArrayList<String>();
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			FTPFile[] ftpFiles = ftpClient.listFiles(path);//要默认用绝对路径
			String fileName = "";
			if (ftpFiles != null && ftpFiles.length > 0) {
				if (isDirectory) {
					for (int i = 0; i < ftpFiles.length; i++) {
						System.out.println(ftpFiles[i].getName());
						if (ftpFiles[i].isDirectory()) {
							fileName = ftpFiles[i].getName();
							list.add(fileName);
						}
					}
				} else {
					for (int i = 0; i < ftpFiles.length; i++) {
						System.out.println(ftpFiles[i].getName());
						if (ftpFiles[i].isFile()) {
							fileName = ftpFiles[i].getName();
							list.add(fileName);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	/**
     * 取得某个目录下的所有文件和文件夹的列表
	 * @param path 目录名
	 * @return 文件和文件夹的列表
     */
	public List<String> getFileList(String path) {
		List<String> list = new ArrayList<String>();
		try {
			// 设置以二进制方式传输
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			FTPFile[] ftpFiles = ftpClient.listFiles(path);
			String fileName = "";
			if (ftpFiles != null) {
				for (int i = 0; i < ftpFiles.length; i++) {
					if(ftpFiles[i].getName().equals(".") || ftpFiles[i].getName().equals(".."))
						continue;
					System.out.println(ftpFiles[i].getName());
					fileName = ftpFiles[i].getName();
					list.add(fileName);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.err.println(e);
		}
		return list;
	}
	
    /**
     * 取得某个目录下的文件
	 * @param path 目录
	 * @return 
     */
	public FTPFile[] getFTPFiles(String path) {
		FTPFile[] ftpFiles=null;
		try {
			// 设置以二进制方式传输
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpFiles = ftpClient.listFiles(path);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ftpFiles;
	}
	/**
     * 取得某个目录下修改时间最新的文件
	 * @param path 目录
	 * @return 修改时间最新的两个文件
     */
	public String[] getLastFiles(String path) {
		String[] logFileName =null;
		try {
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			FTPFile[] ftpFiles = ftpClient.listFiles(path);
			if (ftpFiles != null&&ftpFiles.length>0) {
				Arrays.sort(ftpFiles,new Comparator<FTPFile>(){
					public int compare(FTPFile obj1,FTPFile obj2) {
						return obj2.getTimestamp().compareTo(obj1.getTimestamp());
					}
				});
				if (ftpFiles.length>=2){//ftpfiles排序后前两个是"."和".."
					logFileName =new String[2];
					logFileName[0]=ftpFiles[2].getName();
					logFileName[1]=ftpFiles[3].getName();
				}
				else {
					logFileName =new String[2];
					logFileName[0]=ftpFiles[0].getName();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return logFileName;
	}
   /**
    *  download
    *  从ftp下载文件到本地
    * @param fileName 服务器上的文件名
    * @param path 服务器上的文件夹路径
    * @param srcFile 本地存储路径
    * @return 文件大小
    */
	public void download(String fileName, String path, String srcFile) {	//dir need '/' end  eg: /tmp/
		FileOutputStream fos = null;
		try {
			String dstFileName = path;
			ftpClient.changeWorkingDirectory(dstFileName.toString());
						
			fos = new FileOutputStream(srcFile);
			ftpClient.setBufferSize(1024);
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.retrieveFile(fileName, fos);
			
			fos.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally{
			if(fos != null){
				try {
					if(ftpClient != null){
						ftpClient.changeWorkingDirectory(File.separator);
					}
				} catch(Exception e){
					logger.error(e.getMessage(), e);
				}
				try {
					fos.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	/**
    *  upload
    *  从本地上传到ftp服务器上
    * @param srcDirectory 本地文件夹路径
    * @param dstDirectory 服务器上的文件夹路径
    */
	public void uploadDirectory(String srcDirectory, String dstDirectory){
		File file=new File(srcDirectory);
		File[] files=file.listFiles();
		FileInputStream fis=null;
		String fileName=null;
		try {
			ftpClient.setBufferSize(1024);
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			dstDirectory=new String(dstDirectory.getBytes("GBK"),"iso-8859-1");
			//判断远程目录是否存在,不存在则创建
			boolean flag=ftpClient.changeWorkingDirectory(dstDirectory);
			if (!flag) {
				ftpClient.makeDirectory(dstDirectory);
				ftpClient.changeWorkingDirectory(dstDirectory);
			}
			for (File srcFile : files) {
				fileName=srcFile.getName();
				if (srcFile.isDirectory()) {
					uploadDirectory(srcFile.getPath(),dstDirectory+"/"+srcFile.getName());
				} else {
					try {
						fis=new FileInputStream(srcFile);
						fileName = new String(srcFile.getName().getBytes("GBK"),"iso-8859-1");
						ftpClient.storeFile(fileName, fis);
					}finally{
						try {
							if (fis!=null) {
								fis.close();
								fis=null;
							}
						} catch (IOException e) {
							logger.error(e.getMessage(),e);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(),e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void uploadFile(String srcFile, String dstDirectory){
		FileInputStream fis=null;
		try {
			ftpClient.setBufferSize(1024);
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			//判断远程目录是否存在,不存在则创建
			boolean flag=ftpClient.changeWorkingDirectory(dstDirectory);
			if (!flag) {
				ftpClient.makeDirectory(dstDirectory);
				ftpClient.changeWorkingDirectory(dstDirectory);
			}
			File file=new File(srcFile);
			fis=new FileInputStream(srcFile);
			String fileName = new String(file.getName().getBytes("GBK"),"iso-8859-1");
			ftpClient.storeFile(fileName, fis);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(),e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}finally{
			try {
				if (fis!=null) {
					fis.close();
					fis=null;
				}
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
	}
	public void deleteFile(String filePath){
		try {
			this.getFtpClient().deleteFile(filePath);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void deleteFile(List<String> paths){
		for(String filePath : paths){
			this.deleteFile(filePath);
		}
	}

	/**
	 * 获得文件的InputStream
	 * @param filePath 需要读取的文件
	 * @return InputStream
	 */
	public InputStream getInputStream(String filePath){
		try{
			// 设置以二进制方式传输
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			inStream = ftpClient.retrieveFileStream(filePath);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return inStream;
	}
	
	/**
	 * 关闭InputStream
	 */
	public void closeInputStream() {
		try {
			if (inStream != null) {
				inStream.close();
				inStream = null;
				ftpClient.completePendingCommand();
			}	
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public boolean changeDirectory(String directory) {
		try {
			return ftpClient.changeWorkingDirectory(directory);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	public boolean makeDirectory(String directory) {
		try {
			return ftpClient.makeDirectory(directory);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}
}
