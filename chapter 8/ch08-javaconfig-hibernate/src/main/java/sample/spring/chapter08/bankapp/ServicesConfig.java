package sample.spring.chapter08.bankapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample.spring.chapter08.bankapp.dao.BankAccountDao;
import sample.spring.chapter08.bankapp.dao.FixedDepositDao;
import sample.spring.chapter08.bankapp.service.BankAccountService;
import sample.spring.chapter08.bankapp.service.BankAccountServiceImpl;
import sample.spring.chapter08.bankapp.service.FixedDepositService;
import sample.spring.chapter08.bankapp.service.FixedDepositServiceImpl;

@Configuration
public class ServicesConfig {

	@Bean
	public BankAccountService bankAccountService(BankAccountDao bankAccountDao) {
		BankAccountServiceImpl bankAccountServiceImpl = new BankAccountServiceImpl();
		bankAccountServiceImpl.setBankAccountDao(bankAccountDao);
		return bankAccountServiceImpl;
	}

	@Bean
	public FixedDepositService fixedDepositService(FixedDepositDao myFixedDepositDao, BankAccountDao bankAccountDao) {
		FixedDepositServiceImpl fixedDepositServiceImpl = new FixedDepositServiceImpl();
		fixedDepositServiceImpl.setBankAccountDao(bankAccountDao);
		fixedDepositServiceImpl.setMyFixedDepositDao(myFixedDepositDao);
		return fixedDepositServiceImpl;
	}
}
