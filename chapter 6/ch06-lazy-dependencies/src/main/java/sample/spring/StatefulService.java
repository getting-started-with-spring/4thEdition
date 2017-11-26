package sample.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StatefulService {
	private static Logger logger = LogManager.getLogger(StatefulService.class);
	
	public StatefulService() {
		logger.info("Created StatefulService");
	}
}
