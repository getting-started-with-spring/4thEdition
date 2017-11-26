package sample.spring.chapter06.bankapp.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import sample.spring.chapter06.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter06.bankapp.domain.CustomerRegistrationDetails;

@Named("customerRegistrationService")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerRegistrationServiceImpl implements
		CustomerRegistrationService {

	private CustomerRegistrationDetails customerRegistrationDetails;

	@Inject
	private CustomerRegistrationDao customerRegistrationDao;

	@Inject
	public void obtainCustomerRegistrationDetails(
			CustomerRegistrationDetails customerRegistrationDetails) {
		this.customerRegistrationDetails = customerRegistrationDetails;
	}

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
