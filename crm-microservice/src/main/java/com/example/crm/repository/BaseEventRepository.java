package com.example.crm.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crm.events.CustomerBaseEvent;

public interface BaseEventRepository extends MongoRepository<CustomerBaseEvent, String> {

	List<CustomerBaseEvent> findAllByIdentity(String identity);

}
