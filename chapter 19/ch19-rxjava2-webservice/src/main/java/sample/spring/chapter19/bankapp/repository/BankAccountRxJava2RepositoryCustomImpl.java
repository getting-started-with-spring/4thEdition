package sample.spring.chapter19.bankapp.repository;

import io.reactivex.Completable;
import io.reactivex.Maybe;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import reactor.adapter.rxjava.RxJava2Adapter;
import sample.spring.chapter19.bankapp.domain.BankAccountDetails;
import sample.spring.chapter19.bankapp.domain.FixedDepositDetails;

public class BankAccountRxJava2RepositoryCustomImpl implements BankAccountRxJava2RepositoryCustom {
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	@Override
	public Completable addFixedDeposit(String bankAccountId, int amount) {
		return RxJava2Adapter.monoToCompletable(mongoTemplate.findById(bankAccountId, BankAccountDetails.class)
				.map(account -> addFD(account, amount).subscribe()).then());
	}

	private Maybe<BankAccountDetails> addFD(BankAccountDetails bankAccountDetails, int amount) {
		if (bankAccountDetails.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance amount in bank account");
		}

		FixedDepositDetails fd2 = new FixedDepositDetails();
		fd2.setActive("Y");
		fd2.setFdAmount(700);
		fd2.setFdCreationDate(new Date());
		fd2.setTenure(7);

		bankAccountDetails.addFixedDeposit(fd2);
		bankAccountDetails.setBalance(bankAccountDetails.getBalance() - amount);
		return RxJava2Adapter.monoToMaybe(mongoTemplate.save(bankAccountDetails));
	}
}
