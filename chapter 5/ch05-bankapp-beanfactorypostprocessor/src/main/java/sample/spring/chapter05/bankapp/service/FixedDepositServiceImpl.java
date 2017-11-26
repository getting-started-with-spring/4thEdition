package sample.spring.chapter05.bankapp.service;

import sample.spring.chapter05.bankapp.dao.FixedDepositDao;

public class FixedDepositServiceImpl implements FixedDepositService {
	private FixedDepositDao fixedDepositDao;

	public void setFixedDepositDao(FixedDepositDao fixedDepositDao) {
		this.fixedDepositDao = fixedDepositDao;
	}

	@Override
	public void createFixedDeposit(long id, float depositAmount, int tenure,
			String email) throws Exception {
		// -- create fixed deposit
		fixedDepositDao.createFixedDeposit(id, depositAmount, tenure, email);
	}
}
