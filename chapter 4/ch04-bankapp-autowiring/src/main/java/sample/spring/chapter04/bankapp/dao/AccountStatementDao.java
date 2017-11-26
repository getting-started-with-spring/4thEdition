package sample.spring.chapter04.bankapp.dao;

import java.util.Date;

import sample.spring.chapter04.bankapp.domain.AccountStatement;

public interface AccountStatementDao {
	public AccountStatement getAccountStatement(Date from, Date to);
}
