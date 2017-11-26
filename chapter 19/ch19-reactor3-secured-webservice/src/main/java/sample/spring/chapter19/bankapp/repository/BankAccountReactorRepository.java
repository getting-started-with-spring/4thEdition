package sample.spring.chapter19.bankapp.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.spring.chapter19.bankapp.domain.BankAccountDetails;

public interface BankAccountReactorRepository
		extends ReactiveMongoRepository<BankAccountDetails, String>, BankAccountReactorRepositoryCustom {

	Mono<Long> countByBalance(int balance);

	Flux<BankAccountDetails> removeByBalance(int balance);
	
	Flux<BankAccountDetails> findByBalance(int balance);

	Flux<BankAccountDetails> findByFixedDepositsTenureLessThan(int tenure);

	Flux<BankAccountDetails> findByFixedDepositsFdAmountGreaterThan(int fdAmount);

	Flux<BankAccountDetails> findTop2ByOrderByFixedDepositsFdCreationDateDesc();

	Flux<BankAccountDetails> findTop2ByFixedDepositsTenure(int tenure);

	Flux<BankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(int tenure, int fdAmount);

	@Query("{ 'balance' : {'$lte' : ?0} }")
	Flux<BankAccountDetails> findByCustomQuery(int balance);
}
