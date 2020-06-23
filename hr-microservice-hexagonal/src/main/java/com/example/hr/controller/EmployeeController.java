package com.example.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.application.EmployeeApplication;
import com.example.hr.dto.EmployeeRequest;
import com.example.hr.dto.EmployeeResponse;

@RestController
@RequestScope
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {
	@Autowired
	private EmployeeApplication employeeApplication;
	
	@PostMapping
	public EmployeeResponse hireEmployee(@RequestBody EmployeeRequest request) {
		employeeApplication.hireEmployee(null);
		return null;
	}
}
