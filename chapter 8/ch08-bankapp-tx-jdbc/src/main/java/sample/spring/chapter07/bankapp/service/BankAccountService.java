package sample.spring.chapter07.bankapp.service;

import sample.spring.chapter07.bankapp.domain.BankAccountDetails;

public interface BankAccountService {
	int createBankAccount(BankAccountDetails bankAccountDetails);
}
