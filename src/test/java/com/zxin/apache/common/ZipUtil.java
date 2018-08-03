package com.zxin.apache.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

/**
 * @author
 * @deprecated {@link ZipFileUtil}
 */
public class ZipUtil {
	
	public static String encoding = "utf-8";

	public static int BUF = 2048;

	public static void zip(File root, String path) throws ArchiveException,IOException {
		File[] files = root.listFiles();
		ZipArchiveOutputStream zos = (ZipArchiveOutputStream) new ArchiveStreamFactory()
				.createArchiveOutputStream("zip", new FileOutputStream(path)); // or
		zos.setEncoding(encoding);
		String rootPath = root.getAbsolutePath(); // 获取要压缩文件根路径
		try {
			zip(zos, files, rootPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		zos.close();
	}

	/**
	 * 压缩多个文件
	 * @param files 文件数组
	 * @param rootPath 压缩后的路径
	 * @throws Exception
	 */
	public static void zip(File[] files,String rootPath) throws Exception {
		ZipArchiveOutputStream zos = (ZipArchiveOutputStream) new ArchiveStreamFactory()
		.createArchiveOutputStream("zip", new FileOutputStream(rootPath)); 
		zos.setEncoding(encoding);
		try {
			String newPath = rootPath.replace("/", File.separator);
			zip(zos, files, newPath.substring(0, newPath.lastIndexOf(File.separator)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		zos.close();
	}
	
	private static void zip(ZipArchiveOutputStream zos, File[] files,String rootPath) throws Exception {
		for (File f : files) {
			if (!f.exists())
				continue;

			ZipArchiveEntry ze = new ZipArchiveEntry(getEntryName(f, rootPath));// 获取每个文件相对路径,作为在ZIP中路径
			zos.putArchiveEntry(ze);
			// folder
			if (ze.isDirectory()) {
				File[] fs = f.listFiles();
				zip(zos, fs, rootPath);
				// zos.closeArchiveEntry();
				continue;
			}
			// file
			FileInputStream fis = new FileInputStream(f);
			IOUtils.copy(fis, zos, BUF);
			fis.close();
			zos.closeArchiveEntry();
		}
	}
	private static String getEntryName(File f, String rootPath) {
		String entryName;
		String fPath = f.getAbsolutePath();
		if (fPath.indexOf(rootPath) != -1)
			entryName = fPath.substring(rootPath.length() + 1);
		else
			entryName = f.getName();

		if (f.isDirectory())
			entryName += "/";// "/"后缀表示entry是文件夹
		return entryName;
	}

	public static void unZip(String sPath, String tPath) throws IOException,
			ArchiveException {
		ZipFile file = new ZipFile(sPath, encoding);
		Enumeration<ZipArchiveEntry> en = file.getEntries();
		ZipArchiveEntry ze;
		while (en.hasMoreElements()) {
			ze = en.nextElement();
			File f = new File(tPath, ze.getName());
			if (ze.isDirectory()) {
				f.mkdirs();
				continue;
			} else
				f.getParentFile().mkdirs();
			InputStream is = file.getInputStream(ze);
			OutputStream os = new FileOutputStream(f);
			IOUtils.copy(is, os, BUF);
			is.close();
			os.close();
		}
		file.close();
	}
}
