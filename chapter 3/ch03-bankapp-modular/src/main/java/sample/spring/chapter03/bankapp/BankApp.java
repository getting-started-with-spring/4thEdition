package sample.spring.chapter03.bankapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter03.bankapp.controller.FixedDepositController;

public class BankApp {
	private static Logger logger = LogManager.getLogger(BankApp.class);

	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/bankapp-controller.xml");

		FixedDepositController fixedDepositController = (FixedDepositController) context.getBean("controller");

		logger.info("Submission status of fixed deposit : " + fixedDepositController.submit());
		logger.info("Returned fixed deposit info : " + fixedDepositController.get());
	}
}
