package sample.spring.chapter07.bankapp.hibernate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.spring.chapter07.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter07.bankapp.domain.CustomerRegistrationDetails;
import sample.spring.chapter07.bankapp.domain.DataSource;

public class CustomerRegistrationHibernateDaoImpl implements CustomerRegistrationDao {
	private static Logger logger = LogManager.getLogger(CustomerRegistrationHibernateDaoImpl.class);
	
	public CustomerRegistrationHibernateDaoImpl(DataSource dataSource) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void registerCustomer(CustomerRegistrationDetails customerRegistrationDetails) {
		logger.info("Registering customer");
	}

}
