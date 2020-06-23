package com.example.hr.infrastructure;

import com.example.hr.events.BusinessEvent;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface EventPushlisher {
	public void publishEvent(BusinessEvent event);
}
