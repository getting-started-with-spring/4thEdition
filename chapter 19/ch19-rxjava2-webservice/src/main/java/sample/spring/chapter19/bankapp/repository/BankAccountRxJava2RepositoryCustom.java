package sample.spring.chapter19.bankapp.repository;

import io.reactivex.Completable;

interface BankAccountRxJava2RepositoryCustom {
	Completable addFixedDeposit(String bankAccountId, int amount);
}
