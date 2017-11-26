package sample.spring.chapter06.bankapp.service;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;

public interface FixedDepositService {
	FixedDepositDetails getFixedDepositDetails(long id);

	boolean createFixedDeposit(FixedDepositDetails fdd);

}
