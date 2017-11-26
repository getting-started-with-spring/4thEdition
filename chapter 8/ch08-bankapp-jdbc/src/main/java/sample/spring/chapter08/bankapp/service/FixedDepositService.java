package sample.spring.chapter08.bankapp.service;

import sample.spring.chapter08.bankapp.domain.FixedDepositDetails;


public interface FixedDepositService {
	int createFixedDeposit(FixedDepositDetails fdd) throws Exception;
	FixedDepositDetails getFixedDeposit(int fixedDepositId);
}
