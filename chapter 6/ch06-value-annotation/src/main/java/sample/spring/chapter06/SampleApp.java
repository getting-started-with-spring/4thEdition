package sample.spring.chapter06;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter06.beans.Sample;

public class SampleApp {
	private static Logger logger = LogManager.getLogger(SampleApp.class);

	public static void main(String args[]) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		Sample sample = context.getBean(Sample.class);
		logger.info("environment --> " + sample.getEnvironment());
		logger.info("currency --> " + sample.getCurrency());
		logger.info("country --> " + sample.getCountry());
		logger.info("state --> " + sample.getState());
		logger.info("city --> " + sample.getCity());
		logger.info("splitName --> " + sample.getSplitName()[0] + " " + sample.getSplitName()[1]);
		logger.info("Configuration bean reference --> " + sample.getMyConfigurationBean());
		logger.info("httpOrHttps --> " + sample.getHttpOrHttps());
		logger.info("isGreaterThan --> " + sample.isGreaterThan());
		logger.info("isConditionTrue --> " + sample.isConditionTrue());
		logger.info("totalAmount --> " + sample.getTotalAmount());
		logger.info("listItem --> " + sample.getListItem());
		logger.info("mapItem --> " + sample.getMapItem());
		logger.info("isEmailId --> " + sample.isEmailId());
		context.close();
	}
}
