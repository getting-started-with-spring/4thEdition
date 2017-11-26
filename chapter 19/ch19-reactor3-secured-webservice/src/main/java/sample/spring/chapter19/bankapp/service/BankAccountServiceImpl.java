package sample.spring.chapter19.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.spring.chapter19.bankapp.domain.BankAccountDetails;
import sample.spring.chapter19.bankapp.repository.BankAccountReactorRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountReactorRepository bankAccountRepository;

	@Override
	public Mono<String> createBankAccount(BankAccountDetails bankAccountDetails) {
		return bankAccountRepository.save(bankAccountDetails).map(
				e -> e.getAccountId());
	}

	@Override
	public Mono<BankAccountDetails> saveBankAccount(
			BankAccountDetails bankAccountDetails) {
		return bankAccountRepository.save(bankAccountDetails);
	}

	@Override
	public Mono<BankAccountDetails> findOne(String id) {
		return bankAccountRepository.findById(id);
	}

	@Override
	public Mono<Long> countByBalance(int balance) {
		return bankAccountRepository.countByBalance(balance);
	}

	@Override
	public Flux<BankAccountDetails> removeByBalance(int balance) {
		return bankAccountRepository.removeByBalance(balance);
	}

	@Override
	public Flux<BankAccountDetails> findByBalance(int balance) {
		return bankAccountRepository.findByBalance(balance);
	}

	@Override
	public Flux<BankAccountDetails> findByFixedDepositsTenureLessThan(int tenure) {
		return bankAccountRepository.findByFixedDepositsTenureLessThan(tenure);
	}

	@Override
	public Flux<BankAccountDetails> findByFixedDepositsFdAmountGreaterThan(
			int fdAmount) {
		return bankAccountRepository
				.findByFixedDepositsFdAmountGreaterThan(fdAmount);
	}

	@Override
	public Flux<BankAccountDetails> findTop2ByOrderByFixedDepositsFdCreationDateDesc() {
		return bankAccountRepository
				.findTop2ByOrderByFixedDepositsFdCreationDateDesc();
	}

	@Override
	public Flux<BankAccountDetails> findTop2ByFixedDepositsTenure(int tenure) {
		return bankAccountRepository.findTop2ByFixedDepositsTenure(tenure);
	}

	@Override
	public Flux<BankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(
			int tenure, int fdAmount) {
		return bankAccountRepository
				.findByFixedDepositsTenureAndFixedDepositsFdAmount(tenure,
						fdAmount);
	}

	@Override
	public Flux<BankAccountDetails> findByCustomQuery(int balance) {
		return bankAccountRepository.findByCustomQuery(balance);
	}

	@Override
	public Mono<Void> addFixedDeposit(String bankAccountId, int amount) {
		return bankAccountRepository.addFixedDeposit(bankAccountId, amount);
	}
}