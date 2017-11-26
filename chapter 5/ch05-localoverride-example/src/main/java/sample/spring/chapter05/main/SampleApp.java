package sample.spring.chapter05.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter05.domain.DataSource;
import sample.spring.chapter05.domain.WebServiceConfiguration;

public class SampleApp {
	private static Logger logger = LogManager.getLogger(SampleApp.class);
	
	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		logger.info(context.getBean(DataSource.class));
		logger.info(context.getBean(WebServiceConfiguration.class));
	}
}
