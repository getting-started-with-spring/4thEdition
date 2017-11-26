package sample.spring.chapter11.bankapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.spring.chapter11.bankapp.dao.BankAccountDao;
import sample.spring.chapter11.bankapp.domain.BankAccountDetails;

@Service(value = "bankAccountService")
public class BankAccountServiceImpl implements BankAccountService {
	private static Logger logger = LogManager
			.getLogger(BankAccountServiceImpl.class);
	@Autowired
	private BankAccountDao bankAccountDao;

	@Override
	public int createBankAccount(BankAccountDetails bankAccountDetails) {
		logger.info("createBankAccount method invoked");
		return bankAccountDao.createBankAccount(bankAccountDetails);
	}
}
