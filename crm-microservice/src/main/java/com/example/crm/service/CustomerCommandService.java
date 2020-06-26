package com.example.crm.service;

import java.util.Map;

import com.example.crm.document.Customer;

public interface CustomerCommandService {

	Customer createCustomer(Customer customer);

	Customer updateCustomerByIdentity(String identity, Customer customer);

	Customer updateCustomerByIdentity(String identity, Map<String, Object> request);

	Customer deleteCustomerByIdentity(String identity);

}
