package sample.spring.chapter08.bankapp;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample.spring.chapter08.bankapp.dao.BankAccountDao;
import sample.spring.chapter08.bankapp.dao.BankAccountDaoImpl;
import sample.spring.chapter08.bankapp.dao.FixedDepositDao;
import sample.spring.chapter08.bankapp.dao.FixedDepositDaoImpl;

@Configuration
public class DaosConfig {
	
	@Bean
	public BankAccountDao bankAccountDao(SessionFactory sessionFactory){
		BankAccountDaoImpl bankAccountDaoImpl = new BankAccountDaoImpl();
		bankAccountDaoImpl.setSessionFactory(sessionFactory);
		return bankAccountDaoImpl;
	}
	
	@Bean
	public FixedDepositDao fixedDepositDao(SessionFactory sessionFactory) {
		FixedDepositDaoImpl fixedDepositDaoImpl = new FixedDepositDaoImpl();
		fixedDepositDaoImpl.setSessionFactory(sessionFactory);
		return fixedDepositDaoImpl;
	}
}
