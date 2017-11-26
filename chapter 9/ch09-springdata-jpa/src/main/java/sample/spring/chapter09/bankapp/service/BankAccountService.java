package sample.spring.chapter09.bankapp.service;

import sample.spring.chapter09.bankapp.domain.BankAccountDetails;

public interface BankAccountService {
	int createBankAccount(BankAccountDetails bankAccountDetails);
}
