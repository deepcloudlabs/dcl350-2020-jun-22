package com.example.hr.events;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class EmployeeHiredEvent extends BusinessEvent {

	public EmployeeHiredEvent() {
	}

	public EmployeeHiredEvent(String eventId, String topic, Object data) {
		super(eventId, topic, data);
	}

}
