package com.zxin.app.home;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterestLoan extends AbsLoan{

	private static final Logger logger = LoggerFactory.getLogger(InterestLoan.class);		
	
	public InterestLoan(BigDecimal loan, int time) {
		super(loan, time);
	}

	@Override
	public BigDecimal hasRepayCorpus(int month) {
		return null;
		
	}

	@Override
	public BigDecimal currentCorpus(int month) {
		return null;
		
		
	}

	@Override
	public BigDecimal currentInterest(int month) {
		return null;
		
		
	}

	@Override
	public BigDecimal hasRepayInterest(int month) {
		return null;
		
		
	}

}
