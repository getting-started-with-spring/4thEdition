package com.sample.functionalstyle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sample.functionalstyle.domain.BeanB;
import com.sample.functionalstyle.domain.MyConfiguration;

public class SampleApp {
	private static Logger logger = LogManager.getLogger(SampleApp.class);
	
	public static void main(String... args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(MyConfiguration.class);
		context.refresh();
		logger.info(context.getBean(BeanB.class));
		context.close();
	}
}
