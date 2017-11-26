package sample.spring.chapter06.bankapp.dao;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;

public interface FixedDepositDao {
	boolean createFixedDeposit(FixedDepositDetails fdd);
}
