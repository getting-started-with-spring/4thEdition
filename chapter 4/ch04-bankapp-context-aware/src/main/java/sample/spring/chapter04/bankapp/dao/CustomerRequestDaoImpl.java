package sample.spring.chapter04.bankapp.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.spring.chapter04.bankapp.domain.CustomerRequestDetails;

public class CustomerRequestDaoImpl implements CustomerRequestDao {
	private static Logger logger = LogManager.getLogger(CustomerRequestDaoImpl.class);
	
	public CustomerRequestDaoImpl() {
		logger.info("Created CustomerRequestDaoImpl instance");
	}
	
	@Override
	public void submitRequest(CustomerRequestDetails userRequestDetails) {
		// --save UserRequestDetails
	}

}
