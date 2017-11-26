package sample.spring.chapter08.bankapp.service;

import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter08.bankapp.dao.BankAccountDao;
import sample.spring.chapter08.bankapp.dao.FixedDepositDao;
import sample.spring.chapter08.bankapp.domain.FixedDepositDetails;

public class FixedDepositServiceImpl implements FixedDepositService {

	private FixedDepositDao myFixedDepositDao;

	private BankAccountDao bankAccountDao;

	public void setBankAccountDao(BankAccountDao bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}
	
	public void setMyFixedDepositDao(FixedDepositDao myFixedDepositDao) {
		this.myFixedDepositDao = myFixedDepositDao;
	}
	
	@Override
	@Transactional
	public int createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		// -- create fixed deposit
		bankAccountDao.subtractFromAccount(fdd.getBankAccountId()
				.getAccountId(), fdd.getFdAmount());
		return myFixedDepositDao.createFixedDeposit(fdd);
	}

	@Override
	@Transactional
	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		return myFixedDepositDao.getFixedDeposit(fixedDepositId);
	}
}
