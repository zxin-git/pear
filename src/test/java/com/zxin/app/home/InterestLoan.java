package com.zxin.app.home;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterestLoan extends AbstractLoan{

	private static final Logger logger = LoggerFactory.getLogger(InterestLoan.class);		
	
	private final BigDecimal payment;
	
	public InterestLoan(BigDecimal loan, int time) {
		super(loan, time);
		this.payment = payment();
	}
	
	/**
	 * 每月应本息算法
	 * 
	 * 第一个月: A(1+β)-X = a1
	 * 第二个月: A(1+β)^2-X[1+(1+β)] = (A(1+β)-X)(1+β)-X = a1(1+β)-X =a2
	 * 第三个月: A(1+β)^3-X[1+(1+β)+(1+β)^2] = ((A(1+β)-X)(1+β)-X)(1+β)-X = a2(1+β)-X =a3
	 * 第n个月: A(1+β)^n–X[1+(1+β)+(1+β)^2+…+(1+β)^(n-1)] = A(1+β)^n –X[(1+β)^n - 1]/β
	 * 第m个月: A(1+β)^m –X[(1+β)^m - 1]/β = 0
	 * 即: X = Aβ(1+β)^m /[(1+β)^m - 1] 	;其中 X=payment A=loan β=monthRate m=month base=(1+β)^m 
	 * 
	 * @return
	 */
	private BigDecimal payment(){
		BigDecimal base = BigDecimal.ONE.add(getMonthRate()).pow(time*MONTH_PER_YEAR);
		return loan.multiply(getMonthRate()).multiply(base)
                .divide(base.subtract(BigDecimal.ONE), DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);
	}
	

	public BigDecimal currentRepay(int month){
		return payment;
	};
	
	public BigDecimal remainCorpus(int month){
		BigDecimal remainCorpus = loan;
		for (int i = 1; i <= month; i++) {
			remainCorpus = remainCorpus.multiply(getMonthRate().add(new BigDecimal(1))).subtract(payment); 
		}
		return remainCorpus;
	}
	
	//==================================已由剩余本金 算出当前利息=========================================================================================	
	@Override
	public BigDecimal currentCorpus(int month){
		return payment.subtract(currentInterest(month));
	}
	
	//====>>hasCorpus, hasRepayInterest
	
	/**
	 * 覆盖父方法,仅为优化算法，可删除
	 */
	@Override
	public BigDecimal hasRepay(int month){
		return payment.multiply(new BigDecimal(month));
	}
	
	
	public static BigDecimal getLoan(int time, BigDecimal payment){
		BigDecimal base = BigDecimal.ONE.add(getMonthRate()).pow(time*MONTH_PER_YEAR);
		return base.subtract(BigDecimal.ONE).multiply(payment)
				.divide(base.multiply(getMonthRate()), 2, RoundingMode.HALF_UP);
	}

}
