package com.example.crm.service;

import java.util.List;

import com.example.crm.document.Customer;

public interface CustomerQueryService {

	List<Customer> findAllCustomers(int pagesize, int pageno);

	Customer findCustomerByIdentity(String identity);

}
