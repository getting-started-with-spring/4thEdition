package sample.spring.chapter09.bankapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "sample.spring")
@EnableAsync
public class DatabaseConfig {
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("localhost");
	}

	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoDbFactory(mongoClient(), "test");
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDbFactory());
	}
}
