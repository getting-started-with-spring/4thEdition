package sample.spring.chapter07.bankapp.beanpostprocessor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanNamePrinter implements
		BeanFactoryPostProcessor {
	private static Logger logger = LogManager
			.getLogger(BeanNamePrinter.class);

	public BeanNamePrinter() {
		logger.info("Created BeanNamePrinter instance");
	}

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		// -- get all the bean definitions
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			String beanName = beanDefinitionNames[i];
			logger.info("Found bean named: " + beanName);
		}
	}
}
