package sample.spring.chapter06.bankapp;

import java.util.Date;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter06.bankapp.service.AccountStatementService;
import sample.spring.chapter06.bankapp.service.CustomerRegistrationService;
import sample.spring.chapter06.bankapp.service.CustomerRequestService;
import sample.spring.chapter06.bankapp.service.FixedDepositService;
import sample.spring.chapter06.bankapp.service.MyService;
import sample.spring.chapter06.bankapp.service.Services;
import sample.spring.chapter06.bankapp.service.TxService;

public class BankApp {
	private static Logger logger = LogManager.getLogger(BankApp.class);

	public static void main(String args[]) throws Exception {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		logger.info("----------> Beginning with accessing CustomerRequestService");
		CustomerRequestService customerRequestService_1 = (CustomerRequestService) context
				.getBean("customerRequestService");
		customerRequestService_1.submitRequest("checkBookRequest", "Request to send a 50-leaf check book");
		logger.info("----------> Done with accessing CustomerRequestService");

		logger.info("----------> Beginning with accessing CustomerRegistrationService");
		CustomerRegistrationService customerRegistrationService_1 = (CustomerRegistrationService) context
				.getBean("customerRegistrationService");
		customerRegistrationService_1.setAccountNumber("account_1");
		customerRegistrationService_1.setAddress("address_1");
		customerRegistrationService_1.setDebitCardNumber("debitCardNumber_1");
		customerRegistrationService_1.register();
		logger.info("----------> Done with accessing CustomerRegistrationService");

		logger.info("----------> Beginning with accessing FixedDepositService");
		FixedDepositService fixedDepositService = context.getBean(FixedDepositService.class);
		fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 1000, 12, "someemail@somedomain.com"));
		logger.info("----------> Done with accessing FixedDepositService");

		logger.info("----------> Beginning with accessing AccountStatementService");
		try {
			AccountStatementService accountStatementService = context.getBean(AccountStatementService.class);
			accountStatementService.getAccountStatement(new Date(), new Date());
		} catch (Exception e) {
			logger.error("Exception : " + e.toString());
		}
		logger.info("----------> Done with accessing AccountStatementService");

		logger.info("----------> Beginning with accessing TxService");
		TxService txService = context.getBean(TxService.class);
		logger.info("Transactions --> " + txService.getTransactions(1));
		logger.info("----------> Done with accessing TxService");

		Set<MyService> services = context.getBean(Services.class).getServices();
		for(MyService service : services) {
			logger.info("Service class --> " + service.getClass());
		}
		context.close();
	}
}
