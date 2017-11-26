package sample.spring.chapter03.bankapp.event;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.ClassPathResource;

public class EventSenderFactoryBean implements FactoryBean<EventSender> {
	private String databasePropertiesFile;
	private String webServicePropertiesFile;
	private String messagingPropertiesFile;

	public void setDatabasePropertiesFile(String databasePropertiesFile) {
		this.databasePropertiesFile = databasePropertiesFile;
	}

	public void setWebServicePropertiesFile(String webServicePropertiesFile) {
		this.webServicePropertiesFile = webServicePropertiesFile;
	}

	public void setMessagingPropertiesFile(String messagingPropertiesFile) {
		this.messagingPropertiesFile = messagingPropertiesFile;
	}

	@Override
	public EventSender getObject() throws Exception {
		System.out.println("getObject invoked");
		EventSender eventSender = null;
		Properties properties = new Properties();

		ClassPathResource databaseProperties = null;
		ClassPathResource webServiceProperties = null;
		ClassPathResource messagingProperties = null;

		if (databasePropertiesFile != null) {
			databaseProperties = new ClassPathResource(databasePropertiesFile);
		}
		if (webServicePropertiesFile != null) {
			webServiceProperties = new ClassPathResource(
					webServicePropertiesFile);
		}
		if (messagingPropertiesFile != null) {
			messagingProperties = new ClassPathResource(messagingPropertiesFile);
		}

		if (databaseProperties != null && databaseProperties.exists()) {
			InputStream inStream = databaseProperties.getInputStream();
			properties.load(inStream);
			eventSender = new DatabaseEventSender(properties);
		}

		else if (webServiceProperties != null && webServiceProperties.exists()) {
			InputStream inStream = webServiceProperties.getInputStream();
			properties.load(inStream);
			eventSender = new WebServiceEventSender(properties);
		}

		else if (messagingProperties != null && messagingProperties.exists()) {
			InputStream inStream = messagingProperties.getInputStream();
			properties.load(inStream);
			eventSender = new MessagingEventSender(properties);
		}

		return eventSender;
	}

	@Override
	public Class<?> getObjectType() {
		return EventSender.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}