package sample.spring.chapter19.bankapp;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import sample.spring.chapter19.bankapp.domain.BankAccountDetails;
import sample.spring.chapter19.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter19.bankapp.exception.NotEnoughBalanceException;

public class ReactiveWebClient {
	private static Logger logger = LogManager.getLogger(ReactiveWebClient.class);
	private static WebClient webClient = WebClient.create("http://localhost:8080/ch19-rxjava2-webservice/bankaccount");


	public static void main(String args[]) throws InterruptedException {

		// --create a new BankAccountDetails entity
		webClient.post().uri("/createBankAccount").accept(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(getNewBankAccountDetails()))
				.retrieve()
				.bodyToMono(String.class)
				.subscribe(id -> logger.info("createBankAccount method. returned id is -> " + id));

		// --find BankAccountDetails entities with balance 1000
		webClient.get().uri("/findByBalance/{balance}", 1000).accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(BankAccountDetails.class)
				.subscribe(account -> logger.info("account with balance 1000 -> " + account.getAccountId()));

		// --create a BankAccountDetails and add a fixed deposit of 500 to it
		webClient.post().uri("/saveBankAccount").accept(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(getNewBankAccountDetails()))
				.retrieve()
				.bodyToMono(BankAccountDetails.class).map(account -> account.getAccountId())
				.subscribe(accountId -> addFixedDeposit(accountId, 500).subscribe(
						item -> logger.info("Received item : " + item),
						error -> logger.info("addFixedDeposit --> Exception occurred while adding fixed deposit : '"
								+ error.getMessage() + "'"),
						() -> logger.info("Fixed deposit successfully added to " + accountId)));

		// --find BankAccountDetails entities with balance 500
		webClient.get().uri("/findByBalance/{balance}", 500).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToFlux(BankAccountDetails.class)
				.subscribe(account -> logger.info("account with balance 500 -> " + account.getAccountId()));

		// --create a BankAccountDetails and add a fixed deposit of 2000 to it. This will result in error message.
		webClient.post().uri("/saveBankAccount").accept(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(getNewBankAccountDetails()))
				.retrieve()
				.bodyToMono(BankAccountDetails.class).map(account -> account.getAccountId())
				.subscribe(accountId -> addFixedDeposit(accountId, 2000).subscribe(
						item -> logger.info("Received item : " + item),
						error -> logger.info("addFixedDeposit --> Exception occurred while adding fixed deposit : '"
								+ error.getMessage() + "'"),
						() -> logger.info("Fixed deposit successfully added to " + accountId)));

		Thread.sleep(10000);
	}

	private static Mono<Void> addFixedDeposit(String accountId, int amount) {
		return webClient.put().uri("/addFixedDeposit/{bankAccountId}/{amount}", accountId, amount)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(statusCode -> HttpStatus.INTERNAL_SERVER_ERROR.equals(statusCode),
						clientResponse -> Mono
								.error(new NotEnoughBalanceException("Not enough balance in the bank account")))
				.bodyToMono(Void.class);
	}

	private static BankAccountDetails getNewBankAccountDetails() {
		BankAccountDetails bankAccountDetails = new BankAccountDetails();
		bankAccountDetails.setBalance(1000);
		bankAccountDetails.setLastTransactionTimestamp(new Date());

		FixedDepositDetails fd = new FixedDepositDetails();
		fd.setActive("Y");
		fd.setFdAmount(2000);
		fd.setFdCreationDate(new Date());
		fd.setTenure(6);
		bankAccountDetails.addFixedDeposit(fd);
		return bankAccountDetails;
	}
}
