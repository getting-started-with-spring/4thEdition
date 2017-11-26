package sample.spring.chapter06.bankapp;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter06.bankapp.service.CustomerRequestService;
import sample.spring.chapter06.bankapp.service.FixedDepositService;

public class BankApp {
	private static Logger logger = LogManager.getLogger(BankApp.class);

	public static void main(String args[]) throws Exception {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		logger.info("Validating FixedDepositDetails object using Spring Validation API");

		FixedDepositService fixedDepositService = (FixedDepositService) context.getBean("fixedDepositService");
		fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 0, 12, "someemail@somedomain.com"));
		fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 1000, 12, "someemail@somedomain.com"));

		logger.info("Validating FixedDepositDetails object using JSR 380 Validator");
		FixedDepositService fixedDepositServiceJsr380 = (FixedDepositService) context
				.getBean("fixedDepositServiceJsr380");
		fixedDepositServiceJsr380.createFixedDeposit(new FixedDepositDetails(1, 0, 12, "someemail@somedomain.com"));
		fixedDepositServiceJsr380.createFixedDeposit(new FixedDepositDetails(1, 1000, 12, "someemail@somedomain.com"));

		logger.info("Validating CustomerRequestDetails object using JSR 380 Validator");
		CustomerRequestService customerRequestService = context.getBean(CustomerRequestService.class);
		try {
			customerRequestService.submitRequest("request type", "description < 20", Calendar.getInstance());
		} catch (ConstraintViolationException ex) {
			printValidationErrors(ex);
		}
		try {
			Calendar futureDate = Calendar.getInstance();
			futureDate.add(Calendar.MONTH, 1);
			customerRequestService.submitRequest("request type", "description size > 20", futureDate);
		} catch (ConstraintViolationException ex) {
			printValidationErrors(ex);
		}
		try {
			Calendar pastDate = Calendar.getInstance();
			pastDate.add(Calendar.MONTH, -1);
			customerRequestService.submitRequest("request type", "description size > 20", pastDate);
		} catch (ConstraintViolationException ex) {
			printValidationErrors(ex);
		}
		context.close();
	}

	private static void printValidationErrors(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		Iterator<ConstraintViolation<?>> itr = violations.iterator();
		while (itr.hasNext()) {
			logger.info(itr.next());
		}
	}
}
