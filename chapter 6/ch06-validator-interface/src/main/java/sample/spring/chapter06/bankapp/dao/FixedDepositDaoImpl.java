package sample.spring.chapter06.bankapp.dao;

import org.springframework.stereotype.Repository;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;

@Repository(value="myFixedDepositDao")
public class FixedDepositDaoImpl implements FixedDepositDao {
	public boolean createFixedDeposit(FixedDepositDetails fdd) {
		// -- save the fixed deposits and then return true
		return true;
	}
}
