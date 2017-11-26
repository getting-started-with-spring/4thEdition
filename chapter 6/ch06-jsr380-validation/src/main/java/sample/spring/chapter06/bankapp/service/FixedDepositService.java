package sample.spring.chapter06.bankapp.service;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;


public interface FixedDepositService {
	void createFixedDeposit(FixedDepositDetails fdd) throws Exception;
}
