package com.zxin.jdk.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class ZipUtils 
{
	private static final int BUFFER = 1024;
	static final boolean first = true;
	/**
	 * 文件 解压缩
	 * 
	 * @param srcPath
	 *            源文件路径
	 * 
	 * @throws Exception
	 */
	public static void decompress(String srcPath) throws Exception
	{
		File srcFile = new File(srcPath);
		decompress(srcFile);
	}

	/**
	 * 解压缩
	 * 
	 * @param srcFile
	 * @throws Exception
	 */
	public static void decompress(File srcFile) throws Exception 
	{
		String basePath = srcFile.getParent();
		decompress(srcFile, basePath);
	}

	/**
	 * 解压缩
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws Exception
	 */
	public static void decompress(File srcFile, File destFile) throws Exception 
	{
		CheckedInputStream cis = new CheckedInputStream(new FileInputStream(srcFile), new CRC32());
		ZipInputStream zis = new ZipInputStream(cis);
		decompress(destFile, zis);
		zis.close();
	}

	/**
	 * 解压缩
	 * 
	 * @param srcFile
	 * @param destPath
	 * @throws Exception
	 */
	public static void decompress(File srcFile, String destPath)throws Exception
	{
		decompress(srcFile, new File(destPath));
	}

	/**
	 * 文件 解压缩
	 * 
	 * @param srcPath
	 *            源文件路径
	 * @param destPath
	 *            目标文件路径
	 * @throws Exception
	 */
	public static void decompress(String srcPath, String destPath)throws Exception 
	{
		File srcFile = new File(srcPath);
		decompress(srcFile, destPath);
	}

	/**
	 * 文件 解压缩
	 * 
	 * @param destFile
	 *            目标文件
	 * @param zis
	 *            ZipInputStream
	 * @throws Exception
	 */
	private static void decompress(File destFile, ZipInputStream zis)throws Exception
	{
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null)
		{
			// 文件
			String dir = destFile.getPath() + File.separator + entry.getName();
			File dirFile = new File(dir);
			// 文件核查
			fileProber(dirFile);
 			if (entry.isDirectory()) 
 			{
 				dirFile.mkdirs();
 			} else 
 			{
 				decompressFile(dirFile, zis);
 			}
 			zis.closeEntry();
 		}
 	}

	/**
	 * 文件探针
	 * 
	 * 
	 * 当父目录不存在时，创建目录！
	 * 
	 * 
	 * @param dirFile
	 */
	private static void fileProber(File dirFile) 
	{
		File parentFile = dirFile.getParentFile();
		if (!parentFile.exists()) 
		{
			// 递归寻找上级目录
			fileProber(parentFile);
			parentFile.mkdir();
		}

	}

	/**
	 * 文件解压缩
	 * 
	 * @param destFile
	 *            目标文件
	 * @param zis
	 *            ZipInputStream
	 * @throws Exception
	 */
	private static void decompressFile(File destFile, ZipInputStream zis)throws Exception 
	{
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
		int count;
		byte data[] = new byte[BUFFER];
		while ((count = zis.read(data, 0, BUFFER)) != -1) 
		{
			bos.write(data, 0, count);
		}
		bos.close();
	}
	
	
	public static void compress(String srcPathName,String descPathZipName) 
	{
		File file = new File(srcPathName);
		if (!file.exists())
			throw new RuntimeException(srcPathName + "不存在！");
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(descPathZipName);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			String basedir = "";
			compress(file, out, basedir);
			out.close();
		} catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}

	private static void compress(File file, ZipOutputStream out, String basedir) 
	{
		/* 判断是目录还是文件 */
		if (file.isDirectory()) 
		{
			System.out.println("压缩：" + basedir + file.getName());
			compressDirectory(file, out, basedir);
		} else {
			System.out.println("压缩：" + basedir + file.getName());
			compressFile(file, out, basedir);
		}
	}

	/** 压缩一个目录 */
	private static void compressDirectory(File dir, ZipOutputStream out, String basedir) 
	{
		if (!dir.exists())
			return;
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/** 压缩一个文件 */
	private static void compressFile(File file, ZipOutputStream out, String basedir) 
	{
		if (!file.exists()) 
		{
			System.err.println(file.getPath()+"不存在！");
			return;
		}
		try
		{
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			String zePath=basedir.replaceFirst("^\\S*?/", "");
			ZipEntry entry = new ZipEntry(zePath + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String args[]) throws Exception
	{
//		ZipUtils.decompress("f:\\你好\\cvs.zip", "f:\\你好\\cvsunzip");
		ZipUtils.compress("E:\\logs","E:\\logs.zip");
	}
}
