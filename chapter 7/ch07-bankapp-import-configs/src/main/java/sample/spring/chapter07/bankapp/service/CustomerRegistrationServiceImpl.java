package sample.spring.chapter07.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import sample.spring.chapter07.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter07.bankapp.domain.CustomerRegistrationDetails;

public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

	// -- as CustomerRegistrationDetails is prototype-scoped, the same
	// instance is available throughout the lifetime of this bean instance
	@Autowired
	private CustomerRegistrationDetails customerRegistrationDetails;

	@Autowired
	private CustomerRegistrationDao customerRegistrationDao;

	public CustomerRegistrationDetails getCustomerRegistrationDetails() {
		return customerRegistrationDetails;
	}

	public CustomerRegistrationDao getCustomerRegistrationDao() {
		return customerRegistrationDao;
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
