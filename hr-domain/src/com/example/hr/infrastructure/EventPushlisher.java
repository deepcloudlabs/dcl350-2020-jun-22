package com.example.hr.infrastructure;

import com.example.hr.events.BusinessEvent;

public interface EventPushlisher {
	public void publishEvent(BusinessEvent event);
}
