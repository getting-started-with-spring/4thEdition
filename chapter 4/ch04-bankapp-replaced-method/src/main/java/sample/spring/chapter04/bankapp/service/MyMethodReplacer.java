package sample.spring.chapter04.bankapp.service;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyMethodReplacer implements MethodReplacer,
		ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public Object reimplement(Object obj, Method method, Object[] args)
			throws Throwable {
		return applicationContext.getBean((String) args[0]);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
