package com.zxin.app.home;

import java.math.BigDecimal;

public interface ILoan {

	/**
	 * 已经还完的本金
	 * 包括该月，该月还完后
	 * @param month
	 * @return
	 */
	BigDecimal hasRepayCorpus(int month);
	
	/**
	 * 剩余本金
	 * 该月还完后
	 * @param month
	 * @return
	 */
	BigDecimal remainCorpus(int month);
	
	/**
	 * 此月应还的本金
	 * @param month
	 * @return
	 */
	BigDecimal currentCorpus(int month);
	
	/**
	 * 此月应还的利息
	 * @param month
	 * @return
	 */
	BigDecimal currentInterest(int month);
	
	/**
	 * 此月应还的金额
	 * @param month
	 * @return
	 */
	default BigDecimal currentRepay(int month){
		return currentCorpus(month).add(currentInterest(month));
	};
	
	/**
	 * 已经还的利息
	 * 该月还完后
	 * @param month
	 * @return
	 */
	BigDecimal hasRepayInterest(int month);
	
	/**
	 * 贷款总共的利息
	 * @param month
	 * @return
	 */
	BigDecimal totalInterest();
	
	/**
	 * 贷款剩余利息
	 * @param month
	 * @return
	 */
	BigDecimal remainInterest(int month);
	
	/**
	 * 已经还的金额
	 * @param month
	 * @return
	 */
	default BigDecimal hasRepay(int month){
		return hasRepayCorpus(month).add(hasRepayInterest(month));
	};
	
	
}

