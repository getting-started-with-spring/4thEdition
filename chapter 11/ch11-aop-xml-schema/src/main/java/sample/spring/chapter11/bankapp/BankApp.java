package sample.spring.chapter11.bankapp;

import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter11.bankapp.domain.BankAccountDetails;
import sample.spring.chapter11.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter11.bankapp.service.BankAccountService;
import sample.spring.chapter11.bankapp.service.FixedDepositService;

public class BankApp {
	public static void main(String args[]) throws Exception {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		BankAccountService bankAccountService = context.getBean(BankAccountService.class);
		BankAccountDetails bankAccountDetails = new BankAccountDetails();
		bankAccountDetails.setBalanceAmount(1000);
		bankAccountDetails.setLastTransactionTimestamp(new Date());

		bankAccountService.createBankAccount(bankAccountDetails);

		FixedDepositService fixedDepositService = context.getBean(FixedDepositService.class);
		fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 1000, 12, "someemail@somedomain.com"));
		context.close();
	}
}
