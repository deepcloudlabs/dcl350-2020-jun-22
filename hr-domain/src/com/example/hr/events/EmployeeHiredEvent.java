package com.example.hr.events;

public class EmployeeHiredEvent extends BusinessEvent {

	public EmployeeHiredEvent(String eventId, String topic, Object data) {
		super(eventId, topic, data);
	}

}
