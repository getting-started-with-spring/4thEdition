package sample.spring.chapter09.bankapp;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@PropertySource("classpath:/META-INF/database.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "sample.spring")
@EnableAsync
public class DatabaseConfig {
	@Autowired
	private Environment env;

	// -- specifying destroyMethod="close" is not required. It's inferred by the
	// Spring container.
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan("sample.spring");
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.id.new_generator_mappings", "false");
		entityManagerFactory.setJpaProperties(props);
		return entityManagerFactory;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager platformTransactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	@Bean(name = "transactionTemplate")
	public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager) {
		return new TransactionTemplate(platformTransactionManager);
	}
}
