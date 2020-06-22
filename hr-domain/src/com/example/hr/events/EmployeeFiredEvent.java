package com.example.hr.events;

public class EmployeeFiredEvent extends BusinessEvent {

	public EmployeeFiredEvent(String eventId, String topic, Object data) {
		super(eventId, topic, data);
	}

}
