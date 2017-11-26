package sample.spring.chapter08.bankapp.service;

import sample.spring.chapter08.bankapp.domain.BankAccountDetails;

public interface BankAccountService {
	int createBankAccount(BankAccountDetails bankAccountDetails);
}
