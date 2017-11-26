package sample.spring.chapter07.bankapp;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import sample.spring.chapter07.bankapp.dao.AccountStatementDao;
import sample.spring.chapter07.bankapp.dao.AccountStatementDaoImpl;
import sample.spring.chapter07.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter07.bankapp.dao.CustomerRegistrationDaoImpl;
import sample.spring.chapter07.bankapp.dao.FixedDepositDao;
import sample.spring.chapter07.bankapp.dao.FixedDepositDaoImpl;
import sample.spring.chapter07.bankapp.domain.CustomerRegistrationDetails;
import sample.spring.chapter07.bankapp.service.AccountStatementService;
import sample.spring.chapter07.bankapp.service.AccountStatementServiceImpl;
import sample.spring.chapter07.bankapp.service.CustomerRegistrationService;
import sample.spring.chapter07.bankapp.service.CustomerRegistrationServiceImpl;
import sample.spring.chapter07.bankapp.service.FixedDepositService;
import sample.spring.chapter07.bankapp.service.FixedDepositServiceImpl;

@Configuration
public class BankAppConfiguration {
	
	@Bean(name = "accountStatementService")
	public AccountStatementService accountStatementService() {
		AccountStatementServiceImpl accountStatementServiceImpl = new AccountStatementServiceImpl();
		accountStatementServiceImpl.setAccountStatementDao(accountStatementDao());
		return accountStatementServiceImpl;
	}

	@Bean(name = "accountStatementDao")
	public AccountStatementDao accountStatementDao() {
		return new AccountStatementDaoImpl();
	}

	@Bean(name = "customerRegistrationService")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CustomerRegistrationService customerRegistrationService() {
		return new CustomerRegistrationServiceImpl();
	}

	@Bean(name = "customerRegistrationDao")
	public CustomerRegistrationDao customerRegistrationDao() {
		return new CustomerRegistrationDaoImpl();
	}

	@Bean(name = "customerRegistrationDetails")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CustomerRegistrationDetails customerRegistrationDetails() {
		return new CustomerRegistrationDetails();
	}

	@Bean(name = "fixedDepositService")
	public FixedDepositService fixedDepositService() {
		return new FixedDepositServiceImpl();
	}

	@Bean
	public FixedDepositDao fixedDepositDao() {
		return new FixedDepositDaoImpl();
	}
}