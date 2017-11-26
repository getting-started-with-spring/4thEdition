package sample.spring.chapter03.bankapp.event;

import java.util.Properties;

public class MessagingEventSender implements EventSender {
	@SuppressWarnings("unused")
	private Properties messagingProperties;

	public MessagingEventSender(Properties messagingProperties) {
		this.messagingProperties = messagingProperties;
	}

	@Override
	public void sendEvent(Event e) {
		// -- send event to the messaging middleware
	}

}
