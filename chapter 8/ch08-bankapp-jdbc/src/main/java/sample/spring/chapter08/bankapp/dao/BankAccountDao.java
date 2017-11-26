package sample.spring.chapter08.bankapp.dao;

import sample.spring.chapter08.bankapp.domain.BankAccountDetails;

public interface BankAccountDao {
	int createBankAccount(BankAccountDetails bankAccountDetails);
	void subtractFromAccount(int bankAccountId, int amount);
}
