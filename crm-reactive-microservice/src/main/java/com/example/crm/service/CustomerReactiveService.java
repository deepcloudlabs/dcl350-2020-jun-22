package com.example.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.Customer;
import com.example.crm.repository.CustomerReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class CustomerReactiveService {
	@Autowired
	private CustomerReactiveRepository customerRepository;

	public Flux<Customer> findAllCustomers(int pagesize, int pageno) {
		return customerRepository.findAllFlux(PageRequest.of(pageno, pagesize));
	}

	public Mono<Customer> findCustomerByIdentity(String identity) {
		return customerRepository.findById(identity);
	}
}
