package com.example.icard.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.hr.events.BusinessEvent;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class CardService {

	@RabbitListener(queues = "employees")
	public void listen(@Payload String payload) {
		// payload is encoded as JSON
		System.err.println(payload);
	}

	@KafkaListener(topics = "employees")
	public void listenKafka(BusinessEvent event) {
		System.err.println(event);
	}

}
