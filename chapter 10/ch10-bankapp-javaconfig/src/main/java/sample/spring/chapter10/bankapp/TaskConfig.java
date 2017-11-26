package sample.spring.chapter10.bankapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling // -- used for scheduling FixedDepositProcessorJob's sendEmail
					// method
public class TaskConfig {

}
