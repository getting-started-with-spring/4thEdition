package sample.spring.chapter11.bankapp.service;

import sample.spring.chapter11.bankapp.domain.FixedDepositDetails;


public interface FixedDepositService {
	void createFixedDeposit(FixedDepositDetails fdd) throws Exception;
	FixedDepositDetails getFixedDeposit(int fixedDepositId);
}
