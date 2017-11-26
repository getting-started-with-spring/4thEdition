package sample.spring.chapter06.bankapp.dao;

import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import sample.spring.chapter06.bankapp.domain.CustomerRegistrationDetails;

@Named(value="customerRegistrationDao")
public class CustomerRegistrationDaoImpl implements CustomerRegistrationDao {
	private static Logger logger = LogManager
			.getLogger(CustomerRegistrationDaoImpl.class);

	public CustomerRegistrationDaoImpl() {
		logger.info("Created CustomerRegistrationDaoImpl instance");
	}

	@Override
	public void registerCustomer(
			CustomerRegistrationDetails customerRegistrationDetails) {
	}

}
