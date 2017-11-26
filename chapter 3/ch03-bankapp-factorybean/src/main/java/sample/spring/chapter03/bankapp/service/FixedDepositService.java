package sample.spring.chapter03.bankapp.service;

import sample.spring.chapter03.bankapp.domain.FixedDepositDetails;


public interface FixedDepositService {
	void createFixedDeposit(FixedDepositDetails fdd);
}
