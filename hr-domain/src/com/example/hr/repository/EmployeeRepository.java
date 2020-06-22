package com.example.hr.repository;

import java.util.Optional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public interface EmployeeRepository {
	Optional<Employee> findByIdentity(TcKimlikNo identity);
	void save(Employee employee);
	void remove(Employee employee);
}
