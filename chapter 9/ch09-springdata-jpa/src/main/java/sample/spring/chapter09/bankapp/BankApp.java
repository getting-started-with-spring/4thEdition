package sample.spring.chapter09.bankapp;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

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

		/*
		 * --Uncomment this part to use XML-based configuration instead of Java-based
		 * configuration --
		 * 
		 * ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
		 * "classpath:META-INF/spring/applicationContext.xml");
		 */
		BankAccountService bankAccountService = context.getBean(BankAccountService.class);
		BankAccountDetails bankAccountDetails = new BankAccountDetails();
		bankAccountDetails.setBalanceAmount(1000);
		bankAccountDetails.setLastTransactionTimestamp(new Date());

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

		FixedDepositDetails _fdd = new FixedDepositDetails();
		_fdd.setActive("Y");
		_fdd.setBankAccountId(bankAccountDetails);
		_fdd.setFdCreationDate(new Date());
		_fdd.setFdAmount(500);
		_fdd.setTenure(6);
		fixedDepositService.createFixedDeposit(_fdd);

		logger.info("Total number of fixed deposits : " + fixedDepositService.count());
		logger.info("Number of fixed deposits with 12 months tenure " + fixedDepositService.countByTenure(12));

		List<FixedDepositDetails> removedFds = fixedDepositService.removeByTenure(12);
		logger.info("Removed " + removedFds.size() + " fixed deposits with tenure as 12 months");

		logger.info("Number of fixed deposits with 12 months tenure " + fixedDepositService.countByTenure(12));

		logger.info("findByTenure : " + fixedDepositService.findByTenure(6));

		logger.info("findTop2ByTenure : " + fixedDepositService.findTop2ByTenure(6));

		logger.info(
				"findTop2ByOrderByFdCreationDateDesc : " + fixedDepositService.findTop2ByOrderByFdCreationDateDesc());

		logger.info("findByTenureAndFdAmount : " + fixedDepositService.findByTenureAndFdAmount(6, 500));

		logger.info("findByTenure(tenure, pageable) : " + fixedDepositService.findByTenure(6, PageRequest.of(1, 10)));

		logger.info("findByTenure(tenure, sort) : "
				+ fixedDepositService.findByTenure(6, new Sort(Sort.Direction.ASC, "fdCreationDate")));

		// -- using Slice<T> for paginated access to entities
		logger.info("findByFdAmount(500, new PageRequest(0, 2)) : ");

		Slice<FixedDepositDetails> slice = fixedDepositService.findByFdAmount(500, PageRequest.of(0, 2));
		if (slice.hasContent()) {
			logger.info("Slice has content");
			List<FixedDepositDetails> list = slice.getContent();
			for (FixedDepositDetails details : list) {
				logger.info("Fixed Deposit ID --> " + details.getFixedDepositId());
			}
		}
		if (slice.hasNext()) {
			Pageable pageable = slice.nextPageable();
			slice = fixedDepositService.findByFdAmount(500, pageable);
		}

		// --using Stream<T>
		logger.info("findAllByTenure : ");
		TransactionTemplate txTemplate = context.getBean(TransactionTemplate.class);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try (Stream<FixedDepositDetails> stream = fixedDepositService.findAllByTenure(6)) {
					logger.info("count from stream --> " + stream.filter(t -> t.getActive().equals("Y")).count());
				}
			}
		});

		// -- async query method execution
		CompletableFuture<List<FixedDepositDetails>> future = fixedDepositService.findAllByFdAmount(500);
		while (!future.isDone()) {
			logger.info("Waiting for findAllByFdAmount method to complete .....");
		}
		logger.info(future.get());

		// --custom query using @Query annotation
		logger.info("findByCustomQuery : " + fixedDepositService.findByCustomQuery(6, 1000, "Y"));

		// -- QueryDsl usage
		logger.info("getHighValueFds : " + fixedDepositService.getHighValueFds());

		// --Query by example
		logger.info("getAllFds : " + fixedDepositService.getAllFds());

		// --close ApplicationContext
		context.close();
	}
}