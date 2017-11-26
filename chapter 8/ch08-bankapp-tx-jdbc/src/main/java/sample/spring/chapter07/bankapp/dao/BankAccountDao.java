package sample.spring.chapter07.bankapp.dao;

import sample.spring.chapter07.bankapp.domain.BankAccountDetails;

public interface BankAccountDao {
	int createBankAccount(BankAccountDetails bankAccountDetails);
	void subtractFromAccount(int bankAccountId, int amount);
}
