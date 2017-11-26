package sample.spring.chapter04.bankapp.service;

import sample.spring.chapter04.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter04.bankapp.domain.CustomerRegistrationDetails;

public class CustomerRegistrationServiceImpl implements
		CustomerRegistrationService {

	private CustomerRegistrationDetails customerRegistrationDetails;
	private CustomerRegistrationDao customerRegistrationDao;

	public CustomerRegistrationDetails getCustomerRegistrationDetails() {
		return customerRegistrationDetails;
	}

	public void setCustomerRegistrationDetails(
			CustomerRegistrationDetails customerRegistrationDetails) {
		this.customerRegistrationDetails = customerRegistrationDetails;
	}

	public CustomerRegistrationDao getCustomerRegistrationDao() {
		return customerRegistrationDao;
	}

	public void setCustomerRegistrationDao(
			CustomerRegistrationDao customerRegistrationDao) {
		this.customerRegistrationDao = customerRegistrationDao;
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
