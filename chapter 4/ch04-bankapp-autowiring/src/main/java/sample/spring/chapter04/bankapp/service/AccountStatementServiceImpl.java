package sample.spring.chapter04.bankapp.service;

import java.util.Date;

import sample.spring.chapter04.bankapp.dao.AccountStatementDao;
import sample.spring.chapter04.bankapp.domain.AccountStatement;

public class AccountStatementServiceImpl implements AccountStatementService {
	private AccountStatementDao accountStatementDao;
	
	public void setAccountStatementDao(AccountStatementDao accountStatementDao) {
		this.accountStatementDao = accountStatementDao;
	}

	@Override
	public AccountStatement getAccountStatement(Date from, Date to) {
		return accountStatementDao.getAccountStatement(from, to);
	}
}
