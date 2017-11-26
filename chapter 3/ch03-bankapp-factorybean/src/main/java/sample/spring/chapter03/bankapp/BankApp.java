package sample.spring.chapter03.bankapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter03.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter03.bankapp.service.FixedDepositService;

public class BankApp {
	private static Logger logger = LogManager.getLogger(BankApp.class);

	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		FixedDepositService fixedDepositService = (FixedDepositService) context.getBean("service");

		fixedDepositService.createFixedDeposit(new FixedDepositDetails(101, 10000, 60, "someemail@somedomain.com"));

		logger.info("Invoking getBean(\"eventFactory\") returns : " + context.getBean("eventSenderFactory"));
		logger.info("Invoking getBean(\"&eventFactory\") returns : " + context.getBean("&eventSenderFactory"));
	}
}
