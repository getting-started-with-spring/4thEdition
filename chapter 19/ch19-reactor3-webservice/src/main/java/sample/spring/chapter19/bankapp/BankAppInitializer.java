package sample.spring.chapter19.bankapp;

import org.springframework.web.reactive.support.AbstractAnnotationConfigDispatcherHandlerInitializer;

import sample.spring.chapter19.bankapp.service.BankAccountServiceImpl;

public class BankAppInitializer extends AbstractAnnotationConfigDispatcherHandlerInitializer {

	@Override
	protected Class<?>[] getConfigClasses() {
		return new Class[] { WebConfig.class, DatabaseConfig.class, BankAccountServiceImpl.class };
	}
}
