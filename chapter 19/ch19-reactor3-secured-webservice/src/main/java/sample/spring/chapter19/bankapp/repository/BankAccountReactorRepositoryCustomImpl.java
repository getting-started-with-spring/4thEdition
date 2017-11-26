package sample.spring.chapter19.bankapp.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import reactor.core.publisher.Mono;
import sample.spring.chapter19.bankapp.domain.BankAccountDetails;
import sample.spring.chapter19.bankapp.domain.FixedDepositDetails;

public class BankAccountReactorRepositoryCustomImpl implements BankAccountReactorRepositoryCustom {
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	@Override
	public Mono<Void> addFixedDeposit(String bankAccountId, int amount) {
		return mongoTemplate.findById(bankAccountId, BankAccountDetails.class)
				.map(account -> addFD(account, amount).subscribe()).then();
	}

	private Mono<BankAccountDetails> addFD(BankAccountDetails bankAccountDetails, int amount) {
		if (bankAccountDetails.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance amount in bank account");
		}

		FixedDepositDetails fd2 = new FixedDepositDetails();
		fd2.setActive("Y");
		fd2.setFdAmount(amount);
		fd2.setFdCreationDate(new Date());
		fd2.setTenure(7);

		bankAccountDetails.addFixedDeposit(fd2);
		bankAccountDetails.setBalance(bankAccountDetails.getBalance() - amount);
		return mongoTemplate.save(bankAccountDetails);
	}
}
