package com.zxin.jdk.test.nio;

public class NIOTest {
	public static void main(String[] args) {
		SocketTest.client(8080);
		SocketTest.client(8081);
	}
}
