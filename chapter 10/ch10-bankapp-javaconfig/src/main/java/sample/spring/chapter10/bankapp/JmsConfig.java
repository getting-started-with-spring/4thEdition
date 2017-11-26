package sample.spring.chapter10.bankapp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsMessagingTemplate;

@ImportResource(locations = "classpath:META-INF/spring/applicationContext.xml")
@Configuration
@EnableJms //--corresponds to <jms:annotation-driven>
public class JmsConfig {

	//--corresponds to <amq:broker>
	@PostConstruct
	public void brokerService() throws Exception {
		BrokerService broker = new BrokerService();
		broker.addConnector("tcp://localhost:61616");
		broker.start();
	}
	
	
	//--corresponds to <amq:connectionFactory>
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
		List<String> trustedPackages = new ArrayList<>();
		trustedPackages.add("sample.spring.chapter10.bankapp.domain");
		trustedPackages.add("java.util");
		activeMQConnectionFactory.setTrustedPackages(trustedPackages);
		return activeMQConnectionFactory;
	}

	@Bean
	public CachingConnectionFactory cachingConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setTargetConnectionFactory(activeMQConnectionFactory);
		return cachingConnectionFactory;
	}

	//--corresponds to <jms:listener-container>
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
			CachingConnectionFactory cachingConnectionFactory, JmsTransactionManager transactionManager) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(cachingConnectionFactory);
		factory.setTransactionManager(transactionManager);
		return factory;
	}

	@Bean(name = "jmsTxManager")
	public JmsTransactionManager jmsTransactionManager(CachingConnectionFactory cachingConnectionFactory) {
		JmsTransactionManager transactionManager = new JmsTransactionManager();
		transactionManager.setConnectionFactory(cachingConnectionFactory);
		return transactionManager;
	}

	@Bean
	public JmsMessagingTemplate jmsMessagingTemplate(CachingConnectionFactory cachingConnectionFactory) {
		JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate(cachingConnectionFactory);
		jmsMessagingTemplate.setDefaultDestinationName("fixedDepositDestination");
		return jmsMessagingTemplate;
	}
}
