package sample.spring.chapter07.bankapp.dao;

import sample.spring.chapter07.bankapp.domain.CustomerRegistrationDetails;

public interface CustomerRegistrationDao {
	void registerCustomer(
			CustomerRegistrationDetails customerRegistrationDetails);
}
