package com.example.crm.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crm.document.Customer;
import com.example.crm.events.CustomerCreatedEvent;
import com.example.crm.repository.BaseEventRepository;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.service.CustomerQueryService;

@Service
public class StandardCustomerQueryService implements CustomerQueryService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private BaseEventRepository eventRepository;

	@Override
	public List<Customer> findAllCustomers(int pagesize, int pageno) {
		return customerRepository.findAll();
	}

	@Override
	public Customer findCustomerByIdentity(String identity) {
		var events = eventRepository.findAllByIdentity(identity);
		if (events.isEmpty())
			throw new IllegalArgumentException("Cannot find customer");
		Customer customer = CustomerCreatedEvent.class.cast(events.get(0)).getCustomer();
		// TODO: use the remainings events to reconstruct final customer state
		return customer;
	}

}
