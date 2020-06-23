package com.example.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.application.EmployeeApplication;
import com.example.hr.application.business.SimpleEmployeeApplication;
import com.example.hr.infrastructure.EventPushlisher;
import com.example.hr.repository.EmployeeRepository;

@Configuration
public class AppConfig {

	@Bean
	public EmployeeApplication employeeApplication(EmployeeRepository employeeRepository,
			EventPushlisher eventPushlisher) {
		SimpleEmployeeApplication employeeApplication = new SimpleEmployeeApplication();
		employeeApplication.setEmployeeRepository(employeeRepository);
		employeeApplication.setEventPushlisher(eventPushlisher);
		return employeeApplication;
	}
}
