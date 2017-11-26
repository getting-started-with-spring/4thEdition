package sample.spring.chapter08.bankapp.service;

import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter08.bankapp.dao.BankAccountDao;
import sample.spring.chapter08.bankapp.domain.BankAccountDetails;

public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountDao bankAccountDao;

	public void setBankAccountDao(BankAccountDao bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}
	
	@Override
	@Transactional
	public int createBankAccount(BankAccountDetails bankAccountDetails) {
		return bankAccountDao.createBankAccount(bankAccountDetails);
	}

}
