package org.nutz.plugin.sigar;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Who;

public class SigarTest {
	private static Sigar sigar = new Sigar();
	
	public static void main(String[] args) {
		
	}
	
	public static void system(){
		// 取当前系统进程表中的用户信息  
		Who who[];
		try {
			who = sigar.getWhoList();
		 
			if (who != null && who.length > 0) {  
			    for (int i = 0; i < who.length; i++) {  
			        print("\n~~~~~~~~~" + String.valueOf(i) + "~~~~~~~~~");  
			        Who _who = who[i];  
			        print("getDevice() = " + _who.getDevice());  
			        print("getHost() = " + _who.getHost());  
			        print("getTime() = " + _who.getTime());  
			        // 当前系统进程表中的用户名  
			        print("getUser() = " + _who.getUser());  
			    }  
			} 
		} catch (SigarException e) {
			e.printStackTrace();
		} 
	}
	
	public static void print(String s){
		System.out.println(s);
	}
}
