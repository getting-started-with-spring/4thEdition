package sample.spring.chapter07.bankapp.dao;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.spring.chapter07.bankapp.domain.AccountStatement;

public class AccountStatementDaoImpl implements AccountStatementDao {
	private static Logger logger = LogManager.getLogger(AccountStatementDaoImpl.class);

	@Override
	public AccountStatement getAccountStatement(Date from, Date to) {
		logger.info("Getting account statement");
		return new AccountStatement();
	}

}
