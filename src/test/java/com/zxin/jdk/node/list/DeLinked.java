package com.zxin.jdk.node.list;


public class DeLinked<E> {

	
	public static Node<Integer> reverse(Node<Integer> a) {
//		do {
//			Node<Integer> next = a.next;
//			a.next = a.prev;
//			a.prev = next;
//			a = next;		
//		} while (a.next!=null);
//		a.next = a.prev;
		while(a.next!=null){
			Node<Integer> next = a.next;
			a.next = a.prev;
			a.prev = next;
			a = next;
		}
		a.next = a.prev;
		return a;
	}
	
	public static void main(String[] args) {
		Node<Integer> a = new Node<Integer>(null, 1, null);
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
			Node<Integer> b = new Node<Integer>(a, n, null);
			a.next = b;
			a = b;
		}
		
	}
	public static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
	
	
}
