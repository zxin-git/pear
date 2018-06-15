package com.zxin.jdk.node.concurrent.instance.block;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		System.err.println("input dir:");
//		String dir = in.nextLine();
		String dir = "E:/zxin/developer/java/git/znone/pear";
		System.out.println("input keyword:");
		String word = in.nextLine();
		final int FILE_QUEUE_SIZE = 10;
		final int SEARCH_THEADS = 10;
		BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
		FileEnumTask enumTask = new FileEnumTask(queue, new File(dir));
		new Thread(enumTask).start();
		SearchTask searchTask = new SearchTask(queue, word);
		for (int i = 0; i < SEARCH_THEADS; i++) {
			new Thread(searchTask).start();
		}
	}
}
