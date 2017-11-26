package sample.spring.chapter19.bankapp;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sample.spring.chapter19.bankapp.domain.BankAccountDetails;
import sample.spring.chapter19.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter19.bankapp.service.BankAccountService;

public class DataAccessTest {
	private static Logger logger = LogManager.getLogger(DataAccessTest.class);
	private static BankAccountService bankAccountService;

	public static void main(String args[]) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("sample.spring");
		context.refresh();

		bankAccountService = context.getBean(BankAccountService.class);
		BankAccountDetails bankAccountDetails_1 = getNewBankAccountDetails();
		bankAccountService
				.createBankAccount(bankAccountDetails_1)
				.subscribe(
						id -> {
							logger.info("createBankAccount: created bank account with id - "
									+ id
									+ " and balance "
									+ bankAccountDetails_1.getBalance());
						});

		bankAccountService
				.findByFixedDepositsTenureLessThan(10)
				.subscribe(
						bankDetails -> logger
								.info("findByFixedDepositsTenureLessThan(10): Account id -> "
										+ bankDetails.getAccountId()));

		bankAccountService.countByBalance(1000).subscribe(
				count -> logger.info("countByBalance(1000) -> " + count));

		bankAccountService
				.removeByBalance(500)
				.subscribe(
						bankDetails -> logger.info("removeByBalance(500) -> "
								+ bankDetails.getAccountId()),
						bankDetails -> logger
								.info("removeByBalance(500) -> Nothing found to delete"));

		bankAccountService.findByBalance(1000).subscribe(
				bankDetails -> logger.info("findByBalance(1000) -> "
						+ bankDetails.getAccountId()));

		bankAccountService
				.findByFixedDepositsFdAmountGreaterThan(1000)
				.subscribe(
						bankDetails -> logger
								.info("findByFixedDepositsFdAmountGreaterThan(1000) -> "
										+ bankDetails.getAccountId()));

		bankAccountService
				.findTop2ByOrderByFixedDepositsFdCreationDateDesc()
				.subscribe(
						bankDetails -> logger
								.info("findTop2ByOrderByFixedDepositsFdCreationDateDesc -> "
										+ bankDetails.getAccountId()));

		bankAccountService.findTop2ByFixedDepositsTenure(6).subscribe(
				bankDetails -> logger
						.info("findTop2ByFixedDepositsTenure(6) -> "
								+ bankDetails.getAccountId()));

		bankAccountService
				.findByFixedDepositsTenureAndFixedDepositsFdAmount(6, 2000)
				.subscribe(
						bankDetails -> logger
								.info("findByFixedDepositsTenureAndFixedDepositsFdAmount(6,2000) -> "
										+ bankDetails.getAccountId()));

		BankAccountDetails bankAccountDetails_2 = getNewBankAccountDetails();
		bankAccountService
				.saveBankAccount(bankAccountDetails_2)
				.subscribe(
						bankAccountDetails -> bankAccountService
								.addFixedDeposit(
										bankAccountDetails.getAccountId(), 2000)
								.subscribe(
										() -> logger
												.info("Fixed deposit successfully added to "
														+ bankAccountDetails
																.getAccountId()),
										error -> logger
												.info("addFixedDeposit -> Exception occurred while adding fixed deposit : '"
														+ error.getMessage()
														+ "'")));

		bankAccountService
				.findByCustomQuery(1000)
				.map(account -> account.getBalance())
				.reduce(0, Integer::sum)
				.subscribe(
						totalBalance -> logger.info("Sum of all balances "
								+ totalBalance));

		// --close ApplicationContext
		Thread.sleep(10000);
		context.close();
	}

	private static BankAccountDetails getNewBankAccountDetails() {
		BankAccountDetails bankAccountDetails = new BankAccountDetails();
		bankAccountDetails.setBalance(1000);
		bankAccountDetails.setLastTransactionTimestamp(new Date());

		FixedDepositDetails fd = new FixedDepositDetails();
		fd.setActive("Y");
		fd.setFdAmount(2000);
		fd.setFdCreationDate(new Date());
		fd.setTenure(6);
		bankAccountDetails.addFixedDeposit(fd);
		return bankAccountDetails;
	}

}