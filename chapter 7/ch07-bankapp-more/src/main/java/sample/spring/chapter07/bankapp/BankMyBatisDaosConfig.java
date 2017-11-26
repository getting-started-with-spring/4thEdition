package sample.spring.chapter07.bankapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import sample.spring.chapter07.bankapp.dao.AccountStatementDao;
import sample.spring.chapter07.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter07.bankapp.dao.FixedDepositDao;
import sample.spring.chapter07.bankapp.domain.DataSource;
import sample.spring.chapter07.bankapp.mybatis.dao.AccountStatementMyBatisDaoImpl;
import sample.spring.chapter07.bankapp.mybatis.dao.CustomerRegistrationMyBatisDaoImpl;
import sample.spring.chapter07.bankapp.mybatis.dao.FixedDepositMyBatisDaoImpl;

@Configuration
@ImportResource(locations = "classpath:META-INF/spring/applicationContext.xml")
public class BankMyBatisDaosConfig {
	@Value("#{dbProps.driverClassName}")
	private String driverClass;
	
	@Value("#{dbProps.url}")
	private String url;
	
	@Value("#{dbProps.username}")
	private String username;
	
	@Value("#{dbProps.password}")
	private String password;
	
	@Bean
	public AccountStatementDao accountStatementDao() {
		return new AccountStatementMyBatisDaoImpl(new DataSource(driverClass, url, username, password));
	}

	@Bean
	public CustomerRegistrationDao customerRegistrationDao() {
		return new CustomerRegistrationMyBatisDaoImpl(new DataSource(driverClass, url, username, password));
	}

	@Bean
	public FixedDepositDao fixedDepositDao() {
		return new FixedDepositMyBatisDaoImpl(new DataSource(driverClass, url, username, password));
	}
}