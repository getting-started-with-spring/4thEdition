package sample.spring.chapter06.bankapp.service;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;


public interface FixedDepositService extends MyService {
	void createFixedDeposit(FixedDepositDetails fdd) throws Exception;
}
