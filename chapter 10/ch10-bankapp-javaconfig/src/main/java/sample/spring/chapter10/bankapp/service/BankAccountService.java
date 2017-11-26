package sample.spring.chapter10.bankapp.service;

import sample.spring.chapter10.bankapp.domain.BankAccountDetails;

public interface BankAccountService {
	int createBankAccount(BankAccountDetails bankAccountDetails);
}
