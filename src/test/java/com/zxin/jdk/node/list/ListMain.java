package com.zxin.jdk.node.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ListMain {
	public static void main(String[] args) {
//		linkedHashMapTest();
//		System.out.println(MapThresholdTest(0b1111));
//		treeMap();
		hashMap();
	}
	
	public static void hashMap() {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("a", "abc");
		hashMap.put("b", "abb");
		hashMap.put("c", "acc");
		hashMap.put(null, "1");
		hashMap.put("d", null);
		
	}
	
	public static void treeMap() {
		TreeMap<String, String> treeMap = new TreeMap<>();
		treeMap.put("a", "abc");
		treeMap.put("b", "abb");
		treeMap.put("c", "acc");
//		treeMap.put(null, "1");
		treeMap.put("d", null);
		
	}
	
	public static int MapThresholdTest(int cap) {
		 int n = cap - 1;
	        n |= n >>> 1;	//相当于 n|(n/2) 
	        n |= n >>> 2;
	        n |= n >>> 4;
	        n |= n >>> 8;
	        n |= n >>> 16;
	        return (n < 0) ? 1 :  n + 1;
	}
	
	public static void linkedHashMapTest() {
		LinkedHashMap<Integer,String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put(1, "abc");
		linkedHashMap.put(2, "bc");
		linkedHashMap.put(3, "c");
		Iterator<Map.Entry<Integer,String>> iterator = linkedHashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, String> entry = iterator.next();
			System.out.println("key:"+entry.getKey()+"value:"+entry.getValue());
		}
	}
	
	
	public static void mapData() {
		HashSet<Data> hashSet = new HashSet<>();
		Data a = new Data("abc");
		Data b = new Data("abc");
		System.out.println(a==b);
		System.out.println(a.hashCode()==b.hashCode());
		System.out.println(a.equals(b));
//		if (p.hash == hash &&
//                ((k = p.key) == key || (key != null && key.equals(k))))
		hashSet.add(a);
		hashSet.add(b);
		System.out.println(hashSet.size());
	}
	
	public static void mapString() {
		HashSet<String> hashSet = new HashSet<>();
		String a = "abc";
		String b = new String("abc");
		System.out.println(a==b);
		System.out.println(a.hashCode()==b.hashCode());
		System.out.println(a.equals(b));
		hashSet.add(a);
		hashSet.add(b);
		System.out.println(hashSet.size());
	}
	
	public static void listToString() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		System.out.println(list.toString());
		
	}
}
