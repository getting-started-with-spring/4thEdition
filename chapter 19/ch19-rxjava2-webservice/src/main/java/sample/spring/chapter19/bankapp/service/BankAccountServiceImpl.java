package sample.spring.chapter19.bankapp.service;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.adapter.rxjava.RxJava2Adapter;
import sample.spring.chapter19.bankapp.domain.BankAccountDetails;
import sample.spring.chapter19.bankapp.repository.BankAccountRxJava2Repository;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRxJava2Repository bankAccountRepository;

	@Override
	public Single<String> createBankAccount(
			BankAccountDetails bankAccountDetails) {
		return RxJava2Adapter.monoToSingle(bankAccountRepository.save(
				bankAccountDetails).map(e -> e.getAccountId()));
	}

	@Override
	public Single<BankAccountDetails> saveBankAccount(
			BankAccountDetails bankAccountDetails) {
		return RxJava2Adapter.monoToSingle(bankAccountRepository
				.save(bankAccountDetails));
	}

	@Override
	public Maybe<BankAccountDetails> findOne(String id) {
		return RxJava2Adapter.monoToMaybe(bankAccountRepository.findById(id));
	}

	@Override
	public Single<Long> countByBalance(int balance) {
		return bankAccountRepository.countByBalance(balance);
	}

	@Override
	public Flowable<BankAccountDetails> removeByBalance(int balance) {
		return bankAccountRepository.removeByBalance(balance);
	}

	@Override
	public Flowable<BankAccountDetails> findByBalance(int balance) {
		return bankAccountRepository.findByBalance(balance);
	}

	@Override
	public Flowable<BankAccountDetails> findByFixedDepositsTenureLessThan(
			int tenure) {
		return bankAccountRepository.findByFixedDepositsTenureLessThan(tenure);
	}

	@Override
	public Flowable<BankAccountDetails> findByFixedDepositsFdAmountGreaterThan(
			int fdAmount) {
		return bankAccountRepository
				.findByFixedDepositsFdAmountGreaterThan(fdAmount);
	}

	@Override
	public Flowable<BankAccountDetails> findTop2ByOrderByFixedDepositsFdCreationDateDesc() {
		return bankAccountRepository
				.findTop2ByOrderByFixedDepositsFdCreationDateDesc();
	}

	@Override
	public Flowable<BankAccountDetails> findTop2ByFixedDepositsTenure(int tenure) {
		return bankAccountRepository.findTop2ByFixedDepositsTenure(tenure);
	}

	@Override
	public Flowable<BankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(
			int tenure, int fdAmount) {
		return bankAccountRepository
				.findByFixedDepositsTenureAndFixedDepositsFdAmount(tenure,
						fdAmount);
	}

	@Override
	public Flowable<BankAccountDetails> findByCustomQuery(int balance) {
		return bankAccountRepository.findByCustomQuery(balance);
	}

	@Override
	public Completable addFixedDeposit(String bankAccountId, int amount) {
		return bankAccountRepository.addFixedDeposit(bankAccountId, amount);
	}
}