package com.zxin.jdk.test.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeTest {
	public static void main(String[] args) {
		System.out.println(LocalTime.now().toString());
		System.out.println(LocalDate.now());
		System.out.println(LocalDateTime.now());
	}
}
