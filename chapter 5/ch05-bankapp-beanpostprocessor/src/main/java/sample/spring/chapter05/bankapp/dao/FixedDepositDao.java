package sample.spring.chapter05.bankapp.dao;

import sample.spring.chapter05.bankapp.domain.FixedDepositDetails;

public interface FixedDepositDao {
	boolean createFixedDeposit(FixedDepositDetails fdd);
}
