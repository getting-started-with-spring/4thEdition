package sample.spring.chapter06.bankapp.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component(value="customerRegistrationDetails")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerRegistrationDetails {
	private static Logger logger = LogManager
			.getLogger(CustomerRegistrationDetails.class);
	private String accountNumber;
	private String address;
	private String cardNumber;

	public CustomerRegistrationDetails() {
		logger.info("Created CustomerRegistrationDetails instance");
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
