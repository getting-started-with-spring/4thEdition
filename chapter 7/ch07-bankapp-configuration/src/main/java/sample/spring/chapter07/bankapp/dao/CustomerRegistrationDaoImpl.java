package sample.spring.chapter07.bankapp.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.spring.chapter07.bankapp.domain.CustomerRegistrationDetails;

public class CustomerRegistrationDaoImpl implements CustomerRegistrationDao {
	private static Logger logger = LogManager.getLogger(CustomerRegistrationDaoImpl.class);

	@Override
	public void registerCustomer(CustomerRegistrationDetails customerRegistrationDetails) {
		logger.info("Registering customer");
	}
}
