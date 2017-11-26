package sample.spring.chapter10.bankapp;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter10.bankapp.domain.BankAccountDetails;
import sample.spring.chapter10.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter10.bankapp.service.BankAccountService;
import sample.spring.chapter10.bankapp.service.FixedDepositService;

public class BankApp {
	private static Logger logger = LogManager.getLogger(BankApp.class);

	public static void main(String args[]) throws Exception {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		BankAccountService bankAccountService = context.getBean(BankAccountService.class);
		BankAccountDetails bankAccountDetails = new BankAccountDetails();
		bankAccountDetails.setBalanceAmount(1000);
		bankAccountDetails.setLastTransactionTimestamp(new Date());

		int bankAccountId = bankAccountService.createBankAccount(bankAccountDetails);

		logger.info("Created bank account with id - " + bankAccountId);

		FixedDepositService fixedDepositService = context.getBean(FixedDepositService.class);
		FixedDepositDetails fdd = new FixedDepositDetails();
		fdd.setActive("N");
		fdd.setBankAccountId(bankAccountId);
		fdd.setFdCreationDate(new Date());
		fdd.setFdAmount(500);
		fdd.setTenure(12);
		// -- set the email id here
		fdd.setEmail("sarin.java@gmail.com");
		fixedDepositService.createFixedDeposit(fdd);
		
		Thread.sleep(5000);
		// -- get the newly created FixedDepositDetails twice from the
		// database
		fixedDepositService.findFixedDepositsByBankAccount(bankAccountId);
		logger.info("Invoking FixedDepositService's findFixedDepositsByBankAccount again");
		fixedDepositService.findFixedDepositsByBankAccount(bankAccountId);

		// -- create another fixed deposit. The createFixedDeposit method is
		// annotated with
		// @CacheEvict; therefore, all cached FixedDeposits are evicted from
		// cache
		fixedDepositService.createFixedDeposit(fdd);

		Thread.sleep(5000);
		logger.info("Invoking FixedDepositService's findFixedDepositsByBankAccount after creating a new fixed deposit");
		List<FixedDepositDetails> fixedDepositDetailsList = fixedDepositService
				.findFixedDepositsByBankAccount(bankAccountId);

		for (FixedDepositDetails detail : fixedDepositDetailsList) {
			// -- getFixedDeposit method is annotated with @CachePut;
			// therefore, is always invoked
			fixedDepositService.getFixedDeposit(detail.getFixedDepositId());
		}

		for (FixedDepositDetails detail : fixedDepositDetailsList) {
			// -- the getFixedDepositFromCache is never invoked because the
			// earlier for loop cached all the fixed deposits
			fixedDepositService.getFixedDepositFromCache(detail.getFixedDepositId());
		}
		logger.info("Please wait. The program will exit automatically after 30s.");
		Thread.sleep(30000);
		context.close();
	}
}