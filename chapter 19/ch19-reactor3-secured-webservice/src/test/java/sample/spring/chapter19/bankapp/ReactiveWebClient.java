package sample.spring.chapter19.bankapp;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import sample.spring.chapter19.bankapp.domain.BankAccountDetails;

public class ReactiveWebClient {
	private static Logger logger = LogManager.getLogger(ReactiveWebClient.class);

	private static WebClient unauthenticatedWebClient = WebClient
			.create("http://localhost:8080/ch19-reactor3-secured-webservice/bankaccount");

	private static WebClient userWebClient = WebClient.builder().filter(basicAuthentication("user", "user"))
			.baseUrl("http://localhost:8080/ch19-reactor3-secured-webservice/bankaccount").build();

	private static WebClient adminWebClient = WebClient.builder().filter(basicAuthentication("admin", "admin"))
			.baseUrl("http://localhost:8080/ch19-reactor3-secured-webservice/bankaccount").build();

	public static void main(String args[]) throws InterruptedException {
		unauthenticatedWebClient.get().uri("/findByBalance/{balance}", 1000).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToFlux(BankAccountDetails.class).subscribe(
						account -> logger.info("Unauthenticated  /findByBalance/{balance} -> account with balance 1000 -> "
								+ account.getAccountId()),
						error -> logger.info("Unauthenticated /findByBalance/{balance} -> error -> " + error));

		userWebClient.get().uri("/findByBalance/{balance}", 1000).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToFlux(BankAccountDetails.class).subscribe(
						account -> logger.info("USER  /findByBalance/{balance} -> account with balance 1000 -> "
								+ account.getAccountId()),
						error -> logger.info("USER /findByBalance/{balance} -> error -> " + error));

		userWebClient.get().uri("/findByCustomQuery/{balance}", 1000).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToFlux(BankAccountDetails.class).subscribe(
						account -> logger.info("USER /findByCustomQuery/{balance} -> account with balance 1000 -> "
								+ account.getAccountId()),
						error -> logger.info("USER /findByCustomQuery/{balance} -> error -> " + error));

		adminWebClient.get().uri("/findByCustomQuery/{balance}", 1000).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToFlux(BankAccountDetails.class).subscribe(
						account -> logger.info("ADMIN /findByCustomQuery/{balance} -> account with balance 1000 -> "
								+ account.getAccountId()),
						error -> logger.info("ADMIN /findByCustomQuery/{balance} -> error -> " + error));

		Thread.sleep(100000);
	}
}