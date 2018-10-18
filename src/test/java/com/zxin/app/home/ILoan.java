package com.zxin.app.home;

import java.math.BigDecimal;

public interface ILoan {

	/**
	 * 剩余本金
	 * 该月还完后
	 * @param month
	 * @return
	 */
	BigDecimal remainCorpus(int month);
	
	/**
	 * 此月应还的利息
	 * @param month
	 * @return
	 */
	BigDecimal currentInterest(int month);
	
	/**
	 * 此月应还的本金, 本金贷款默认值
	 * @param month
	 * @return
	 */
	BigDecimal currentCorpus(int month);
	
	
	/**
	 * 此月应还的金额, 本息贷款默认值
	 * @param month
	 * @return
	 */
	BigDecimal currentRepay(int month);
	
	/**
	 * 已经还完的本金
	 * 包括该月，该月还完后
	 * @param month
	 * @return
	 */
	default BigDecimal hasRepayCorpus(int month){
		BigDecimal hasRepayCorpus = new BigDecimal(0);
		for (int i = 1 ; i <= month; i++) {
			hasRepayCorpus = hasRepayCorpus.add(currentCorpus(i));
		}
		return hasRepayCorpus;	
	}
	
	/**
	 * 已经还的利息
	 * 该月还完后
	 * @param month
	 * @return
	 */
	default BigDecimal hasRepayInterest(int month){
		BigDecimal hasRepayInterest = new BigDecimal(0);
		for (int i = 1 ; i <= month; i++) {
			hasRepayInterest = hasRepayInterest.add(currentInterest(i));
		}
		return hasRepayInterest;
	}
	
	/**
	 * 贷款总共的利息
	 * 依赖年限
	 * @param month
	 * @return
	 */
	BigDecimal totalInterest();
	
	/**
	 * 贷款剩余利息
	 * 依赖年限
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
	
	/**
	 * 按期应剩余的还款额
	 * 依赖年限
	 * @param month
	 * @return
	 */
	default BigDecimal remainRepay(int month){
		return hasRepay(month).subtract(hasRepay(month));
	};
	
}

