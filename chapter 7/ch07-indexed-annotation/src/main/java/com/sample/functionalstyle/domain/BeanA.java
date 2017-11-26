package com.sample.functionalstyle.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BeanA {
	private static Logger logger = LogManager.getLogger(BeanA.class);

	public void name() {
		logger.info("This is @Component annotated BeanA");
	}
}
