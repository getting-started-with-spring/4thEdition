package sample.spring.chapter09.bankapp.dao;

import java.util.List;

import sample.spring.chapter09.bankapp.domain.BankAccountDetails;

public interface BankAccountDao {
	int createBankAccount(BankAccountDetails bankAccountDetails);
	List<BankAccountDetails> findAccountsWithBalanceGreaterThan(int balanceAmount);
	void subtractFromAccount(int bankAccountId, int amount);
}
