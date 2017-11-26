package sample.spring.chapter04.bankapp.dao;

import sample.spring.chapter04.bankapp.domain.CustomerRegistrationDetails;

public interface CustomerRegistrationDao {
	void registerCustomer(
			CustomerRegistrationDetails customerRegistrationDetails);
}
