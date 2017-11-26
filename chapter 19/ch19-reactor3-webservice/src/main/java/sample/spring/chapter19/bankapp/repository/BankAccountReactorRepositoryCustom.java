package sample.spring.chapter19.bankapp.repository;

import reactor.core.publisher.Mono;

interface BankAccountReactorRepositoryCustom {
	Mono<Void> addFixedDeposit(String bankAccountId, int amount);
}
