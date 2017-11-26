package sample.spring.chapter14;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import sample.spring.chapter14.domain.FixedDepositDetails;

public class FixedDepositWSClient {
	private static Logger logger = LogManager.getLogger(FixedDepositWSClient.class);
	private static ApplicationContext context;

	public static void main(String args[]) {
		context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		getFixedDepositList(context.getBean(RestTemplate.class));
		getFixedDeposit(context.getBean(RestTemplate.class));
		openFixedDeposit(context.getBean(RestTemplate.class));
		editFixedDeposit(context.getBean(RestTemplate.class));
		closeFixedDeposit(context.getBean(RestTemplate.class));
		try {
			openInvalidFixedDeposit(context.getBean(RestTemplate.class));
		} catch (Exception e) {
			logger.error("Error occurred when the fixed deposit amount was 100");
		}
	}

	private static void getFixedDepositList(RestTemplate restTemplate) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");

		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

		ParameterizedTypeReference<List<FixedDepositDetails>> typeRef = new ParameterizedTypeReference<List<FixedDepositDetails>>() {
		};

		ResponseEntity<List<FixedDepositDetails>> responseEntity = restTemplate
				.exchange(
						"http://localhost:8080/ch14-webservice-uritemplates/fixedDeposits",
						HttpMethod.GET, requestEntity, typeRef);
		List<FixedDepositDetails> fixedDepositDetails = responseEntity
				.getBody();
		logger.info("List of fixed deposit details: \n" + fixedDepositDetails);
	}

	private static void getFixedDeposit(RestTemplate restTemplate) {
		ResponseEntity<FixedDepositDetails> responseEntity = restTemplate
				.getForEntity(
						"http://localhost:8080/ch14-webservice-uritemplates/fixedDeposits/1",
						FixedDepositDetails.class);
		FixedDepositDetails fixedDepositDetails = responseEntity.getBody();
		logger.info("Fixed deposit details for id = 1: \n"
				+ fixedDepositDetails);
	}

	private static void openFixedDeposit(RestTemplate restTemplate) {
		FixedDepositDetails fdd = new FixedDepositDetails();
		fdd.setDepositAmount("9999");
		fdd.setEmail("99@somedomain.com");
		fdd.setTenure("12");

		ResponseEntity<FixedDepositDetails> responseEntity = restTemplate
				.postForEntity(
						"http://localhost:8080/ch14-webservice-uritemplates/fixedDeposits",
						fdd, FixedDepositDetails.class);

		FixedDepositDetails fixedDepositDetails = responseEntity.getBody();
		logger.info("Details of the newly created fixed deposit: "
				+ fixedDepositDetails);
	}

	private static void openInvalidFixedDeposit(RestTemplate restTemplate) {
		FixedDepositDetails fdd = new FixedDepositDetails();
		fdd.setDepositAmount("100");
		fdd.setEmail("99@somedomain.com");
		fdd.setTenure("12");

		ResponseEntity<FixedDepositDetails> responseEntity = restTemplate
				.postForEntity(
						"http://localhost:8080/ch14-webservice-uritemplates/fixedDeposits",
						fdd, FixedDepositDetails.class);

		FixedDepositDetails fixedDepositDetails = responseEntity.getBody();
		logger.info("Details of the newly created fixed deposit: "
				+ fixedDepositDetails);
	}

	private static void editFixedDeposit(RestTemplate restTemplate) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");

		FixedDepositDetails fdd = new FixedDepositDetails();
		fdd.setDepositAmount("9999");
		fdd.setEmail("99@somedomain.com");
		fdd.setTenure("12");

		HttpEntity<FixedDepositDetails> requestEntity = new HttpEntity<FixedDepositDetails>(
				fdd, headers);

		ResponseEntity<FixedDepositDetails> responseEntity = restTemplate
				.exchange(
						"http://localhost:8080/ch14-webservice-uritemplates/fixedDeposits/2",
						HttpMethod.PUT, requestEntity,
						FixedDepositDetails.class);
		FixedDepositDetails fixedDepositDetails = responseEntity.getBody();
		logger.info("Modified fixed deposit details : " + fixedDepositDetails);
	}

	private static void closeFixedDeposit(RestTemplate restTemplate) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "text/plain");

		HttpEntity<FixedDepositDetails> requestEntity = new HttpEntity<FixedDepositDetails>(
				headers);

		ResponseEntity<String> responseEntity = restTemplate
				.exchange(
						"http://localhost:8080/ch14-webservice-uritemplates/fixedDeposits/3",
						HttpMethod.DELETE, requestEntity, String.class);
		logger.info("HTTP status code : " + responseEntity.getStatusCode()
				+ ". Response body: " + responseEntity.getBody());
	}
}
