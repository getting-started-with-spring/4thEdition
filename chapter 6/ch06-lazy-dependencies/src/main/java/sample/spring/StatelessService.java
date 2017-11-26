package sample.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class StatelessService {
	private static Logger logger = LogManager.getLogger(StatelessService.class);
	
	public StatelessService() {
		logger.info("Created StatelessService");
	}
}
