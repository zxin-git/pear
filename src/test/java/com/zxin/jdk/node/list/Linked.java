package com.zxin.jdk.node.list;

public class Linked<E> {

	
	public static Node<Integer> reverse(Node<Integer> a) {
//		Node<Integer> prev = null;	//.next要设置的值
//		while(a!=null){
//			Node<Integer> next = a.next;
//			a.next = prev;
//			prev = a;
//			a = next;
//		}
//		return prev;
		Node<Integer> prev = null;	//.next要设置的值
		while(a.next!=null){
			Node<Integer> next = a.next;
			a.next = prev;
			prev = a;	//准备下一个链的前缀
			a = next;
		}
		a.next = prev;
		return a;
	}
	
	public static void main(String[] args) {
		Node<Integer> a = new Node<Integer>( 1, null);
		init(a, 10);
		print(a);
		a = reverse(a);
		System.out.println("");
		print(a);
	}
	
	public static void print(Node<Integer> a){
		do {
			System.out.print(a.item + ",");
			a=a.next;
		} while (a!=null);
		
	}
	public static void init(Node<Integer> a,int i){
		for(int n=2;n<=i;n++){
			Node<Integer> b = new Node<Integer>(n, null);
			a.next = b;
			a = b;
		}
		
	}
	
	public static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
