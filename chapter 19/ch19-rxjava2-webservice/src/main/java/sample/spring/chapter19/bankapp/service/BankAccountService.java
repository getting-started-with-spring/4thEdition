package sample.spring.chapter19.bankapp.service;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import sample.spring.chapter19.bankapp.domain.BankAccountDetails;

public interface BankAccountService {
	Single<String> createBankAccount(BankAccountDetails bankAccountDetails);

	Single<BankAccountDetails> saveBankAccount(
			BankAccountDetails bankAccountDetails);

	Maybe<BankAccountDetails> findOne(String id);

	Single<Long> countByBalance(int balance);

	Flowable<BankAccountDetails> removeByBalance(int balance);

	Flowable<BankAccountDetails> findByBalance(int balance);

	Flowable<BankAccountDetails> findByFixedDepositsTenureLessThan(int tenure);

	Flowable<BankAccountDetails> findByFixedDepositsFdAmountGreaterThan(
			int fdAmount);

	Flowable<BankAccountDetails> findTop2ByOrderByFixedDepositsFdCreationDateDesc();

	Flowable<BankAccountDetails> findTop2ByFixedDepositsTenure(int tenure);

	Flowable<BankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(
			int tenure, int fdAmount);

	Flowable<BankAccountDetails> findByCustomQuery(int balance);

	Completable addFixedDeposit(String bankAccountId, int amount);
}
