package com.example.hr.application.business;

import com.example.hr.application.EmployeeApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.events.EmployeeFiredEvent;
import com.example.hr.events.EmployeeHiredEvent;
import com.example.hr.infrastructure.EventPushlisher;
import com.example.hr.repository.EmployeeRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
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
	public boolean hireEmployee(Employee employee) {
		employeeRepository.save(employee);
		eventPushlisher.publishEvent(new EmployeeHiredEvent("", "employees", employee));
		return true;
	}

	@Override
	public boolean fireEmployee(TcKimlikNo identity) {
		var employee = employeeRepository.findByIdentity(identity);
		if (employee.isEmpty()) return false;
		Employee emp = employee.get();
		employeeRepository.remove(emp);
		eventPushlisher.publishEvent(new EmployeeFiredEvent("1", "employees", emp));
		return true;
	}

}
