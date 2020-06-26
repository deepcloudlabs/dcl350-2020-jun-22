package com.example.icard.service;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import com.example.hr.events.BusinessEvent;

@Service
public class SimpleKafkaConsumer {
	private KafkaConsumer<String, BusinessEvent> kafkaConsumer;
	
	//TODO: @PostConstruct
	public void init() {
		Properties configs = new Properties();
		// TODO: set kafka properties
		this.kafkaConsumer = new KafkaConsumer<>(configs);
		this.kafkaConsumer.subscribe(List.of("employees"));
		new Thread(this::pollMessages).start();
	}
	
	public void pollMessages() {
		while(true) {
			this.kafkaConsumer.poll(Duration.ofMillis(100)).forEach( event -> {
				CompletableFuture.runAsync( () -> {
					System.out.println(event);
				});
			});
		}
	}
}
