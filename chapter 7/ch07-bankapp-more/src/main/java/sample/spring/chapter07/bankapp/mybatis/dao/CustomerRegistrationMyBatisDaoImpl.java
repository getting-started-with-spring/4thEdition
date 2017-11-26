package sample.spring.chapter07.bankapp.mybatis.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.spring.chapter07.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter07.bankapp.domain.CustomerRegistrationDetails;
import sample.spring.chapter07.bankapp.domain.DataSource;

public class CustomerRegistrationMyBatisDaoImpl implements CustomerRegistrationDao {
	private static Logger logger = LogManager.getLogger(CustomerRegistrationMyBatisDaoImpl.class);

	public CustomerRegistrationMyBatisDaoImpl(DataSource dataSource) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void registerCustomer(CustomerRegistrationDetails customerRegistrationDetails) {
		logger.info("Registering customer");
	}

}
