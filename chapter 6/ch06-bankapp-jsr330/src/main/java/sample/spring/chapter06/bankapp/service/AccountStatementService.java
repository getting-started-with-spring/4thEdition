package sample.spring.chapter06.bankapp.service;

import java.util.Date;

import sample.spring.chapter06.bankapp.domain.AccountStatement;

public interface AccountStatementService {
	public AccountStatement getAccountStatement(Date from, Date to);
}
