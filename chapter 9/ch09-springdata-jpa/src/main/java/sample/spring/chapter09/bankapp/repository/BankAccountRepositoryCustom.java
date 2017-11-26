package sample.spring.chapter09.bankapp.repository;

interface BankAccountRepositoryCustom {
	void subtractFromAccount(int bankAccountId, int amount);
}
