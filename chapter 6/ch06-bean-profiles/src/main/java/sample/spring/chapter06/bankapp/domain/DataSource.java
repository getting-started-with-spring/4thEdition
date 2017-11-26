package sample.spring.chapter06.bankapp.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataSource {
	@Value("#{dbProps.driverClassName}")
	private String driverClass;
	
	@Value("#{dbProps.url}")
	private String url;
	
	@Value("#{dbProps.username}")
	private String username;
	
	@Value("#{dbProps.password}")
	private String password;

	public String getDriverClass() {
		return driverClass;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
