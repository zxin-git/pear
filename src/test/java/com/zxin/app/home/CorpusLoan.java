package com.zxin.app.home;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorpusLoan extends AbstractLoan{

	private static final Logger logger = LoggerFactory.getLogger(CorpusLoan.class);

	private final BigDecimal averageCorpus;
	
	public CorpusLoan(BigDecimal loan, int time) {
		super(loan,time);
		this.averageCorpus = loan.divide(new BigDecimal(time*MONTH_PER_YEAR), DEFAULT_SCALE, RoundingMode.HALF_UP);	//四舍五入请注意
	}

	@Override
	public BigDecimal currentCorpus(int month){
		return averageCorpus;
	}
	
	//====>>hasCorpus
	
	@Override
	public BigDecimal remainCorpus(int month) {
		return loan.subtract(hasRepayCorpus(month));
	}
	
	//==================================已由剩余本金 算出当前利息=========================================================================================
	@Override
	public BigDecimal currentRepay(int month){
		return currentCorpus(month).add(currentInterest(month));
	};
	
	//====>>hasRepayInterest
	
}


