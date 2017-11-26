package sample.spring.chapter04.bankapp.service;

import sample.spring.chapter04.bankapp.domain.FixedDepositDetails;


public interface FixedDepositService {
	void createFixedDeposit(FixedDepositDetails fdd) throws Exception;
}
