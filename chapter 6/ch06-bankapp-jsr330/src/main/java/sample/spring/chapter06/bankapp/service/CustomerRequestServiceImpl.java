package sample.spring.chapter06.bankapp.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.spring.chapter06.bankapp.dao.CustomerRequestDao;
import sample.spring.chapter06.bankapp.domain.CustomerRequestDetails;

@Named(value = "customerRequestService")
public class CustomerRequestServiceImpl implements CustomerRequestService {
	private static Logger logger = LogManager
			.getLogger(CustomerRequestServiceImpl.class);
	private CustomerRequestDetails customerRequestDetails;
	private CustomerRequestDao customerRequestDao;

	@Inject
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
