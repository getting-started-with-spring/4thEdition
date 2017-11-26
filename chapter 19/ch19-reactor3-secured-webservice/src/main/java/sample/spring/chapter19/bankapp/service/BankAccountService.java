package sample.spring.chapter19.bankapp.service;

import org.springframework.security.access.prepost.PreAuthorize;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.spring.chapter19.bankapp.domain.BankAccountDetails;

public interface BankAccountService {
	Mono<String> createBankAccount(BankAccountDetails bankAccountDetails);

	Mono<BankAccountDetails> saveBankAccount(BankAccountDetails bankAccountDetails);

	Mono<BankAccountDetails> findOne(String id);

	Mono<Long> countByBalance(int balance);

	Flux<BankAccountDetails> removeByBalance(int balance);

	Flux<BankAccountDetails> findByBalance(int balance);

	Flux<BankAccountDetails> findByFixedDepositsTenureLessThan(int tenure);

	Flux<BankAccountDetails> findByFixedDepositsFdAmountGreaterThan(int fdAmount);

	Flux<BankAccountDetails> findTop2ByOrderByFixedDepositsFdCreationDateDesc();

	Flux<BankAccountDetails> findTop2ByFixedDepositsTenure(int tenure);

	Flux<BankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(
			int tenure, int fdAmount);

	@PreAuthorize("hasRole('ADMIN')")
	Flux<BankAccountDetails> findByCustomQuery(int balance);
	
	Mono<Void> addFixedDeposit(String bankAccountId, int amount);
}
