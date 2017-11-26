package sample.spring.chapter06;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter06.beans.Sample;

public class SampleApp {
	private static Logger logger = LogManager.getLogger(SampleApp.class);

	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		Sample sample = context.getBean(Sample.class);
		logger.info(sample);
	}
}
