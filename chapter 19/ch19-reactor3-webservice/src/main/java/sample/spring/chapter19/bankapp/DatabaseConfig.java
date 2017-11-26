package sample.spring.chapter19.bankapp;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "sample.spring.chapter19.bankapp.repository")
public class DatabaseConfig {
	@Bean
	public MongoClient mongoClient() throws UnknownHostException {
		return MongoClients.create("mongodb://localhost");
	}

	public ReactiveMongoDatabaseFactory mongoDbFactory() throws UnknownHostException {
		return new SimpleReactiveMongoDatabaseFactory(mongoClient(), "test");
	}

	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() throws UnknownHostException {
		return new ReactiveMongoTemplate(mongoDbFactory());
	}
}
