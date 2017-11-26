package sample.spring.chapter07.bankapp;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import sample.spring.chapter07.bankapp.dao.AccountStatementDao;
import sample.spring.chapter07.bankapp.dao.FixedDepositDao;
import sample.spring.chapter07.bankapp.service.AccountStatementService;
import sample.spring.chapter07.bankapp.service.AccountStatementServiceImpl;
import sample.spring.chapter07.bankapp.service.CustomerRegistrationService;
import sample.spring.chapter07.bankapp.service.CustomerRegistrationServiceImpl;
import sample.spring.chapter07.bankapp.service.FixedDepositService;
import sample.spring.chapter07.bankapp.service.FixedDepositServiceImpl;

@Configuration
public class BankServicesConfig {
	
	@Bean(name = "accountStatementService")
	public AccountStatementService accountStatementService(AccountStatementDao accountStatementDao) {
		AccountStatementServiceImpl accountStatementServiceImpl = new AccountStatementServiceImpl();
		accountStatementServiceImpl.setAccountStatementDao(accountStatementDao);
		return accountStatementServiceImpl;
	}

	@Bean(name = "customerRegistrationService")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CustomerRegistrationService customerRegistrationService() {
		return new CustomerRegistrationServiceImpl();
	}

	@Bean(name = "fixedDepositService")
	public FixedDepositService fixedDepositService(FixedDepositDao fixedDepositDao) {
		return new FixedDepositServiceImpl(fixedDepositDao);
	}
}
