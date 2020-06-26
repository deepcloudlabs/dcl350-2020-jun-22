package com.example.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.document.Customer;
import com.example.crm.service.CustomerReactiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
@CrossOrigin
public class CustomerReactiveRestController {
	@Autowired
	private CustomerReactiveService customerService;

	@GetMapping(params = { "pagesize", "pageno" })
	public Flux<Customer> getAllCustomers(@RequestParam int pagesize, @RequestParam int pageno) {
		return customerService.findAllCustomers(pagesize, pageno);
	}

	@GetMapping("{identity}")
	public Mono<Customer> getCustomerByIdentity(@PathVariable String identity) {
		return customerService.findCustomerByIdentity(identity);
	}

}
