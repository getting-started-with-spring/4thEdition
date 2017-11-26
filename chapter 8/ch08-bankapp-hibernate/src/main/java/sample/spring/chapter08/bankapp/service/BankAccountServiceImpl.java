package sample.spring.chapter08.bankapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter08.bankapp.dao.BankAccountDao;
import sample.spring.chapter08.bankapp.domain.BankAccountDetails;

@Service(value = "bankAccountService")
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountDao bankAccountDao;

	@Override
	@Transactional
	public int createBankAccount(BankAccountDetails bankAccountDetails) {
		return bankAccountDao.createBankAccount(bankAccountDetails);
	}

}
