package sample.spring.chapter04.bankapp.service;

import sample.spring.chapter04.bankapp.dao.FixedDepositDao;
import sample.spring.chapter04.bankapp.domain.FixedDepositDetails;

public class FixedDepositServiceImpl implements FixedDepositService {
	private FixedDepositDao myFixedDepositDao;

	public void setMyFixedDepositDao(FixedDepositDao myFixedDepositDao) {
		this.myFixedDepositDao = myFixedDepositDao;
	}

	@Override
	public void createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		// -- create fixed deposit
		myFixedDepositDao.createFixedDeposit(fdd);
	}
}
