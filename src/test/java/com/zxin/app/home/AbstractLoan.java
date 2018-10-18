package com.zxin.app.home;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 抽象方法
 * 仅实现依赖年限和利率的方法，不依赖的由接口默认实现，@since jdk1.8 
 * @author zxin
 *
 */
public abstract class AbstractLoan implements ILoan{

	protected static final int DEFAULT_SCALE = 8;
	
	protected static final int MONTH_PER_YEAR = 12;
	//单位 w
	protected BigDecimal loan;
	
	protected int time;
	
	protected static BigDecimal rate = new BigDecimal(Double.toString(4.9/100));
	
	public AbstractLoan(BigDecimal loan, int time){
		this.loan = loan;
		this.time = time;
	}
	
	public static BigDecimal getMonthRate() {
		return rate.divide(new BigDecimal(MONTH_PER_YEAR), DEFAULT_SCALE, RoundingMode.CEILING);
	}
	
	@Override
	public BigDecimal currentInterest(int month) {
		return remainCorpus(month-1).multiply(getMonthRate());
	}
	
	@Override
	public BigDecimal totalInterest() {
		return hasRepayInterest(this.time*MONTH_PER_YEAR);
	}
	
	@Override
	public BigDecimal remainInterest(int month) {
		return hasRepayInterest(this.time*MONTH_PER_YEAR).subtract(hasRepayInterest(month));
	}

	@Override
	public BigDecimal remainRepay(int month){
		return hasRepay(this.time*MONTH_PER_YEAR).subtract(hasRepay(month));
	};

	public BigDecimal getLoan() {
		return loan;
	}

	public void setLoan(BigDecimal loan) {
		this.loan = loan;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	

}

