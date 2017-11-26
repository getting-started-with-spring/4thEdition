package sample.spring.chapter06.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import sample.spring.chapter06.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter06.bankapp.domain.CustomerRegistrationDetails;

@Service("customerRegistrationService")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("service")
public class CustomerRegistrationServiceImpl implements
		CustomerRegistrationService {

	private CustomerRegistrationDetails customerRegistrationDetails;

	@Autowired
	private CustomerRegistrationDao customerRegistrationDao;

	@Autowired
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
