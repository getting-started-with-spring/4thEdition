package sample.spring.chapter04.bankapp.service;

import java.util.Date;

import sample.spring.chapter04.bankapp.domain.AccountStatement;

public interface AccountStatementService {
	public AccountStatement getAccountStatement(Date from, Date to);
}
