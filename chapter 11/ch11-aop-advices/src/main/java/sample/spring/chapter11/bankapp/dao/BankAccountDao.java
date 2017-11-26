package sample.spring.chapter11.bankapp.dao;

import sample.spring.chapter11.bankapp.domain.BankAccountDetails;

public interface BankAccountDao {
	int createBankAccount(BankAccountDetails bankAccountDetails);
	void subtractFromAccount(int bankAccountId, int amount);
}
