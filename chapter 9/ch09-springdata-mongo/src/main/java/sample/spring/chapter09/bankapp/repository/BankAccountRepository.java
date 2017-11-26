package sample.spring.chapter09.bankapp.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.scheduling.annotation.Async;

import sample.spring.chapter09.bankapp.domain.BankAccountDetails;

public interface BankAccountRepository
		extends MongoRepository<BankAccountDetails, String>, QuerydslPredicateExecutor<BankAccountDetails>, BankAccountRepositoryCustom {

	long countByBalance(int balance);

	List<BankAccountDetails> removeByBalance(int balance);

	List<BankAccountDetails> findByBalance(int balance);

	List<BankAccountDetails> findByFixedDepositsTenureLessThan(int tenure);

	List<BankAccountDetails> findByFixedDepositsFdAmountGreaterThan(int fdAmount);

	List<BankAccountDetails> findTop2ByOrderByFixedDepositsFdCreationDateDesc();

	List<BankAccountDetails> findTop2ByFixedDepositsTenure(int tenure);

	List<BankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(int tenure, int fdAmount);

	@Async
	CompletableFuture<List<BankAccountDetails>> findAllByBalanceGreaterThan(int balance);

	@Query("{'balance' : {'$lte' : ?0} }")
	List<BankAccountDetails> findByCustomQuery(int balance);
}
