package sample.spring.chapter09.bankapp.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import sample.spring.chapter09.bankapp.domain.BankAccountDetails;

public interface BankAccountService {
	void subtractFromAccount(String bankAccountId, int amount);
	
	String createBankAccount(BankAccountDetails bankAccountDetails);

	Optional<BankAccountDetails> findOne(String id);

	long countByBalance(int balance);

	List<BankAccountDetails> removeByBalance(int balance);

	List<BankAccountDetails> findByBalance(int balance);

	List<BankAccountDetails> findByFixedDepositsTenureLessThan(int tenure);

	List<BankAccountDetails> findByFixedDepositsFdAmountGreaterThan(int fdAmount);

	List<BankAccountDetails> findTop2ByOrderByFixedDepositsFdCreationDateDesc();

	List<BankAccountDetails> findTop2ByFixedDepositsTenure(int tenure);

	List<BankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(int tenure, int fdAmount);

	CompletableFuture<List<BankAccountDetails>> findAllByBalanceGreaterThan(int balance);
	
	List<BankAccountDetails> findByCustomQuery(int balance);
	
	Iterable<BankAccountDetails> getHighValueFds();
	
	Iterable<BankAccountDetails> getAllBankAccountsWithoutFds();
	
}
