package sample.spring.chapter06.bankapp.service;

public interface CustomerRegistrationService {
	void setAccountNumber(String accountNumber);
	void setAddress(String address);
	void setDebitCardNumber(String cardNumber);
	void register();
}
