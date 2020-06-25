package com.example.hr.application;

import java.util.Optional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface EmployeeApplication {

	boolean hireEmployee(Employee employee);

	Optional<Employee> fireEmployee(TcKimlikNo identity);

}