package sample.spring.chapter11.bankapp.service;

import sample.spring.chapter11.bankapp.domain.BankAccountDetails;

public interface BankAccountService {
	int createBankAccount(BankAccountDetails bankAccountDetails);
	boolean isDuplicateAccount(BankAccountDetails bankAccountDetails);
}
