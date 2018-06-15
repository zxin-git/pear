package com.zxin.jdk.node.list;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
	public static void main(String[] args) {
		orderTest();
	}
	
	public static void orderTest() {
//		LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();//默认为插入排序，见accessOrder属性
		LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>(16,0.75f,true); //为true为访问排序
		linkedHashMap.put(1, "a");
		linkedHashMap.put(2, "b");
		linkedHashMap.put(3, "c");
		linkedHashMap.put(4, "d");
//		linkedHashMap.put(2, "e");
		linkedHashMap.get(2);
		linkedHashMap.get(2);
		linkedHashMap.get(2);
		linkedHashMap.get(3);
		linkedHashMap.get(1);
//		Iterator<Map.Entry<Integer, String>> iterator = linkedHashMap.entrySet().iterator();
		Iterator<Integer> iterator = linkedHashMap.keySet().iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
