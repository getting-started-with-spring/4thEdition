package sample.spring.chapter07.bankapp;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import sample.spring.chapter07.bankapp.dao.TransactionDaoImpl;
import sample.spring.chapter07.bankapp.domain.CustomerRegistrationDetails;
import sample.spring.chapter07.bankapp.service.TransactionServiceImpl;

@Import({ TransactionServiceImpl.class, TransactionDaoImpl.class })
public class BankOtherObjects {
	@Bean(name = "customerRegistrationDetails")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CustomerRegistrationDetails customerRegistrationDetails() {
		return new CustomerRegistrationDetails();
	}
}
