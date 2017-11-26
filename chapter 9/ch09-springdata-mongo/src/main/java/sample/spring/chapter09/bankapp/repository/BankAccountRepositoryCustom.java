package sample.spring.chapter09.bankapp.repository;

interface BankAccountRepositoryCustom {
	void subtractFromAccount(String bankAccountId, int amount);
}
