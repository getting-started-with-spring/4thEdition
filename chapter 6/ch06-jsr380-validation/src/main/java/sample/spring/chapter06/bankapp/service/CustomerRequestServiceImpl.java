package sample.spring.chapter06.bankapp.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.spring.chapter06.bankapp.dao.CustomerRequestDao;
import sample.spring.chapter06.bankapp.domain.CustomerRequestDetails;

@Service("customerRequestService")
public class CustomerRequestServiceImpl implements CustomerRequestService {
	@Autowired
	private CustomerRequestDao customerRequestDao;

	@Override
	public Calendar submitRequest(String type, String description, Calendar accountSinceDate) {
		CustomerRequestDetails details = new CustomerRequestDetails();
		details.setType(type);
		details.setDescription(description);
		customerRequestDao.submitRequest(details);
		details.setAccountSinceDate(accountSinceDate);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return cal;
	}
}
