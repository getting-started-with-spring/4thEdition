package sample.spring.chapter06.bankapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter06.bankapp.controller.FixedDepositController;

public class BankAppWithProfile {
	private static Logger logger = LogManager.getLogger(BankAppWithProfile.class);

	public static void main(String args[]) {
		System.setProperty("spring.profiles.active", "mybatis, production");
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		logger.info("dbProps bean -> " + context.getBean("dbProps"));

		FixedDepositController fixedDepositController = context.getBean(FixedDepositController.class);

		logger.info("Submission status of fixed deposit : " + fixedDepositController.submit());
		logger.info("Returned fixed deposit info : " + fixedDepositController.get());
		context.close();
	}
}
