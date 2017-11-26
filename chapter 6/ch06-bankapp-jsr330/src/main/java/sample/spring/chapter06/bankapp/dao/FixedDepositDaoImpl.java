package sample.spring.chapter06.bankapp.dao;

import javax.inject.Named;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;

@Named(value="myFixedDepositDao")
public class FixedDepositDaoImpl implements FixedDepositDao {
	public boolean createFixedDeposit(FixedDepositDetails fdd) {
		// -- save the fixed deposits and then return true
		return true;
	}
}
