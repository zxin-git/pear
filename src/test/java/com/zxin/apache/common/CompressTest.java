package com.zxin.apache.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompressTest {

	private static Logger logger = LoggerFactory.getLogger(CompressTest.class);
	
	public static void zip(String input, String output, String name) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(output));
        String[] paths = input.split("\\|");
        File[] files = new File[paths.length];
        byte[] buffer = new byte[1024];
        for (int i = 0; i < paths.length; i++) {
            files[i] = new File(paths[i]);
        }
        for (int i = 0; i < files.length; i++) {
            FileInputStream fis = new FileInputStream(files[i]);
            if (files.length == 1 && name != null) {
                out.putNextEntry(new ZipEntry(name));
            } else {
                out.putNextEntry(new ZipEntry(files[i].getName()));
            }
            int len;
            // 读入需要下载的文件的内容，打包到zip文件
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.closeEntry();
            fis.close();
        }
        out.close();
    }
	
	
	public static void gzip(String input, String output, String name) throws Exception {
        String compress_name = null;
        if (name != null) {
            compress_name = name;
        } else {
            compress_name = new File(input).getName();
        }
        byte[] buffer = new byte[1024];
        try {
            GzipParameters gp = new GzipParameters();    //设置压缩文件里的文件名
            gp.setFilename(compress_name);
            GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(new FileOutputStream(output), gp);
            FileInputStream fis = new FileInputStream(input);
            int length;
            while ((length = fis.read(buffer)) > 0) {
                gcos.write(buffer, 0, length);
            }
            fis.close();
            gcos.finish();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
	
	
	
	public static void z7z(String input, String output, String name) throws Exception {
        try {
            SevenZOutputFile sevenZOutput = new SevenZOutputFile(new File(output));
            SevenZArchiveEntry entry = null;

            String[] paths = input.split("\\|");
            File[] files = new File[paths.length];
            for (int i = 0; i < paths.length; i++) {
                files[i] = new File(paths[i].trim());
            }
            for (int i = 0; i < files.length; i++) {
                BufferedInputStream instream = null;
                instream = new BufferedInputStream(new FileInputStream(paths[i]));
                if (name != null) {
                    entry = sevenZOutput.createArchiveEntry(new File(paths[i]), name);
                } else {
                    entry = sevenZOutput.createArchiveEntry(new File(paths[i]), new File(paths[i]).getName());
                }
                sevenZOutput.putArchiveEntry(entry);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = instream.read(buffer)) > 0) {
                    sevenZOutput.write(buffer, 0, len);
                }
                instream.close();
                sevenZOutput.closeArchiveEntry();
            }
            sevenZOutput.close();
        } catch (IOException ioe) {
            System.out.println(ioe.toString() + "  " + input);
        }
    }
	
	
}

