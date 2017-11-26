package sample.spring.chapter06.bankapp.dao;

import sample.spring.chapter06.bankapp.domain.CustomerRequestDetails;

public interface CustomerRequestDao {
	void submitRequest(CustomerRequestDetails userRequestDetails);
} 
