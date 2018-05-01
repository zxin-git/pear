package com.zxin.jdk.test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyUtil {

	//// 工具类中的方法都是静态方式访问的因此将构造器私有不允许创建对象(绝对好习惯)
	private CopyUtil() {
	}

	public static void fileCopy(String source, String target) throws IOException {
		try (InputStream in = new FileInputStream(source)) {
			try (OutputStream out = new FileOutputStream(target)) {
				byte[] buffer = new byte[4096];
				int bytesToRead;
				while ((bytesToRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesToRead);
				}
			}
		}
	}

	public static void fileCopyNIO(String source, String target) throws IOException {
		try (FileInputStream in = new FileInputStream(source)) {
			try (FileOutputStream out = new FileOutputStream(target)) {
				FileChannel inChannel = in.getChannel();
				FileChannel outChannel = out.getChannel();
				ByteBuffer buffer = ByteBuffer.allocate(4096);
				while (inChannel.read(buffer) != -1) {
					buffer.flip();
					outChannel.write(buffer);
					buffer.clear();
				}
			}
		}
	}

	public static void main(String[] args) {
		// 获取你当前的路径

		File f = new File(CopyUtil.class.getClass().getResource("/").getPath());
		System.out.println(f);

		try {
			CopyUtil.fileCopyNIO("e:/a","e:/b");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}