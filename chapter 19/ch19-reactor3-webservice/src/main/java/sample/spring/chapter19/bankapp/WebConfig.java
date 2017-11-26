package sample.spring.chapter19.bankapp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@Configuration
@ComponentScan(basePackages = "sample.spring.chapter19.bankapp.controller")
public class WebConfig {
}
