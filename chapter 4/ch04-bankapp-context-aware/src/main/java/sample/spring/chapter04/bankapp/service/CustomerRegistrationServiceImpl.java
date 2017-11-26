package sample.spring.chapter04.bankapp.service;

import java.beans.ConstructorProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.spring.chapter04.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter04.bankapp.domain.CustomerRegistrationDetails;

public class CustomerRegistrationServiceImpl implements
		CustomerRegistrationService {
	private static Logger logger = LogManager
			.getLogger(CustomerRegistrationServiceImpl.class);

	private CustomerRegistrationDetails customerRegistrationDetails;
	private CustomerRegistrationDao customerRegistrationDao;

	@ConstructorProperties({ "customerRegistrationDetails",
			"customerRegistrationDao" })
	public CustomerRegistrationServiceImpl(
			CustomerRegistrationDetails customerRegistrationDetails,
			CustomerRegistrationDao customerRegistrationDao) {
		this.customerRegistrationDetails = customerRegistrationDetails;
		this.customerRegistrationDao = customerRegistrationDao;
		logger.info("Created CustomerRegistrationServiceImpl instance");
	}

	@Override
	public void setAccountNumber(String accountNumber) {
		customerRegistrationDetails.setAccountNumber(accountNumber);
	}

	@Override
	public void setAddress(String address) {
		customerRegistrationDetails.setAddress(address);
	}

	@Override
	public void setDebitCardNumber(String cardNumber) {
		customerRegistrationDetails.setCardNumber(cardNumber);
	}

	@Override
	public void register() {
		customerRegistrationDao.registerCustomer(customerRegistrationDetails);
	}

}
