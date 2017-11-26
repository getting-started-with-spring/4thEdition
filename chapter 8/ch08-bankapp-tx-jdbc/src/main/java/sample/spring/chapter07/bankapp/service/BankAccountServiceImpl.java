package sample.spring.chapter07.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.spring.chapter07.bankapp.dao.BankAccountDao;
import sample.spring.chapter07.bankapp.domain.BankAccountDetails;

@Service(value = "bankAccountService")
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountDao bankAccountDao;

	@Override
	public int createBankAccount(BankAccountDetails bankAccountDetails) {
		return bankAccountDao.createBankAccount(bankAccountDetails);
	}

}
