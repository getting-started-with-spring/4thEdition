package sample.spring.chapter07.bankapp.mybatis.dao;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.spring.chapter07.bankapp.dao.AccountStatementDao;
import sample.spring.chapter07.bankapp.domain.AccountStatement;
import sample.spring.chapter07.bankapp.domain.DataSource;

public class AccountStatementMyBatisDaoImpl implements AccountStatementDao {
	private static Logger logger = LogManager.getLogger(AccountStatementMyBatisDaoImpl.class);

	public AccountStatementMyBatisDaoImpl(DataSource dataSource) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public AccountStatement getAccountStatement(Date from, Date to) {
		logger.info("Getting account statement");
		return new AccountStatement();
	}

}
