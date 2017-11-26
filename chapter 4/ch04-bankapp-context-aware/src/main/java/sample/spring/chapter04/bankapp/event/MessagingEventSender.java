package sample.spring.chapter04.bankapp.event;

import java.util.Properties;

public class MessagingEventSender implements EventSender {
	@SuppressWarnings("unused")
	private Properties messagingProperties;

	public void setMessagingProperties(Properties messagingProperties) {
		this.messagingProperties = messagingProperties;
	}

	@Override
	public void sendEvent(Event e) {
		// -- send event to the messaging middleware
	}

}
