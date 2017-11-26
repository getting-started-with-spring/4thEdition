package sample.spring.chapter06.bankapp;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter06.bankapp.service.FundTransferProcessor;

public class BankApp {
	public static void main(String args[]) throws Exception {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		context.getBean(FundTransferProcessor.class).sameBankImmediateFundTransferService();
		context.getBean(FundTransferProcessor.class).diffBankImmediateFundTransferService();
		context.close(); 
	}
}
