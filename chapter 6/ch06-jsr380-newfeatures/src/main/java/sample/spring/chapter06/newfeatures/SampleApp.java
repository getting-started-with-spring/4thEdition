package sample.spring.chapter06.newfeatures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter06.newfeatures.domain.Profile;

public class SampleApp {
	private static Logger logger = LogManager.getLogger(SampleApp.class);

	public static void main(String args[]) throws Exception {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		logger.info("Validating Profile object using JSR 380 Validator");

		List<String> friends = new ArrayList<String>();
		friends.add("Johnson");
		friends.add("John");
		Profile profile = new Profile(friends, Optional.of("123456789"), -1, 0);

		Validator validator = context.getBean(Validator.class);
		Set<ConstraintViolation<Profile>> violations = validator.validate(profile);

		Iterator<ConstraintViolation<Profile>> itr = violations.iterator();
		while (itr.hasNext()) {
			logger.error(itr.next());
		}
		context.close();
	}
}
