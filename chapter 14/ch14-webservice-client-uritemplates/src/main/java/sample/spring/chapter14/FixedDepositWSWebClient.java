package sample.spring.chapter14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import sample.spring.chapter14.domain.FixedDepositDetails;

public class FixedDepositWSWebClient {
	private static Logger logger = LogManager.getLogger(FixedDepositWSWebClient.class);

	public static void main(String args[]) throws InterruptedException {
		WebClient webClient = WebClient.create("http://localhost:8080/ch14-webservice-uritemplates");
		try {
			getFixedDepositList(webClient);
			openFixedDeposit(webClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(10000);
	}

	private static void getFixedDepositList(WebClient webClient) {
		webClient.get().uri("/fixedDeposits").accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToFlux(FixedDepositDetails.class).subscribe(fixedDeposit -> logger
						.info("Fixed Deposit -> " + fixedDeposit));
	}

	private static void openFixedDeposit(WebClient webClient) {
		FixedDepositDetails fdd = new FixedDepositDetails();
		fdd.setDepositAmount("9999");
		fdd.setEmail("99@somedomain.com");
		fdd.setTenure("12");

		webClient.post().uri("/fixedDeposits").accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(fdd))
				.retrieve().bodyToMono(FixedDepositDetails.class).subscribe(fixedDeposit -> logger
						.info("createFixedDeposit method. returned id is -> " + fixedDeposit.getId()));
	}
}