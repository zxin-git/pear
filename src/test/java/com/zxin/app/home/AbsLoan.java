package com.zxin.app.home;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class AbsLoan implements ILoan{

	protected static final int DEFAULT_SCALE = 8;
	
	protected static final int MONTH_PER_YEAR = 12;
	//单位 w
	protected BigDecimal loan;
	
	protected int time;
	
	protected BigDecimal rate = new BigDecimal(Double.toString(4.9/100));
	
	public AbsLoan(BigDecimal loan, int time){
		this.loan = loan;
		this.time = time;
	}
	
	public BigDecimal getMonthRate() {
		return rate.divide(new BigDecimal(MONTH_PER_YEAR), DEFAULT_SCALE, RoundingMode.CEILING);
	}
	
	@Override
	public BigDecimal remainCorpus(int month) {
		return hasRepayCorpus(this.time*MONTH_PER_YEAR).subtract(hasRepayCorpus(month));
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
	public BigDecimal hasRepay(int month){
		BigDecimal hasRepay = new BigDecimal(0);
		for (int i = 1; i <= month; i++) {
			hasRepay = hasRepay.add(currentRepay(i));
		}
		return hasRepay;
//		return hasRepayCorpus(month).add(hasRepayInterest(month));
	}

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

