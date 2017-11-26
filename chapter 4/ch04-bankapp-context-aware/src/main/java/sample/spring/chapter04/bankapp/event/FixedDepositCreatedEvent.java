package sample.spring.chapter04.bankapp.event;

import java.util.Map;

public class FixedDepositCreatedEvent implements Event {
	private Map<String, Object> eventData;

	@Override
	public String getEventType() {
		return "FixedDepositCreatedEvent";
	}

	@Override
	public void setEventData(Map<String, Object> eventData) {
		this.eventData = eventData;
	}

	@Override
	public Map<String, Object> getEventData() {
		return eventData;
	}
}
