package sample.spring.chapter07.bankapp.dao;

import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;

public interface FixedDepositDao {
	boolean createFixedDeposit(FixedDepositDetails fdd);
}
