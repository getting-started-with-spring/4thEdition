package sample.spring.chapter04.bankapp.dao;

import sample.spring.chapter04.bankapp.domain.FixedDepositDetails;

public interface FixedDepositDao {
	boolean createFixedDeposit(FixedDepositDetails fdd);
}
