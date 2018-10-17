package com.zxin.umpay.count;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountTest {
	
	public static void main(String[] args) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			Scanner scanner = new Scanner(new File("consume.log"));
			int i = 0;
			while (scanner.hasNextLine()) {
				i++;
				String line = scanner.nextLine();
				String[] as = line.split(" ");
				if(as ==null || as.length!=4)System.err.println( i +"error");
				int payFlag = Integer.parseInt(as[3]);
				String key = as[0]+"_"+as[1]+"_"+payFlag;
				if(map.get(key)!=null){
					map.put(key, map.get(key)+1);
				}else{
					map.put(key, 1);
				}
			}
			
			write(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void write(Map<String, Integer> map){
		File file = new File("map.txt");
		if (file.exists()) {
			file.delete();
		}
		FileWriter writer = null;
		try{
			file.createNewFile();
			writer = new FileWriter(file);
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				String str = entry.getKey()+" \t:\t "+entry.getValue();
				writer.write(str+"\n");
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

