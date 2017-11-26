package sample.spring.chapter04.bankapp.service;

import java.beans.ConstructorProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.spring.chapter04.bankapp.dao.CustomerRequestDao;
import sample.spring.chapter04.bankapp.domain.CustomerRequestDetails;

public class CustomerRequestServiceImpl implements CustomerRequestService {
	private static Logger logger = LogManager
			.getLogger(CustomerRequestServiceImpl.class);
	private CustomerRequestDetails customerRequestDetails;
	private CustomerRequestDao customerRequestDao;

	@ConstructorProperties({ "customerRequestDetails", "customerRequestDao" })
	public CustomerRequestServiceImpl(
			CustomerRequestDetails customerRequestDetails,
			CustomerRequestDao customerRequestDao) {
		logger.info("Created CustomerRequestServiceImpl instance");
		this.customerRequestDetails = customerRequestDetails;
		this.customerRequestDao = customerRequestDao;
	}

	@Override
	public void submitRequest(String requestType, String requestDescription) {
		// -- create an instance of UserRequestDetails and save it
		customerRequestDao.submitRequest(customerRequestDetails);
	}

}
