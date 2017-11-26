package sample.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class MyServices {
	private static Logger logger = LogManager.getLogger(MyServices.class);
	
	@Autowired
	@Lazy
	private StatelessService statelessService;
	
	@Autowired
	@Lazy
	private StatefulService statefulService;

	public void useStateless() {
		logger.info(" --> " + statelessService);
	}

	public void useStateful() {
		logger.info(" --> " + statefulService);
	}
}
