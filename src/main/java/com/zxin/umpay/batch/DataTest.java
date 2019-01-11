package com.zxin.umpay.batch;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTest {

	private static Logger logger = LoggerFactory.getLogger(DataTest.class);

	private static File dir = new File("D:\\Users\\tmp");
	
	private static Map<String, Node> map = new HashMap<>();
	
	public static void main(String[] args) {
		try{
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				Scanner scanner = new Scanner(file);
				scanner.nextLine();
				while(scanner.hasNextLine()){
					String line = scanner.nextLine();
					String[] datas = line.split(",");
					Node now = new Node(datas[1]);
					Node node = map.get(datas[0]) == null ? now : map.get(datas[0]).next(now) ;
					map.put(datas[0], node);
				}
			}
//			System.out.println(Json.toJson(map));
			int i = 0 ;
			for (Entry<String, Node> entry : map.entrySet()) {
//				if(entry.getValue().toString().split("1;").length >= 6 
//						|| entry.getValue().toString().split("unknown;").length >= 6
//						|| entry.getValue().toString().split("\\|").length >= 6){
//					
//				}else{
					System.out.println(i++ + "\t"+entry.getKey()+ "###\t" +entry.getValue());
//				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static class Node{
		private String value;
		private Node next;
		
		public Node(String value){
			this.value = value;
		}
		
		public Node next(Node node){
			Node next = this;
			while(next.next !=null ){
				next = next.next;
			}
			next.next = node;
			return this;
		}
		
		@Override
		public String toString() {
			String str = this.value+";";
			Node next = this;
			while(next.next != null){
				str += next.next.value+";";
				next = next.next;
			}
			return str;
		}
	}
	
	
}


