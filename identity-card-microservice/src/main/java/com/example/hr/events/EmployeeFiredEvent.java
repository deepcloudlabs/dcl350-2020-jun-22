package com.example.hr.events;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class EmployeeFiredEvent extends BusinessEvent {

	public EmployeeFiredEvent() {
	}

	public EmployeeFiredEvent(String eventId, String topic, Object data) {
		super(eventId, topic, data);
	}

}
