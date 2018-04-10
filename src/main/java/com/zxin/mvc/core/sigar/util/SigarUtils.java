package com.zxin.mvc.core.sigar.util;

import java.io.File;
import java.nio.file.Paths;

import org.hyperic.sigar.Sigar;

public class SigarUtils {
    private static Sigar sigar = initSigar();
    
    public static Sigar sigar(){
		if(sigar==null)
			sigar = initSigar();
		return sigar;
	}
    
    private static Sigar initSigar() {
        try {
            //此处只为得到依赖库文件的目录，可根据实际项目自定义
            String file = Paths.get(System.getProperty("conf.dir")+File.separator+"core"+File.separator+"sigar"+File.separator+"lib").toString();
            File classPath = new File(file);

            String path = System.getProperty("java.library.path");
            String sigarLibPath = classPath.getCanonicalPath();
            //为防止java.library.path重复加，此处判断了一下
            if (!path.contains(sigarLibPath)) {
                if (isOSWin()) {
                    path += ";" + sigarLibPath;
                } else {
                    path += ":" + sigarLibPath;
                }
                System.setProperty("java.library.path", path);
            }
            return new Sigar();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isOSWin(){//OS 版本判断
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("win") >= 0) {
            return true;
        } else return false;
    }
       
    public static void main(String[] args) {
    	
	}
}