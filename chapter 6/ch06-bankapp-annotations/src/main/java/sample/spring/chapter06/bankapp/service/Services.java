package sample.spring.chapter06.bankapp.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Services {
	@Autowired
	@Qualifier("service")
	private Set<MyService> services;

	public Set<MyService> getServices() {
		return services;
	}
}
