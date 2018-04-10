package com.zxin.jdk.test.lang;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Map.Entry;

public class HashTest {
	public static void first(){
		HashMap hashMap = new HashMap<>();
		Data d = new Data();
		Data d2 = new Data();
//		Data d2 = d;
		System.out.println(d2==d);
		System.out.println(d.equals(d2));
		System.out.println(d.hashCode());
		System.out.println(d2.hashCode());
	}
	
	public static void set(){
		HashSet hashSet =new HashSet();
	}
	
	public static void map(){
//		resize()	newCap = oldCap << 1 	newThr = oldThr << 1  	全为2倍,有最大 MAXIMUM_CAPACITY限制
		HashMap<String,String> hashMap = new HashMap<>();
		hashMap.put("1", "a");
		hashMap.put("2", "b");
		hashMap.put("3", "a");
		Entry<String, String> a = null;Entry<String, String> b = null;
		for(Entry<String, String> e:hashMap.entrySet()){
			if(e.getKey().equals("1"))a=e;
			if(e.getKey().equals("3"))b=e;
		}
		//return Objects.hashCode(key) ^ Objects.hashCode(value); HashtMap$Node.hashCode()返回的还是key与value哈希的异或	
		//HashtMap$Node.hash = hash(key);  hash属性  取 hash(Object) return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);	^异或
		//根据hash验证   >>右移(左边以该数的符号位补充，移出的部分将被抛弃), >>>无符号右移
		System.out.println(a.equals(b));
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());	
		//if ((p = tab[i = (n - 1) & hash]) == null)		hash=hash(key),n=tab.lenth=16 default;
		hashMap.put("1", "c");
	}
	
	public static void table(){
//		rehash() newCapacity = (oldCapacity << 1) + 1  threshold = (int)Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1); 	this(11, 0.75f);
//		int newCapacity = (oldCapacity << 1) + 1;		
		Hashtable<String,String> ht1 = new Hashtable<>();
		Hashtable<String,String> ht2 = new Hashtable<>();
		ht1.put("1", "a");
		ht1.put("2", "b");
		ht1.put("3", "a");
		Entry<String, String> a = null;Entry<String, String> b = null;
		for(Entry<String, String> e:ht1.entrySet()){
			if(e.getKey().equals("1"))a=e;
			if(e.getKey().equals("3"))b=e;
		}
//		return hash ^ Objects.hashCode(value);	Hashtable$Entry.hash == key.hashCode();		保证了key与value决定 hashcode值
		System.out.println(a.equals(b));
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
//		int hash = key.hashCode();
//		int index = (hash & 0x7FFFFFFF) % tab.length;	tab.length=11 default;
		ht1.put("1", "c");
	}
	
	public static void main(String[] args) {
//		first();
		table();
//		map();
	}
}
