package com.example.icard.service;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import com.example.hr.events.BusinessEvent;

@Service
public class SimpleKafkaConsumer {
	private KafkaConsumer<String, BusinessEvent> kafkaConsumer;

	@PostConstruct
	public void init() {
		var consumerProperties = new Properties();
		consumerProperties.put("bootstrap.servers", "localhost:9092");
		consumerProperties.put("group.id", "hr");
		consumerProperties.put("zookeeper.session.timeout.ms", "6000");
		consumerProperties.put("zookeeper.sync.time.ms", "2000");
		consumerProperties.put("auto.commit.enable", "false");
		consumerProperties.put("auto.commit.interval.ms", "1000");
		consumerProperties.put("consumer.timeout.ms", "-1");
		consumerProperties.put("max.poll.records", "1");
		consumerProperties.put("value.deserializer", "org.springframework.kafka.support.serializer.JsonDeserializer");
		consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		consumerProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		this.kafkaConsumer = new KafkaConsumer<>(consumerProperties);
		this.kafkaConsumer.subscribe(List.of("employees"));
		new Thread(this::pollMessages).start();
	}

	public void pollMessages() {
		while (true) {
			this.kafkaConsumer.poll(Duration.ofMillis(100)).forEach(event -> {
				CompletableFuture.runAsync(() -> {
					System.out.println(event);
				});
			});
		}
	}
}
