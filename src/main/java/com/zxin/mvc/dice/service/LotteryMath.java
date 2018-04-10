package com.zxin.mvc.dice.service;

import java.math.BigInteger;

public class LotteryMath {
	public static void main(String[] args) {
		System.out.println( "一等奖："+100.0/Combination(33,6).multiply(Combination(16,1)).longValue()+"%" );
		System.out.println(Combination(33,6).multiply(Combination(16,1)));
		
	
	}
	
	public static BigInteger Arrange(int total,int num){
		BigInteger sum = BigInteger.valueOf(1);
		for (int i = total; i >num ; i--) {
			sum = sum.multiply(BigInteger.valueOf(i));
		}
		return sum;
	}
	
	public static BigInteger Combination(int total,int num){
		BigInteger sum = BigInteger.valueOf(1);
		int diff = total - num;
		for (int i = total; i >Math.max(diff, num) ; i--) {
			sum = sum.multiply(BigInteger.valueOf(i));
		}
		for (int i = Math.min(diff, num); i >1 ; i--) {
			sum = sum.divide(BigInteger.valueOf(i));
		}		
		return sum;
	}
	
	public static BigInteger Factorial(long num ){
		BigInteger sum = BigInteger.valueOf(1);
		for (long i = 1; i <= num; i++) {
			sum= sum.multiply(BigInteger.valueOf(i));
		}		
		return sum;
	}
}
