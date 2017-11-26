package sample.spring.chapter07.bankapp.service;

import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;

public interface FixedDepositService {
	FixedDepositDetails getFixedDepositDetails(long id);

	boolean createFixedDeposit(FixedDepositDetails fdd);

}
