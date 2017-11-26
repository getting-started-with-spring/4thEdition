package sample.spring.chapter16;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "sample.spring.chapter16.domain",
		"sample.spring.chapter16.dao", "sample.spring.chapter16.service" })
public class RootContextConfig {

}
