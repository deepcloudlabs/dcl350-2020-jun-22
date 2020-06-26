package com.example.crm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crm.document.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{
	
}
