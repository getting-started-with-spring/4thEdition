package sample.spring.chapter07.bankapp.service;

import sample.spring.chapter07.bankapp.dao.FixedDepositDao;
import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;

public class FixedDepositServiceImpl implements FixedDepositService {
	private FixedDepositDao fixedDepositDao;

	public FixedDepositServiceImpl(FixedDepositDao fixedDepositDao) {
		this.fixedDepositDao = fixedDepositDao;
	}

	@Override
	public void createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		// -- create fixed deposit
		fixedDepositDao.createFixedDeposit(fdd);
	}
}
