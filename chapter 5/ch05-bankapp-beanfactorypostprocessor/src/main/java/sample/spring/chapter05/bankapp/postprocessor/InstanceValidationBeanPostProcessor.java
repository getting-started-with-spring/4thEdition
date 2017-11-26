package sample.spring.chapter05.bankapp.postprocessor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import sample.spring.chapter05.bankapp.common.InstanceValidator;

public class InstanceValidationBeanPostProcessor implements BeanPostProcessor,
		Ordered {
	private static Logger logger = LogManager
			.getLogger(InstanceValidationBeanPostProcessor.class);
	private int order;

	public InstanceValidationBeanPostProcessor() {
		logger.info("Created InstanceValidationPostProcessor instance");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		logger.info("InstanceValidationBeanPostProcessor's postProcessBeforeInitialization method invoked for bean " + beanName + " of type " + bean.getClass());
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		logger.info("InstanceValidationBeanPostProcessor's postProcessAfterInitialization method invoked for bean " + beanName + " of type " + bean.getClass());
		if (bean instanceof InstanceValidator) {
			((InstanceValidator) bean).validateInstance();
		}
		return bean;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return order;
	}
}