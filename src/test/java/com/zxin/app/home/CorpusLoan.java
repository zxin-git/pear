package com.zxin.app.home;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorpusLoan extends AbsLoan{

	private static final Logger logger = LoggerFactory.getLogger(CorpusLoan.class);

	private BigDecimal averageCorpus;
	
	public CorpusLoan(int year, BigDecimal loan) {
		super(loan,year);
		this.time = year;
		this.loan = loan;
		this.averageCorpus = loan.divide(new BigDecimal(time*MONTH_PER_YEAR), DEFAULT_SCALE, RoundingMode.HALF_UP);	//四舍五入请注意
	}
	
	@Override
	public BigDecimal hasRepayCorpus(int month){
		return averageCorpus.multiply(new BigDecimal(month));
	}
	
	@Override
	public BigDecimal currentInterest(int month){
		return rate.multiply(remainCorpus(month-1)).divide(new BigDecimal(MONTH_PER_YEAR), DEFAULT_SCALE, RoundingMode.CEILING);
	}
	
	@Override
	public BigDecimal currentCorpus(int month){
		return averageCorpus;
	}
	
	@Override
	public BigDecimal hasRepayInterest(int month){
		BigDecimal hasRepayInterest = new BigDecimal(0);
		for (int i = 1 ; i <= month; i++) {
			hasRepayInterest = hasRepayInterest.add(currentInterest(i));
		}
		return hasRepayInterest;
	}	
	
}


