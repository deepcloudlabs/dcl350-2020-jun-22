package com.example.hr.application.business;

import com.example.hr.application.EmployeeApplication;
import com.example.hr.domain.Employee;
import com.example.hr.events.EmployeeFiredEvent;
import com.example.hr.events.EmployeeHiredEvent;
import com.example.hr.infrastructure.EventPushlisher;
import com.example.hr.repository.EmployeeRepository;

public class SimpleEmployeeApplication implements EmployeeApplication {
	private EmployeeRepository employeeRepository;
	private EventPushlisher eventPushlisher;

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void setEventPushlisher(EventPushlisher eventPushlisher) {
		this.eventPushlisher = eventPushlisher;
	}

	@Override
	public void hireEmployee(Employee employee) {
		employeeRepository.save(employee);
		eventPushlisher.publishEvent(new EmployeeHiredEvent("", "employees", employee));
	}

	@Override
	public void fireEmployee(Employee employee) {
		employeeRepository.remove(employee);
		eventPushlisher.publishEvent(new EmployeeFiredEvent("", "employees", employee));
	}

}
