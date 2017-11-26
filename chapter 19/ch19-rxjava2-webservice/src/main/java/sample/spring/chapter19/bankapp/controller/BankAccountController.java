package sample.spring.chapter19.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import sample.spring.chapter19.bankapp.domain.BankAccountDetails;
import sample.spring.chapter19.bankapp.service.BankAccountService;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {
	@Autowired
	private BankAccountService bankAccountService;

	@PostMapping("/createBankAccount")
	public Single<String> createBankAccount(@RequestBody BankAccountDetails bankAccountDetails) {
		return bankAccountService.createBankAccount(bankAccountDetails);
	}

	@PostMapping("/saveBankAccount")
	public Single<BankAccountDetails> saveBankAccount(@RequestBody BankAccountDetails bankAccountDetails) {
		return bankAccountService.saveBankAccount(bankAccountDetails);
	}

	@GetMapping("/findOne/{id}")
	public Maybe<BankAccountDetails> findOne(@PathVariable("id") String bankAccountId) {
		return bankAccountService.findOne(bankAccountId);
	}

	@GetMapping("/countByBalance/{balance}")
	public Single<Long> countByBalance(@PathVariable("balance") int balance) {
		return bankAccountService.countByBalance(balance);
	}

	@PutMapping("/removeByBalance/{balance}")
	public Flowable<BankAccountDetails> removeByBalance(@PathVariable("balance") int balance) {
		return bankAccountService.removeByBalance(balance);
	}

	@GetMapping("/findByBalance/{balance}")
	public Flowable<BankAccountDetails> findByBalance(@PathVariable("balance") int balance) {
		return bankAccountService.findByBalance(balance);
	}

	@GetMapping("/findByFixedDepositsTenureLessThan/{tenure}")
	public Flowable<BankAccountDetails> findByFixedDepositsTenureLessThan(@PathVariable("tenure") int tenure) {
		return bankAccountService.findByFixedDepositsTenureLessThan(tenure);
	}

	@GetMapping("/findByFixedDepositsFdAmountGreaterThan/{fdAmount}")
	public Flowable<BankAccountDetails> findByFixedDepositsFdAmountGreaterThan(@PathVariable("fdAmount") int fdAmount) {
		return bankAccountService.findByFixedDepositsFdAmountGreaterThan(fdAmount);
	}

	@GetMapping("/findTop2ByOrderByFixedDepositsFdCreationDateDesc")
	public Flowable<BankAccountDetails> findTop2ByOrderByFixedDepositsFdCreationDateDesc() {
		return bankAccountService.findTop2ByOrderByFixedDepositsFdCreationDateDesc();
	}

	@GetMapping("/findTop2ByFixedDepositsTenure/{tenure}")
	public Flowable<BankAccountDetails> findTop2ByFixedDepositsTenure(@PathVariable("tenure") int tenure) {
		return bankAccountService.findTop2ByFixedDepositsTenure(tenure);
	}

	@GetMapping("/findByFixedDepositsTenureAndFixedDepositsFdAmount/{tenure}/{fdAmount}")
	public Flowable<BankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(
			@PathVariable("tenure") int tenure, @PathVariable("fdAmount") int fdAmount) {
		return bankAccountService.findByFixedDepositsTenureAndFixedDepositsFdAmount(tenure, fdAmount);
	}

	@GetMapping(path = "/findByCustomQuerySse/{balance}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flowable<BankAccountDetails> findByCustomQuerySse(@PathVariable("balance") int balance) {
		return bankAccountService.findByCustomQuery(balance).doOnNext(account -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	@GetMapping("/findByCustomQuery/{balance}")
	public Flowable<BankAccountDetails> findByCustomQuery(@PathVariable("balance") int balance) {
		return bankAccountService.findByCustomQuery(balance);
	}

	@PutMapping("/addFixedDeposit/{bankAccountId}/{amount}")
	public Completable addFixedDeposit(@PathVariable("bankAccountId") String bankAccountId,
			@PathVariable("amount") int amount) {
		return bankAccountService.addFixedDeposit(bankAccountId, amount);
	}
}
