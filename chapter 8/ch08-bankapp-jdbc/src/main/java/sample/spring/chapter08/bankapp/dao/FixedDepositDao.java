package sample.spring.chapter08.bankapp.dao;

import sample.spring.chapter08.bankapp.domain.FixedDepositDetails;

public interface FixedDepositDao {
	int createFixedDeposit(FixedDepositDetails fdd);
	FixedDepositDetails getFixedDeposit(int fixedDepositId);
}
