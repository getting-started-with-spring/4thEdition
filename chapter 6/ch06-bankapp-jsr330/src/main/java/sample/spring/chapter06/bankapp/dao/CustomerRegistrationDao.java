package sample.spring.chapter06.bankapp.dao;

import sample.spring.chapter06.bankapp.domain.CustomerRegistrationDetails;

public interface CustomerRegistrationDao {
	void registerCustomer(
			CustomerRegistrationDetails customerRegistrationDetails);
}
