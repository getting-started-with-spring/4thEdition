package sample.spring.chapter03.bankapp.dao;

import sample.spring.chapter03.bankapp.domain.FixedDepositDetails;

public interface FixedDepositDao {
	boolean createFixedDeposit(FixedDepositDetails fdd);
}
