package com.example.crm.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.document.Customer;

import reactor.core.publisher.Flux;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface CustomerReactiveRepository extends ReactiveMongoRepository<Customer, String>{

	@Query("{}")
	Flux<Customer> findAllFlux(PageRequest page);

}
