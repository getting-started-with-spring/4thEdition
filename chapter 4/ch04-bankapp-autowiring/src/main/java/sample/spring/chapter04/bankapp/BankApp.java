package sample.spring.chapter04.bankapp;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter04.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter04.bankapp.service.AccountStatementService;
import sample.spring.chapter04.bankapp.service.CustomerRegistrationService;
import sample.spring.chapter04.bankapp.service.CustomerRequestService;
import sample.spring.chapter04.bankapp.service.FixedDepositService;

public class BankApp {
	private static Logger logger = LogManager.getLogger(BankApp.class);

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		logger.info("Beginning with accessing CustomerRequestService (autowire='constructor')");
		CustomerRequestService customerRequestService_1 = (CustomerRequestService) context
				.getBean("customerRequestService");
		customerRequestService_1.submitRequest("checkBookRequest",
				"Request to send a 50-leaf check book");
		logger.info("Done with accessing CustomerRequestService (autowire='constructor')");

		logger.info("Beginning with accessing CustomerRegistrationService (autowire='byType')");
		CustomerRegistrationService customerRegistrationService_1 = (CustomerRegistrationService) context
				.getBean("customerRegistrationService");
		customerRegistrationService_1.setAccountNumber("account_1");
		customerRegistrationService_1.setAddress("address_1");
		customerRegistrationService_1.setDebitCardNumber("debitCardNumber_1");
		customerRegistrationService_1.register();
		logger.info("Done with accessing CustomerRegistrationService (autowire='byType')");

		logger.info("Beginning with accessing CustomerRegistrationService (autowire='no')");
		CustomerRegistrationService customerRegistrationService_2 = (CustomerRegistrationService) context
				.getBean("customerRegistrationService_");
		try {
			customerRegistrationService_2.setAccountNumber("account_2");
			customerRegistrationService_2.setAddress("address_2");
			customerRegistrationService_2
					.setDebitCardNumber("debitCardNumber_2");
			customerRegistrationService_2.register();
		} catch (Exception e) {
			logger.error("Exception : " + e.toString());
		}
		logger.info("Done with accessing CustomerRegistrationService (autowire='no')");

		logger.info("Beginning with accessing FixedDepositService (autowire='byName')");
		FixedDepositService fixedDepositService = context
				.getBean(FixedDepositService.class);
		fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 1000,
				12, "someemail@somedomain.com"));
		logger.info("Done with accessing FixedDepositService (autowire='byName')");

		logger.info("Beginning with accessing AccountStatementService (autowire-candidate='false')");
		try {
			AccountStatementService accountStatementService = context
					.getBean(AccountStatementService.class);
			accountStatementService.getAccountStatement(new Date(), new Date());
		} catch (Exception e) {
			logger.error("Exception : " + e.toString());
		}
		logger.info("Done with accessing AccountStatementService (autowire-candidate='false')");
	}
}
