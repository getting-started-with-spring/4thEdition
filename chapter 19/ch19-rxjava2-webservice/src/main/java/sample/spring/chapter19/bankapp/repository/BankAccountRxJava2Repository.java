package sample.spring.chapter19.bankapp.repository;

import io.reactivex.Flowable;
import io.reactivex.Single;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import sample.spring.chapter19.bankapp.domain.BankAccountDetails;

public interface BankAccountRxJava2Repository
		extends ReactiveMongoRepository<BankAccountDetails, String>, BankAccountRxJava2RepositoryCustom {

	Single<Long> countByBalance(int balance);

	Flowable<BankAccountDetails> removeByBalance(int balance);
	
	Flowable<BankAccountDetails> findByBalance(int balance);

	Flowable<BankAccountDetails> findByFixedDepositsTenureLessThan(int tenure);

	Flowable<BankAccountDetails> findByFixedDepositsFdAmountGreaterThan(int fdAmount);

	Flowable<BankAccountDetails> findTop2ByOrderByFixedDepositsFdCreationDateDesc();

	Flowable<BankAccountDetails> findTop2ByFixedDepositsTenure(int tenure);

	Flowable<BankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(int tenure, int fdAmount);

	@Query("{ 'balance' : {'$lte' : ?0} }")
	Flowable<BankAccountDetails> findByCustomQuery(int balance);
}
