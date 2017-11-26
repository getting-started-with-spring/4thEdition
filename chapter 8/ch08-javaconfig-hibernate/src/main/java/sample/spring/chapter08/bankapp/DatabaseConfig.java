package sample.spring.chapter08.bankapp;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/META-INF/database.properties")
@EnableTransactionManagement
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
	public SessionFactory sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.scanPackages("sample.spring");
		builder.setProperty("hibernate.show_sql", "true");
		builder.setProperty("hibernate.id.new_generator_mappings", "false");
		return builder.buildSessionFactory();
	}
	
	@Bean
	public PlatformTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
}
