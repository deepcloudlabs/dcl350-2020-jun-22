package com.example.hr.adapter;

import org.springframework.stereotype.Service;

import com.example.hr.events.BusinessEvent;
import com.example.hr.infrastructure.EventPushlisher;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class EventPuslisherAdapter implements EventPushlisher {

	@Override
	public void publishEvent(BusinessEvent event) {
		// TODO Auto-generated method stub

	}

}
