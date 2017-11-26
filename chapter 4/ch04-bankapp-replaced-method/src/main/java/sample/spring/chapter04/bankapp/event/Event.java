package sample.spring.chapter04.bankapp.event;

import java.util.Map;

public interface Event {
	String getEventType();
	void setEventData(Map<String, Object> eventData);
	Map<String, Object> getEventData();
}
