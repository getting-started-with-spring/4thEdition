package sample.spring.chapter06.bankapp.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import sample.spring.chapter06.bankapp.domain.CustomerRequestDetails;

@Repository(value = "customerRequestDao")
public class CustomerRequestDaoImpl implements CustomerRequestDao {
	private static Logger logger = LogManager
			.getLogger(CustomerRequestDaoImpl.class);

	public CustomerRequestDaoImpl() {
		logger.info("Created CustomerRequestDaoImpl instance");
	}

	@Override
	public void submitRequest(CustomerRequestDetails userRequestDetails) {
		
	}
}
