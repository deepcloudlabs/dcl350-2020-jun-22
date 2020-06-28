package com.example.crm.service.business;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crm.document.Customer;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.service.CustomerService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class StandardCustomerService implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		String identity = customer.getIdentity();
		var managedCustomer = customerRepository.findById(identity);
		if (managedCustomer.isEmpty()) {
			throw new IllegalArgumentException("Customer already exists");
		}
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomerByIdentity(String identity, Customer customer) {
		var managedCustomer = customerRepository.findById(identity);
		if (managedCustomer.isPresent()) {
			return customerRepository.save(customer);
		}
		throw new IllegalArgumentException("Customer does not exist");
	}

	@Override
	public Customer updateCustomerByIdentity(String identity, Map<String, Object> request) {
		var managedCustomer = customerRepository.findById(identity);
		if (managedCustomer.isPresent()) {
			var customer = managedCustomer.get();
			request.forEach((field, value) -> {
				Field declaredField;
				try {
					declaredField = Customer.class.getDeclaredField(field);
					declaredField.setAccessible(true);
					declaredField.set(customer, value);
					declaredField.setAccessible(false);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			});
			return customerRepository.save(customer);
		}
		throw new IllegalArgumentException("Customer does not exist");
	}

	@Override
	public Customer deleteCustomerByIdentity(String identity) {
		var customer = customerRepository.findById(identity);
		if (customer.isPresent()) {
			Customer removedCustomer = customer.get();
			customerRepository.delete(removedCustomer);
			return removedCustomer;
		}
		throw new IllegalArgumentException("Customer does not exist");
	}

	@Override
	public List<Customer> findAllCustomers(int pagesize, int pageno) {
		return customerRepository.findAll();
	}

	@Override
	public Customer findCustomerByIdentity(String identity) {
		return customerRepository.findById(identity)
				.orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

	}

}
