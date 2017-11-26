package sample.spring.chapter13;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "sample.spring.chapter13.domain",
		"sample.spring.chapter13.dao", "sample.spring.chapter13.service" })
public class RootContextConfig {

}
