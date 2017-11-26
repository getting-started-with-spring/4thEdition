package sample.spring.chapter07.bankapp;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import sample.spring.chapter07.bankapp.beanpostprocessor.BeanNamePrinter;
import sample.spring.chapter07.bankapp.beanpostprocessor.MyBeanPostProcessor;
import sample.spring.chapter07.bankapp.domain.CustomerRegistrationDetails;

public class BankOtherObjects {
	@Bean(name = "customerRegistrationDetails")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CustomerRegistrationDetails customerRegistrationDetails() {
		return new CustomerRegistrationDetails();
	}
	
	@Bean
	public static BeanNamePrinter beanNamePrinter() {
		return new BeanNamePrinter();
	}
	
	@Bean
	public static MyBeanPostProcessor myBeanPostProcessor() {
		return new MyBeanPostProcessor();
	}
}
