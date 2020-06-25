package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.event.TradeEvent;

public interface TradeEventRepository extends MongoRepository<TradeEvent, String> {

}
