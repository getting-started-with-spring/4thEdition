package sample.spring.chapter10.bankapp.dao;

import sample.spring.chapter10.bankapp.domain.BankAccountDetails;

public interface BankAccountDao {
	int createBankAccount(BankAccountDetails bankAccountDetails);
	void subtractFromAccount(int bankAccountId, int amount);
}
