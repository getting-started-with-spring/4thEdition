package sample.spring.chapter06.bankapp.service;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import sample.spring.chapter06.bankapp.dao.AccountStatementDao;
import sample.spring.chapter06.bankapp.domain.AccountStatement;

@Named(value="accountStatementService")
public class AccountStatementServiceImpl implements AccountStatementService {
	@Inject
	private AccountStatementDao accountStatementDao;
	
	@Override
	public AccountStatement getAccountStatement(Date from, Date to) {
		return accountStatementDao.getAccountStatement(from, to);
	}
}
