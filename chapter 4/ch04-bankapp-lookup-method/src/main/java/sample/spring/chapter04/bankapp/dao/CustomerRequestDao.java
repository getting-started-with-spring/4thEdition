package sample.spring.chapter04.bankapp.dao;

import sample.spring.chapter04.bankapp.domain.CustomerRequestDetails;

public interface CustomerRequestDao {
	void submitRequest(CustomerRequestDetails userRequestDetails);
}
