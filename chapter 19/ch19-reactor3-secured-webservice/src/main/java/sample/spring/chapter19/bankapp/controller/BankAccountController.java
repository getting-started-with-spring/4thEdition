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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.spring.chapter19.bankapp.domain.BankAccountDetails;
import sample.spring.chapter19.bankapp.service.BankAccountService;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {
	@Autowired
	private BankAccountService bankAccountService;

	@PostMapping("/createBankAccount")
	public Mono<String> createBankAccount(@RequestBody BankAccountDetails bankAccountDetails) {
		return bankAccountService.createBankAccount(bankAccountDetails);
	}

	@PostMapping("/saveBankAccount")
	public Mono<BankAccountDetails> saveBankAccount(@RequestBody BankAccountDetails bankAccountDetails) {
		return bankAccountService.saveBankAccount(bankAccountDetails);
	}

	@GetMapping("/findOne/{id}")
	public Mono<BankAccountDetails> findOne(@PathVariable("id") String bankAccountId) {
		return bankAccountService.findOne(bankAccountId);
	}

	@GetMapping("/countByBalance/{balance}")
	public Mono<Long> countByBalance(@PathVariable("balance") int balance) {
		return bankAccountService.countByBalance(balance);
	}

	@PutMapping("/removeByBalance/{balance}")
	public Flux<BankAccountDetails> removeByBalance(@PathVariable("balance") int balance) {
		return bankAccountService.removeByBalance(balance);
	}

	@GetMapping("/findByBalance/{balance}")
	public Flux<BankAccountDetails> findByBalance(@PathVariable("balance") int balance) {
		return bankAccountService.findByBalance(balance);
	}

	@GetMapping("/findByFixedDepositsTenureLessThan/{tenure}")
	public Flux<BankAccountDetails> findByFixedDepositsTenureLessThan(@PathVariable("tenure") int tenure) {
		return bankAccountService.findByFixedDepositsTenureLessThan(tenure);
	}

	@GetMapping("/findByFixedDepositsFdAmountGreaterThan/{fdAmount}")
	public Flux<BankAccountDetails> findByFixedDepositsFdAmountGreaterThan(@PathVariable("fdAmount") int fdAmount) {
		return bankAccountService.findByFixedDepositsFdAmountGreaterThan(fdAmount);
	}

	@GetMapping("/findTop2ByOrderByFixedDepositsFdCreationDateDesc")
	public Flux<BankAccountDetails> findTop2ByOrderByFixedDepositsFdCreationDateDesc() {
		return bankAccountService.findTop2ByOrderByFixedDepositsFdCreationDateDesc();
	}

	@GetMapping("/findTop2ByFixedDepositsTenure/{tenure}")
	public Flux<BankAccountDetails> findTop2ByFixedDepositsTenure(@PathVariable("tenure") int tenure) {
		return bankAccountService.findTop2ByFixedDepositsTenure(tenure);
	}

	@GetMapping("/findByFixedDepositsTenureAndFixedDepositsFdAmount/{tenure}/{fdAmount}")
	public Flux<BankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(
			@PathVariable("tenure") int tenure, @PathVariable("fdAmount") int fdAmount) {
		return bankAccountService.findByFixedDepositsTenureAndFixedDepositsFdAmount(tenure, fdAmount);
	}

	@GetMapping("/findByCustomQuery/{balance}")
	public Flux<BankAccountDetails> findByCustomQuery(@PathVariable("balance") int balance) {
		return bankAccountService.findByCustomQuery(balance);
	}

	@GetMapping(path = "/findByCustomQuerySse/{balance}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<BankAccountDetails> findByCustomQuerySse(@PathVariable("balance") int balance) {
		return bankAccountService.findByCustomQuery(balance).doOnNext(account -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	@PutMapping("/addFixedDeposit/{bankAccountId}/{amount}")
	public Mono<Void> addFixedDeposit(@PathVariable("bankAccountId") String bankAccountId,
			@PathVariable("amount") int amount) {
		return bankAccountService.addFixedDeposit(bankAccountId, amount);
	}

}
