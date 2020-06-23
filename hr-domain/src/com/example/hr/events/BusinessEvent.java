package com.example.hr.events;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class BusinessEvent {
	private final String eventId;
	private final String topic;
	private final Object data;

	public BusinessEvent(String eventId, String topic, Object data) {
		this.eventId = eventId;
		this.topic = topic;
		this.data = data;
	}

	public String getEventId() {
		return eventId;
	}

	public String getTopic() {
		return topic;
	}

	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		return "BusinessEvent [eventId=" + eventId + ", topic=" + topic + ", data=" + data + "]";
	}

}
