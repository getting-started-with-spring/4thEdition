package com.sample.functionalstyle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sample.functionalstyle.domain.Person;
import com.sample.functionalstyle.domain.Profile;

public class SampleApp {
	private static Logger logger = LogManager.getLogger(SampleApp.class);

	public static void main(String... args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBean("primaryProfile", Profile.class,
				beanDefinition -> beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE),
				beanDefinition -> beanDefinition.setPrimary(true));

		context.registerBean("secondaryProfile", Profile.class, () -> new Profile("secondaryProfileName", "00"),
				beanDefinition -> beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE),
				beanDefinition -> beanDefinition.setPrimary(false));

		context.registerBean("personPrimary", Person.class);

		context.refresh();

		Profile profile = context.getBean(Person.class).getProfile();

		logger.info("Profile -> name: " + profile.getName() + ", age: " + profile.getAge());

		context.close();
	}
}
