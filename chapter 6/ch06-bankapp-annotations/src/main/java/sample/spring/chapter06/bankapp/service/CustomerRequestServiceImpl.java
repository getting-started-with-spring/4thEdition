package sample.spring.chapter06.bankapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sample.spring.chapter06.bankapp.dao.CustomerRequestDao;
import sample.spring.chapter06.bankapp.domain.CustomerRequestDetails;

@Service(value = "customerRequestService")
@Qualifier("service")
public class CustomerRequestServiceImpl implements CustomerRequestService {
	private static Logger logger = LogManager
			.getLogger(CustomerRequestServiceImpl.class);
	private CustomerRequestDetails customerRequestDetails;
	private CustomerRequestDao customerRequestDao;

	@Autowired
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
