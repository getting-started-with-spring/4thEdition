package sample.spring.chapter07.bankapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sample.spring.chapter07.bankapp.service.TransactionService;
import sample.spring.chapter07.bankapp.service.TransactionServiceImpl;

public class BankAppMixed {
	private static Logger logger = LogManager.getLogger(BankAppMixed.class);

	public static void main(String args[]) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BankAppConfiguration.class,
				TransactionServiceImpl.class);
		logger.info("----------> Beginning with accessing TransactionService");
		TransactionService transasctionService = context.getBean(TransactionService.class);
		transasctionService.getTransactions("someCustomerId");
		logger.info("----------> Done with accessing TransactionService");

		context.close();
	}
}