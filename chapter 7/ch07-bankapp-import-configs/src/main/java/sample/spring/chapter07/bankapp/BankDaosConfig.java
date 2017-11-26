package sample.spring.chapter07.bankapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample.spring.chapter07.bankapp.dao.AccountStatementDao;
import sample.spring.chapter07.bankapp.dao.AccountStatementDaoImpl;
import sample.spring.chapter07.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter07.bankapp.dao.CustomerRegistrationDaoImpl;
import sample.spring.chapter07.bankapp.dao.FixedDepositDao;
import sample.spring.chapter07.bankapp.dao.FixedDepositDaoImpl;

@Configuration
public class BankDaosConfig {

	@Bean(name = "accountStatementDao")
	public AccountStatementDao accountStatementDao() {
		return new AccountStatementDaoImpl();
	}

	@Bean(name = "customerRegistrationDao")
	public CustomerRegistrationDao customerRegistrationDao() {
		return new CustomerRegistrationDaoImpl();
	}

	@Bean(name = "fixedDepositDao")
	public FixedDepositDao fixedDepositDao() {
		return new FixedDepositDaoImpl();
	}
}