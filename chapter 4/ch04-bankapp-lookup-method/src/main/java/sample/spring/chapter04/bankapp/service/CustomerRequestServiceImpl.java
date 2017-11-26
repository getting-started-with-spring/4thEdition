package sample.spring.chapter04.bankapp.service;

import java.beans.ConstructorProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.spring.chapter04.bankapp.dao.CustomerRequestDao;
import sample.spring.chapter04.bankapp.domain.CustomerRequestDetails;

public abstract class CustomerRequestServiceImpl implements
		CustomerRequestService {
	private static Logger logger = LogManager
			.getLogger(CustomerRequestServiceImpl.class);
	private CustomerRequestDao customerRequestDao;

	@ConstructorProperties({ "customerRequestDao" })
	public CustomerRequestServiceImpl(CustomerRequestDao customerRequestDao) {
		logger.info("Created CustomerRequestServiceImpl instance");
		this.customerRequestDao = customerRequestDao;
	}

	public abstract CustomerRequestDetails getCustomerRequestDetails();

	@Override
	public void submitRequest(String requestType, String requestDescription) {
		// -- populate CustomerRequestDetails object and save it
		CustomerRequestDetails customerRequestDetails = getCustomerRequestDetails();
		customerRequestDetails.setType(requestType);
		customerRequestDetails.setDescription(requestDescription);
		customerRequestDao.submitRequest(customerRequestDetails);
	}
}
