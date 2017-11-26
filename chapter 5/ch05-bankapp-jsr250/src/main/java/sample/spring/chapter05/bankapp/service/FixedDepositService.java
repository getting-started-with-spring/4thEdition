package sample.spring.chapter05.bankapp.service;

import sample.spring.chapter05.bankapp.domain.FixedDepositDetails;


public interface FixedDepositService {
	void createFixedDeposit(FixedDepositDetails fdd) throws Exception;
}
