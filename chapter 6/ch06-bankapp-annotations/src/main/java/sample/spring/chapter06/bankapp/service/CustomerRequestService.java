package sample.spring.chapter06.bankapp.service;

public interface CustomerRequestService extends MyService {
	void submitRequest(String requestType, String requestDescription);
}
