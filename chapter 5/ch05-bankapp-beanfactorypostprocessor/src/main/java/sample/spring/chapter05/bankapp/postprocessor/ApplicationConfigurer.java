package sample.spring.chapter05.bankapp.postprocessor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;

public class ApplicationConfigurer implements BeanFactoryPostProcessor {
	private static Logger logger = LogManager
			.getLogger(ApplicationConfigurer.class);
	
	public ApplicationConfigurer() {
		logger.info("Created ApplicationConfigurer instance");
	}
	
	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		// -- get all the bean definitions
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			String beanName = beanDefinitionNames[i];
			BeanDefinition beanDefinition = beanFactory
					.getBeanDefinition(beanName);
			beanDefinition.setAutowireCandidate(false);
			// -- obtain dependencies of a bean
			if (beanDefinition.isSingleton()) {
				if (hasPrototypeDependency(beanFactory, beanDefinition)) {
					logger.error("Singleton-scoped " + beanName
							+ " bean is dependent on a prototype-scoped bean.");
				}
			}
		}
	}

	//-- Checks if a bean contains a prototype-scoped dependency
	private boolean hasPrototypeDependency(
			ConfigurableListableBeanFactory beanFactory,
			BeanDefinition beanDefinition) {
		boolean isPrototype = false;
		MutablePropertyValues mutablePropertyValues = beanDefinition
				.getPropertyValues();
		PropertyValue[] propertyValues = mutablePropertyValues
				.getPropertyValues();
		for (int j = 0; j < propertyValues.length; j++) {
			if (propertyValues[j].getValue() instanceof RuntimeBeanReference) {
				String dependencyBeanName = ((RuntimeBeanReference) propertyValues[j]
						.getValue()).getBeanName();
				BeanDefinition dependencyBeanDef = beanFactory
						.getBeanDefinition(dependencyBeanName);
				if (dependencyBeanDef.isPrototype()) {
					isPrototype = true;
					break;
				}
			}
		}
		return isPrototype;
	}
}
