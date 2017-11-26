package sample.spring.chapter09.bankapp;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sample.spring.chapter09.bankapp.domain.BankAccountDetails;
import sample.spring.chapter09.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter09.bankapp.service.BankAccountService;
import sample.spring.chapter09.bankapp.service.FixedDepositService;

public class BankApp {
	private static Logger logger = LogManager.getLogger(BankApp.class);

	public static void main(String args[]) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("sample.spring");
		context.refresh();

		BankAccountService bankAccountService = context.getBean(BankAccountService.class);
		BankAccountDetails bankAccountDetails = new BankAccountDetails();
		bankAccountDetails.setBalanceAmount(1000);
		bankAccountDetails.setLastTransactionTimestamp(new Date());
		
		List<BankAccountDetails> results = bankAccountService.findAccountsWithBalanceGreaterThan(100);
		logger.info("Bank accounts with balance > 100 are: ");
		for(BankAccountDetails details : results) {
			logger.info(details.getAccountId());
		}

		int bankAccountId = bankAccountService.createBankAccount(bankAccountDetails);

		logger.info("Created bank account with id - " + bankAccountId);

		FixedDepositService fixedDepositService = context.getBean(FixedDepositService.class);

		FixedDepositDetails fdd = new FixedDepositDetails();
		fdd.setActive("Y");
		fdd.setBankAccountId(bankAccountDetails);
		fdd.setFdCreationDate(new Date());
		fdd.setFdAmount(500);
		fdd.setTenure(12);
		int fixedDepositId = fixedDepositService.createFixedDeposit(fdd);
		logger.info("Created fixed deposit with id - " + fixedDepositId);

		logger.info(fixedDepositService.getFixedDeposit(fixedDepositId));

		context.close();
	}
}
