package com.example.hr.adapter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.hr.events.BusinessEvent;
import com.example.hr.infrastructure.EventPushlisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
//@Service
public class EventPublisherRabbitAdapter implements EventPushlisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private ObjectMapper mapper;

	@Override
	public void publishEvent(BusinessEvent event) {
		String payload;
		try {
			payload = mapper.writeValueAsString(event);
			rabbitTemplate.convertAndSend("employeex", null, payload);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
