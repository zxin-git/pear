package com.zxin.app.home;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoanTest {

	private static final Logger logger = LoggerFactory.getLogger(LoanTest.class);
	
	public static void main(String[] args) {
//		AbstractLoan absLoan = new InterestLoan(new BigDecimal(70*10000), 30);
////		AbstractLoan absLoan = new CorpusLoan(new BigDecimal(70*10000), 20);
//		printInfo(absLoan);
		System.out.println(InterestLoan.getLoan(20, new BigDecimal(3346)));
	}
	
	public static void printInfo(AbstractLoan absLoan){
		System.out.println("贷款总额:\t"+absLoan.loan);
		System.out.println("总利息:\t"+absLoan.totalInterest().setScale(2, RoundingMode.HALF_UP));
		for(int i=1; i<=absLoan.time*12; i++){
			System.out.println("第" + i + "个月的 应还\t"+absLoan.currentRepay(i).setScale(2, RoundingMode.HALF_UP) +"\t利息为\t" + absLoan.currentInterest(i).setScale(2, RoundingMode.HALF_UP) +"\t剩余本金为:\t" + absLoan.remainCorpus(i).setScale(2, RoundingMode.HALF_UP));
		}
		printA(absLoan, 5);
	}
	
	public static void printA(AbstractLoan absLoan, int year){
		System.out.println(year + "年已还总额\t"+absLoan.hasRepay(year*12).setScale(2, RoundingMode.HALF_UP));
		System.out.println(year + "年已还利息\t"+absLoan.hasRepayInterest(year*12).setScale(2, RoundingMode.HALF_UP));
		System.out.println(year + "年剩余本金\t"+absLoan.remainCorpus(year*12).setScale(2, RoundingMode.HALF_UP));
	}
}

