package com.zxin.jdk.node.list;

import java.util.Iterator;
import com.zxin.jdk.node.list.LinkedList;

import org.nutz.json.Json;


public class ListTest {
	public static void main(String[] args) {
		DeLinkedList<Integer> linkedList = new DeLinkedList<>();
		LinkedList<Integer> linkedList1 = new LinkedList<>();
		linkedList.add(1);
		linkedList1.add(1);
		linkedList.add(2);
		linkedList1.add(2);
		linkedList.add(3);
		linkedList.add(4);
//		linkedList.reverse(linkedList);
//		linkedList.print(linkedList);
	}
	
	
	public static void print(DeLinkedList<Integer> linkedList){
		for(int i=0;i<linkedList.size;i++){
			System.out.println(Json.toJson(linkedList.node(i)));
		}
	}
	
}
