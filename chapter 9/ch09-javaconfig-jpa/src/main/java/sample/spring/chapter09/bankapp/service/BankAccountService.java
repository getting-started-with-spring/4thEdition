package sample.spring.chapter09.bankapp.service;

import java.util.List;

import sample.spring.chapter09.bankapp.domain.BankAccountDetails;

public interface BankAccountService {
	int createBankAccount(BankAccountDetails bankAccountDetails);

	List<BankAccountDetails> findAccountsWithBalanceGreaterThan(int balanceAmount);
}
