package com.zxin.jdk.test.util;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<Integer> list = getList(10);
//		for (Integer i : list) {
//			if (i==5) {
//				list.remove(5);	//java.util.ConcurrentModificationException
//			}
//		}
		
//		for (int i=0,len=list.size();i<len;i++) {
//			System.out.println(list.get(i));
//			if (list.get(i)==5) {		//java.lang.IndexOutOfBoundsException
//				list.remove(7);	
//			}
//		}
		
//		for (int i=0;i<list.size();i++) {		动态改变了size
//			System.out.println(list.get(i));
//			if (list.get(i)==5) {		
//				list.remove(7);	
//			}
//		}
		
		Iterator<Integer> sListIterator = list.iterator();  
		while(sListIterator.hasNext()){  
		    Integer e = sListIterator.next();  
		    if(e.equals("3")){  		//推荐
		    sListIterator.remove();  
		    }  
		} 
		
	}
	
	public static ArrayList<Integer> getList(int num) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			list.add(i);
		}
		return list;
		
	}
}
