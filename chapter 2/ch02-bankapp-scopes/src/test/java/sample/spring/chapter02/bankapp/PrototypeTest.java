package sample.spring.chapter02.bankapp;

import static org.junit.Assert.assertNotSame;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter02.bankapp.domain.FixedDepositDetails;

public class PrototypeTest {
	private static ApplicationContext context;

	@BeforeClass
	public static void init() {
		context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
	}

	@Test
	public void testInstances() {
		FixedDepositDetails fixedDepositDetails1 = (FixedDepositDetails) context
				.getBean("fixedDepositDetails");
		FixedDepositDetails fixedDepositDetails2 = (FixedDepositDetails) context
				.getBean("fixedDepositDetails");

		assertNotSame("Same FixedDepositDetails instances",
				fixedDepositDetails1, fixedDepositDetails2);
	}
}