package sample.spring.chapter07.bankapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import sample.spring.chapter07.bankapp.domain.DataSource;

@Configuration
@Profile("production")
@PropertySource("classpath:/META-INF/productionDB.properties")
public class ProdDBConfiguration {
	private static Logger logger = LogManager.getLogger(ProdDBConfiguration.class);

	@Autowired
	private Environment env;

	public ProdDBConfiguration() {
		logger.info("initializing");
	}

	@Bean
	public DataSource dataSource() {
		return new DataSource(env.getProperty("driverClass"), env.getProperty("url"), env.getProperty("username"),
				env.getProperty("password"));
	}
}
