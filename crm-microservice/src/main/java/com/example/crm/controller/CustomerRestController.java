package com.example.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.document.Customer;
import com.example.crm.service.CustomerService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@RestController
@RequestScope
@RequestMapping("customers")
@CrossOrigin
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;

	@GetMapping(params = { "pagesize", "pageno" })
	public List<Customer> getAllCustomers(@RequestParam int pagesize, @RequestParam int pageno) {
		return customerService.findAllCustomers(pagesize, pageno);
	}

	@GetMapping("{identity}")
	public Customer getCustomerByIdentity(@PathVariable String identity) {
		return customerService.findCustomerByIdentity(identity);
	}

	@PostMapping
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}

	@PutMapping("{identity}")
	public Customer updateCustomer(@PathVariable String identity, @RequestBody Customer customer) {
		return customerService.updateCustomerByIdentity(identity, customer);
	}

	@PatchMapping("{identity}")
	public Customer patchCustomer(@PathVariable String identity, @RequestBody Map<String, Object> request) {
		return customerService.updateCustomerByIdentity(identity, request);
	}

	@DeleteMapping("{identity}")
	public Customer removeCustomerByIdentity(@PathVariable String identity) {
		return customerService.deleteCustomerByIdentity(identity);
	}
}
