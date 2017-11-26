package sample.spring.chapter07.bankapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import sample.spring.chapter07.bankapp.domain.DataSource;

@Configuration
@Profile({ "dev", "default" })
@PropertySource("classpath:/META-INF/devDB.properties")
public class DevDBConfiguration {
	private static Logger logger = LogManager.getLogger(DevDBConfiguration.class);

	@Value("${driverClassName}")
	private String driverClass;

	@Value("${url}")
	private String url;

	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;

	public DevDBConfiguration() {
		logger.info("initializing");
	}

	@Bean
	public DataSource dataSource() {
		return new DataSource(driverClass, url, username, password);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
