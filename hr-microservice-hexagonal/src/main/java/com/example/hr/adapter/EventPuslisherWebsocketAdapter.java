package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.events.BusinessEvent;
import com.example.hr.infrastructure.EventPushlisher;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class EventPuslisherWebsocketAdapter implements EventPushlisher {
	@Autowired private SimpMessagingTemplate messagingTemplate;
	
	@Override
	public void publishEvent(BusinessEvent event) {
		messagingTemplate.convertAndSend("changes", event);
	}

}
