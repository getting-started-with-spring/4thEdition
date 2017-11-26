package sample.spring.chapter07.bankapp.dao;

import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;

public interface FixedDepositDao {
	FixedDepositDetails getFixedDepositDetails(long id);

	boolean createFixedDeposit(FixedDepositDetails fdd);
}
